/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem;

import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import javax.swing.JDesktopPane;

/**
 *
 * @author bruno
 */
public class VisualizarImagemPresenter extends BaseInternalFramePresenter<VisualizarImagemView> {

    public VisualizarImagemPresenter(JDesktopPane container, Imagem imagem) {
        super(container, new VisualizarImagemView());
        var view = getView();
        try {
            view.getTxtImagem().setIcon(imagem.getImageIcon());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        view.setVisible(true);
    }

}
