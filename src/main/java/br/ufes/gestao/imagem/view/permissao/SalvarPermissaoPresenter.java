/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.permissao;

import br.ufes.gestao.imagem.model.Permissao;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import br.ufes.gestao.imagem.service.PermissaoService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class SalvarPermissaoPresenter extends BaseInternalFramePresenter<SalvarPermissaoView> {

    private PermissaoService permissaoService;

    public SalvarPermissaoPresenter(JDesktopPane container, Long idUsuario, Long idImagem) {
        super(container, new SalvarPermissaoView());
        var view = getView();
        this.permissaoService = new PermissaoService();
        removerActionListeners(view.getBtnFechar());
        removerActionListeners(view.getBtnSalvar());
        List<TipoPermissaoEnum> permissoes = getPermissoes(idUsuario, idImagem);
        for (var tipo : permissoes) {
            switch (tipo) {
                case COMPARTILHAR:
                    view.getChkCompartilhar().setSelected(true);
                    break;
                case EXCLUIR:
                    view.getChkExcluir().setSelected(true);
                    break;
                case VISUALIZAR:
                    view.getChkVisualizar().setSelected(true);
                    break;
            }
        }

        view.getBtnFechar().addActionListener((e) -> {
            getView().dispose();
        });

        view.getBtnSalvar().addActionListener((e) -> {
            salvar(permissoes, idUsuario, idImagem);
        });

        view.setVisible(true);
    }

    public SalvarPermissaoPresenter(JDesktopPane container, Long idUsuario) {
        super(container, new SalvarPermissaoView());
        var view = getView();
        this.permissaoService = new PermissaoService();
        removerActionListeners(view.getBtnFechar());
        removerActionListeners(view.getBtnSalvar());

        view.getChkCompartilhar().setSelected(false);

        view.getChkExcluir().setSelected(false);

        view.getChkVisualizar().setSelected(false);

        view.getBtnFechar().addActionListener((e) -> {
            getView().dispose();
        });

        view.getBtnSalvar().addActionListener((e) -> {
            salvarAll(idUsuario);
        });

        view.setVisible(true);
    }

    private List<TipoPermissaoEnum> getPermissoes(Long idUsuario, Long idImagem) {
        try {
            List<Permissao> permissoes = permissaoService.getByUsuarioAndImagem(idUsuario, idImagem);
            if (permissoes != null && permissoes.size() > 0) {
                return permissoes.stream().map(p -> p.getTipo()).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }

    private void salvar(List<TipoPermissaoEnum> permissoes, Long idUsuario, Long idImagem) {
        try {
            permissoes = new ArrayList<>();
            var view = getView();
            if (view.getChkCompartilhar().isSelected()) {
                permissoes.add(TipoPermissaoEnum.COMPARTILHAR);
            }
            if (view.getChkExcluir().isSelected()) {
                permissoes.add(TipoPermissaoEnum.EXCLUIR);
            }
            if (view.getChkVisualizar().isSelected()) {
                permissoes.add(TipoPermissaoEnum.VISUALIZAR);
            }
            permissaoService.insert(permissoes, idUsuario, idImagem);
            fechar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarAll(Long idUsuario) {
        try {
            List<TipoPermissaoEnum> permissoes = new ArrayList<>();
            var view = getView();
            if (view.getChkCompartilhar().isSelected()) {
                permissoes.add(TipoPermissaoEnum.COMPARTILHAR);
            }
            if (view.getChkExcluir().isSelected()) {
                permissoes.add(TipoPermissaoEnum.EXCLUIR);
            }
            if (view.getChkVisualizar().isSelected()) {
                permissoes.add(TipoPermissaoEnum.VISUALIZAR);
            }
            permissaoService.insertTodasImagens(permissoes, idUsuario);
            fechar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }

    private void fechar() {
        getView().dispose();
    }

}
