/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem;

import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import br.ufes.gestao.imagem.service.NotificacaoService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

/**
 *
 * @author bruno
 */
public class SolicitarImagemPresenter extends BaseInternalFramePresenter<SolicitarImagemView> {

    private NotificacaoService notificacaoService;
    private Long idUsuario;

    public SolicitarImagemPresenter(JDesktopPane container, TipoNotificacaoEnum tipoNotificacao, Long idImagem, Long idUsuario) {
        super(container, new SolicitarImagemView());
        var view = getView();
        
        removerActionListeners(view.getBtnNao());
        removerActionListeners(view.getBtnSim());
        
        notificacaoService = new NotificacaoService();
        this.idUsuario = idUsuario;
        view.setTitle("Acesso a " + tipoNotificacao.getDescricao());

        view.getBtnSim().addActionListener((e) -> {
            solicitar(tipoNotificacao, idImagem);
        });

        view.setVisible(true);
    }

    private void solicitar(TipoNotificacaoEnum tipoNotificacao, Long idImagem) {
        try {
            var todas = getView().getChkTodas().isSelected();
            idImagem = todas ? null : idImagem;
            if (!TipoNotificacaoEnum.COMPARTILHAMENTO.equals(tipoNotificacao)) {
                notificacaoService.insertSolicitação(tipoNotificacao, idUsuario, idImagem);
                fechar();
            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(SolicitarImagemPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fechar() {
        getView().dispose();
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }

}
