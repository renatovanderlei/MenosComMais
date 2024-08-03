package com.devcaotics.controllers;

import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.model.negocio.ONG;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ONGController {

    private ONG ongCadastro;
    private ONG selection = new ONG();
    private ONG ongLogado = new ONG();
    private String modalType;

    @PostConstruct
    public void init() {
        this.ongCadastro = new ONG();
        this.modalType = "create";
    }

    public ONG getONGLogado() {
        return this.ongLogado;
    }

    public void inserir(String confirma) {
        List<ONG> u = ManagerDao.getCurrentInstance()
                .readAll("select o from ONG o where o.login='" + ongCadastro.getLogin() + "'", ONG.class);

        if (u.isEmpty()) {
            if (!this.ongCadastro.getSenha().equals("") && !confirma.equals("")) {
                if (!this.ongCadastro.getSenha().equals(confirma)) {
                    FacesContext.getCurrentInstance().addMessage("formCadONG:pswSenha",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Severo", "A senha e a confirmação não batem!"));
                    return;
                }

                ManagerDao.getCurrentInstance().insert(this.ongCadastro);
                this.ongCadastro = new ONG();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("ONG cadastrada com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha não pode ficar em branco!", "Crie uma senha válida."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Login de ONG já existe!", "tente outro login"));
        }
    }

    public List<ONG> readONGs() {
        return ManagerDao.getCurrentInstance().readAll("select o from ONG o", ONG.class);
    }

    public ONG getOngCadastro() {
        return ongCadastro;
    }

    public void setOngCadastro(ONG ongCadastro) {
        this.ongCadastro = ongCadastro;
    }

    public ONG getSelection() {
        return selection;
    }

    public void setSelection(ONG selection) {
        this.selection = selection;
    }

    public String alterar() {
        ManagerDao.getCurrentInstance().update(this.selection);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "ONG atualizada com sucesso"));

        return "ongs";
    }

    public void deletar() {
        ManagerDao.getCurrentInstance().delete(this.selection);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Sucesso!", "ONG deletada"));
    }
}
