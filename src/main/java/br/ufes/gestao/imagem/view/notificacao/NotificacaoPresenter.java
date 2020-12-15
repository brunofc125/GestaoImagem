/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.notificacao;

import br.ufes.gestao.imagem.model.Notificacao;
import br.ufes.gestao.imagem.service.NotificacaoService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class NotificacaoPresenter extends BaseInternalFramePresenter<NotificacaoView> {

    private NotificacaoService notificacaoService;
    private List<Notificacao> notificacoes;
    private DefaultTableModel tmNotificacoes;
    private Long idUsuario;
    private TelaPrincipalPresenter telaPrincipalPresenter;

    public NotificacaoPresenter(JDesktopPane container, Long idUsuario, TelaPrincipalPresenter telaPrincipalPresenter) {
        super(container, new NotificacaoView());
        notificacoes = new ArrayList<>();
        notificacaoService = new NotificacaoService();
        this.telaPrincipalPresenter = telaPrincipalPresenter;
        this.idUsuario = idUsuario;
        configurarTabela();

        getView().setVisible(true);
    }

    private void configurarTabela() {
        try {
            var view = getView();
            tmNotificacoes = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tmNotificacoes.setDataVector(new Object[][]{}, new String[]{"Tipo", "Mensagem", "Quem Enviou?", "Visualizada", "Data Envio"});
            view.getTbNotificacoes().setModel(tmNotificacoes);
            view.getTbNotificacoes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            buscar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() throws Exception {
        tmNotificacoes.setNumRows(0);
        this.notificacoes = notificacaoService.getByUsuario(idUsuario);
        if (this.notificacoes != null && this.notificacoes.size() > 0) {
            this.notificacoes.forEach(notificacao -> {
                tmNotificacoes.addRow(new Object[]{
                    notificacao.getTipo().getDescricao(),
                    notificacao.getDescricao(),
                    notificacao.getUsuarioRemetente().getNome(),
                    notificacao.isVisualizada() ? "Sim" : "Não",
                    notificacao.getDataEnvio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                });
            });
            notificacaoService.visualizar(idUsuario);
            this.telaPrincipalPresenter.getView().getBtnNotificacao().setText("0");
        }
    }
}
