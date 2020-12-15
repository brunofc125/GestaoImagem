/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import br.ufes.gestao.imagem.service.NotificacaoService;
import br.ufes.gestao.imagem.service.UsuarioService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class CompartilharImagemPresenter extends BaseInternalFramePresenter<CompartilharImagemView> {

    private UsuarioService usuarioService;
    private NotificacaoService notificacaoService;
    private List<Usuario> usuarios;
    private DefaultTableModel tmUsuarios;
    private Long idUsuario;
    private Long idImagem;

    public CompartilharImagemPresenter(JDesktopPane container, Long idUsuario, Long idImagem) {
        super(container, new CompartilharImagemView());
        this.usuarioService = new UsuarioService();
        this.notificacaoService = new NotificacaoService();
        this.idImagem = idImagem;
        this.idUsuario = idUsuario;
        configurarTabela();
        var view = getView();

        removerActionListeners(view.getBtnBuscar());
        removerActionListeners(view.getBtnCompartilhar());
        removerActionListeners(view.getBtnFechar());

        view.getBtnBuscar().addActionListener((e) -> {
            try {
                buscar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getBtnCompartilhar().addActionListener((ActionEvent e) -> {
            try {
                int linhaSelecionada = getView().getTbUsuarios().getSelectedRow();
                if (linhaSelecionada >= 0) {
                    var id = (Long) tmUsuarios.getValueAt(linhaSelecionada, 0);
                    notificacaoService.insert(TipoNotificacaoEnum.COMPARTILHAMENTO, idUsuario, id, idImagem);
                    JOptionPane.showMessageDialog(null, "Compartilhamento Realizado", "Sucesso", JOptionPane.OK_OPTION);
                    getView().dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "É necessário selecionar um usuário", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getBtnFechar().addActionListener((e) -> {
            getView().dispose();
        });

        view.setVisible(true);
    }

    private void configurarTabela() {
        try {
            var view = getView();
            tmUsuarios = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tmUsuarios.setDataVector(new Object[][]{}, new String[]{"ID", "Nome"});
            view.getTbUsuarios().setModel(tmUsuarios);
            view.getTbUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            buscar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() throws Exception {
        tmUsuarios.setNumRows(0);
        var nome = getView().getTxtNome().getText();
        this.usuarios = usuarioService.filter(nome);
        if (this.usuarios != null && this.usuarios.size() > 0) {
            this.usuarios.forEach(usuario -> {
                tmUsuarios.addRow(new Object[]{usuario.getId(), usuario.getNome()});
            });
        }
    }

    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
}
