/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.tela_principal;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.tela_principal.state.LoginTelaPrincipalPresenter;
import br.ufes.gestao.imagem.view.tela_principal.state.TelaPrincipalPresenterState;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JFrame;

/**
 *
 * @author bruno
 */
public class TelaPrincipalPresenter {

    private TelaPrincipalView view;
    private TelaPrincipalPresenterState state;
    private Usuario usuarioLogado;

    public TelaPrincipalPresenter() {
        this.view = new TelaPrincipalView();

        this.view.setState(JFrame.ICONIFIED);
        this.view.setLocationRelativeTo(this.view.getParent());
        this.view.setExtendedState(MAXIMIZED_BOTH);

        this.view.setVisible(true);

        setState(new LoginTelaPrincipalPresenter(this));

    }

    public TelaPrincipalView getView() {
        return view;
    }

    public void setState(TelaPrincipalPresenterState state) {
        this.state = state;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

}
