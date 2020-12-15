/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem.state;

import br.ufes.gestao.imagem.service.ImagemService;
import br.ufes.gestao.imagem.view.imagem.ListaImagemPresenter;
import javax.swing.JButton;

/**
 *
 * @author bruno
 */
public abstract class ListaImagemPresenterState {

    protected ListaImagemPresenter presenter;
    private ImagemService imagemService;

    public ListaImagemPresenterState(ListaImagemPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        this.presenter = presenter;
        this.imagemService = new ImagemService();
        var view = presenter.getView();
        removerActionListeners(view.getBtnCompartilhar());
        removerActionListeners(view.getBtnExcluir());
        removerActionListeners(view.getBtnVisualizar());
    }
    
    public void fechar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }

}
