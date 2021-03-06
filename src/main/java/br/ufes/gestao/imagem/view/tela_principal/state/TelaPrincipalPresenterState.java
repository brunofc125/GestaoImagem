/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.tela_principal.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.service.NotificacaoService;
import br.ufes.gestao.imagem.view.imagem.ListaImagemPresenter;
import br.ufes.gestao.imagem.view.notificacao.NotificacaoPresenter;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 *
 * @author bruno
 */
public abstract class TelaPrincipalPresenterState {

    protected TelaPrincipalPresenter presenter;
    protected NotificacaoService notificacaoService;

    public TelaPrincipalPresenterState(TelaPrincipalPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter nao informada");
        }
        this.presenter = presenter;
        this.notificacaoService = new NotificacaoService();
        var view = this.presenter.getView();
        getQtdNotificacoes();
        removerActionListeners(view.getItmExibirImagens());
        removerActionListeners(view.getItmUsuarioCadastro());
        removerActionListeners(view.getItmSair());
        removerActionListeners(view.getBtnNotificacao());

        view.getItmExibirImagens().addActionListener((e) -> {
            new ListaImagemPresenter(view.getDesktop(), presenter.getUsuarioLogado().getId(), true);
        });

        view.getItmSair().addActionListener((e) -> {
            this.presenter.setState(new LoginTelaPrincipalPresenter(this.presenter));
        });

        view.getBtnNotificacao().addActionListener((e) -> {
            new NotificacaoPresenter(presenter.getView().getDesktop(), presenter.getUsuarioLogado().getId(), this.presenter);
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
        for (var frame : presenter.getView().getDesktop().getAllFrames()) {
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private void removerActionListeners(JMenuItem item) {
        for (var action : item.getActionListeners()) {
            item.removeActionListener(action);
        }
    }

    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }

    public void getQtdNotificacoes() {
        try {
            var qtd = notificacaoService.getQtdNotificacoes(presenter.getUsuarioLogado().getId());
            if (qtd != null) {
                presenter.getView().getBtnNotificacao().setText(qtd.toString());
            } else {
                presenter.getView().getBtnNotificacao().setText("0");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
