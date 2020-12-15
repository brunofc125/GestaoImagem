/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem.state;

import br.ufes.gestao.imagem.view.imagem.ListaImagemPresenter;
import br.ufes.gestao.imagem.view.permissao.SalvarPermissaoPresenter;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ListaImagemPermissaoPresenter extends ListaImagemPresenterState {

    private Long idUsuario;

    public ListaImagemPermissaoPresenter(ListaImagemPresenter presenter, Long idUsuario) {
        super(presenter);
        this.idUsuario = idUsuario;
        config();
    }

    private void config() {
        var view = presenter.getView();
        view.getBtnCompartilhar().setVisible(false);
        view.getBtnExcluir().setVisible(false);
        view.getBtnVisualizar().setVisible(true);
        view.getBtnVisualizar().setText("Permissões");
        view.getChkTodasImagens().setVisible(true);

        view.getBtnVisualizar().addActionListener((e) -> {
            salvarPermissao();
        });
    }

    private void salvarPermissao() {
        var imagemProxy = presenter.getImagemSelecionada();
        if (imagemProxy != null || presenter.isTodas()) {
            if (presenter.isTodas()) {
                new SalvarPermissaoPresenter(presenter.getContainer(), idUsuario);
            } else if (imagemProxy != null) {
                var imagem = imagemProxy.getImage();
                new SalvarPermissaoPresenter(presenter.getContainer(), idUsuario, imagem.getId());
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necessário selecionar uma imagem", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
