/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.tela_principal.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.imagem.ListaImagemPresenter;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import javax.swing.JMenuItem;

/**
 *
 * @author bruno
 */
public abstract class TelaPrincipalPresenterState {

    protected TelaPrincipalPresenter presenter;

    public TelaPrincipalPresenterState(TelaPrincipalPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter nao informada");
        }
        this.presenter = presenter;
        var view = this.presenter.getView();
        removerActionListeners(view.getItmExibirImagens());
        removerActionListeners(view.getItmUsuarioCadastro());
        removerActionListeners(view.getItmSair());

        view.getItmExibirImagens().addActionListener((e) -> {
            new ListaImagemPresenter(view.getDesktop());
        });

        view.getItmSair().addActionListener((e) -> {
            this.presenter.setState(new LoginTelaPrincipalPresenter(this.presenter));
        });
    }

    public void fechar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }

    public void setRodaPe(Usuario usuario) {
        if (usuario != null) {
            var view = this.presenter.getView();
            view.getLblNomeUsuario().setText(usuario.getNome() + "      Tipo: " + usuario.getTipo().getDescricao());
        }
    }

    public void cancelar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }

    public void disabledAll() {
        var view = presenter.getView();
        view.getBtnNotificacao().setVisible(false);
        view.getDesktop().setVisible(true);
        view.getItmExibirImagens().setVisible(false);
        view.getItmUsuarioCadastro().setVisible(false);
        view.getMnBar().setVisible(false);
        view.getMnImagem().setVisible(false);
        view.getMnUsuario().setVisible(false);
        view.getLbUsuario().setVisible(false);
        view.getLblNomeUsuario().setVisible(false);
    }

    public void enabledAll() {
        var view = presenter.getView();
        view.getBtnNotificacao().setVisible(true);
        view.getDesktop().setVisible(true);
        view.getItmExibirImagens().setVisible(true);
        view.getItmUsuarioCadastro().setVisible(true);
        view.getMnBar().setVisible(true);
        view.getMnImagem().setVisible(true);
        view.getMnUsuario().setVisible(true);
        view.getLbUsuario().setVisible(true);
        view.getLblNomeUsuario().setVisible(true);
    }
    
    public void exitViews() {
        for (var frame :  presenter.getView().getDesktop().getAllFrames()) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private void removerActionListeners(JMenuItem item) {
        for (var action : item.getActionListeners()) {
            item.removeActionListener(action);
        }
    }
}
