/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.usuario;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.service.UsuarioService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import br.ufes.gestao.imagem.observer.IObservador;

/**
 *
 * @author bruno
 */
public class ListaUsuarioPresenter extends BaseInternalFramePresenter<ListaUsuarioView> implements IObservador {

    private UsuarioService usuarioService;
    private List<Usuario> usuarios;
    private DefaultTableModel tmUsuarios;

    public ListaUsuarioPresenter(JDesktopPane container) {
        super(container, new ListaUsuarioView());
        var view = getView();
        usuarioService = new UsuarioService();
        usuarios = new ArrayList<>();
        configurarTabela();

        view.getBtnBuscar().addActionListener((ActionEvent e) -> {
            try {
                buscar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getBtnCadastrar().addActionListener((ActionEvent e) -> {
            new ManterUsuarioPresenter(container, false).attachObserver(this);
        });

        view.getBtnVisualizar().addActionListener((ActionEvent e) -> {
            int linhaSelecionada = getView().getTbUsuarios().getSelectedRow();
            if (linhaSelecionada >= 0) {
                var id = (Long) tmUsuarios.getValueAt(linhaSelecionada, 0);
                new ManterUsuarioPresenter(container, id).attachObserver(this);
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar um contato", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getBtnConcederPermissao().addActionListener((ActionEvent e) -> {
            throw new UnsupportedOperationException("Not supported yet.");
        });

        view.getBtnExcluir().addActionListener((ActionEvent e) -> {
            try {
                excluir();
                buscar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.setVisible(true);
    }

    private void buscar() throws Exception {
        tmUsuarios.setNumRows(0);
        var nome = getView().getTxtNome().getText();
        this.usuarios = usuarioService.filter(nome);
        if (this.usuarios != null && this.usuarios.size() > 0) {
            this.usuarios.forEach(usuario -> {
                tmUsuarios.addRow(new Object[]{usuario.getId(), usuario.getNome(), usuario.getTipo().getDescricao()});
            });
        }
    }

    private void excluir() throws Exception {
        int linhaSelecionada = getView().getTbUsuarios().getSelectedRow();
        if (linhaSelecionada >= 0) {
            var usuario = usuarios.get(linhaSelecionada);
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir a tarefa", "", JOptionPane.YES_NO_OPTION);
            if (opcao == 0) {
                usuarioService.delete(usuario.getId());
                JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!", "", JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um contato", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void update() {
        try {
            this.buscar();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void fechar() {
        getView().dispose();
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
            tmUsuarios.setDataVector(new Object[][]{}, new String[]{"ID", "Nome", "Tipo"});
            view.getTbUsuarios().setModel(tmUsuarios);
            view.getTbUsuarios().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            buscar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
