package com.devcaotics.controllers;

import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.model.negocio.Mercadinho;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class MercadinhoController {

    private Mercadinho mercadinhoCadastro;
    private Mercadinho selection = new Mercadinho();
    private Mercadinho mercadinhoLogado = new Mercadinho();
    private String modalType;

    @PostConstruct
    public void init() {
        this.mercadinhoCadastro = new Mercadinho();
        this.modalType = "create";
    }

    private Mercadinho getMercadinhoLogado() {
        mercadinhoLogado = ((LoginController) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("loginController")).getMercadinhoLogado();
        return this.mercadinhoLogado;
    }

    public void inserir(String confirma) {
        List<Mercadinho> u = new ArrayList<>();
        u = ManagerDao.getCurrentInstance().readAll("select m from Mercadinho m where m.login='" + mercadinhoCadastro.getLogin() + "'", Mercadinho.class);

        if (u.isEmpty()) {
            if (!this.mercadinhoCadastro.getSenha().equals("") && !confirma.equals("")) {
                if (!this.mercadinhoCadastro.getSenha().equals(confirma)) {
                    FacesContext.getCurrentInstance().addMessage("formCadMercadinho:pswSenha",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Severo", "A senha e a confirmação não batem!"));
                    return;
                }

                ManagerDao.getCurrentInstance().insert(this.mercadinhoCadastro);
                this.mercadinhoCadastro = new Mercadinho();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Mercadinho cadastrado com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha não pode ficar em branco!", "Crie uma senha válida."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Login de mercadinho já existe!", "Tente outro login"));
        }
    }

    public List<Mercadinho> readMercadinhos() {
        return ManagerDao.getCurrentInstance().readAll("select m from Mercadinho m", Mercadinho.class);
    }

    public Mercadinho getMercadinhoCadastro() {
        return mercadinhoCadastro;
    }

    public void setMercadinhoCadastro(Mercadinho mercadinhoCadastro) {
        this.mercadinhoCadastro = mercadinhoCadastro;
    }

    public Mercadinho getSelection() {
        return selection;
    }

    public void setSelection(Mercadinho selection) {
        this.selection = selection;
    }

    public String alterar() {
        ManagerDao.getCurrentInstance().update(this.selection);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Mercadinho atualizado com sucesso"));

        return "mercadinhos";
    }

    public void deletar() {
        ManagerDao.getCurrentInstance().delete(this.selection);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Sucesso!", "Mercadinho deletado"));
    }
}
