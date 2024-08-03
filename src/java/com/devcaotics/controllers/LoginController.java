package com.devcaotics.controllers;

import com.devcaotics.model.dao.ManagerDao;
import com.devcaotics.model.negocio.LoteProduto;
import com.devcaotics.model.negocio.Mercadinho;
import com.devcaotics.model.negocio.ONG;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class LoginController {

    private static LoginController myself = null;

    public static LoginController getCurrentInstance() {
        if (myself == null)
            myself = new LoginController();
        return myself;
    }

    private List<LoteProduto> lotes = new ArrayList<>();
    
    private Mercadinho mercadinhoLogado;
    private ONG ongLogada;
    private String login;
    private String senha;

    public String realizarLogin() {
        try {
            // Assuming you are only dealing with Mercadinho for now
            Mercadinho mLogin = (Mercadinho) ManagerDao.getCurrentInstance()
                    .readAll("select m from Mercadinho m where m.login = '" + login
                            + "' and m.senha = '" + senha + "'", Mercadinho.class).get(0);
            this.mercadinhoLogado = mLogin;
            return "indexMercadinho";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Erro ao Logar", "Login e/ou senha estão incorretos"));
            return null;
        }
    }

    public String logout() {
        this.mercadinhoLogado = null;
        this.ongLogada = null;
        return "index";
    }

    public Mercadinho getMercadinhoLogado() {
        return mercadinhoLogado;
    }

    public void setMercadinhoLogado(Mercadinho mercadinhoLogado) {
        this.mercadinhoLogado = mercadinhoLogado;
    }

    public ONG getOngLogada() {
        return ongLogada;
    }

    public void setOngLogada(ONG ongLogada) {
        this.ongLogada = ongLogada;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
