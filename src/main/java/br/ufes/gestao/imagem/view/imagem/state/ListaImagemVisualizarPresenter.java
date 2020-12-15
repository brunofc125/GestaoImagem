/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem.state;

import br.ufes.gestao.imagem.memento.Zelador;
import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import br.ufes.gestao.imagem.service.ImagemService;
import br.ufes.gestao.imagem.service.NotificacaoService;
import br.ufes.gestao.imagem.service.PermissaoService;
import br.ufes.gestao.imagem.view.imagem.CompartilharImagemPresenter;
import br.ufes.gestao.imagem.view.imagem.ListaImagemPresenter;
import br.ufes.gestao.imagem.view.imagem.SolicitarImagemPresenter;
import br.ufes.gestao.imagem.view.imagem.VisualizarImagemPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ListaImagemVisualizarPresenter extends ListaImagemPresenterState {

    private Long idUsuario;
    private PermissaoService permissaoService;
    private NotificacaoService notificacaoService;
    private ImagemService imagemService;
    private Zelador zelador;

    public ListaImagemVisualizarPresenter(ListaImagemPresenter presenter, Long idUsuario) {
        super(presenter);
        this.idUsuario = idUsuario;
        permissaoService = new PermissaoService();
        zelador = new Zelador();
        notificacaoService = new NotificacaoService();
        imagemService = new ImagemService();
        config();
    }

    private void config() {
        var view = presenter.getView();

        view.getBtnCompartilhar().setVisible(true);
        view.getBtnExcluir().setVisible(true);
        view.getBtnVisualizar().setVisible(true);
        view.getBtnVisualizar().setText("Visualizar");
        view.getChkTodasImagens().setVisible(false);

        view.getBtnVisualizar().addActionListener((e) -> {
            visualizar();
        });
        view.getBtnExcluir().addActionListener((e) -> {
            excluir();
        });
        view.getBtnCompartilhar().addActionListener((e) -> {
            compartilhar();
        });
        view.getBtnRestaurar().addActionListener((e) -> {
            restaurar();
        });
    }

    private void visualizar() {
        try {
            var imagemProxy = presenter.getImagemSelecionada();
            if (imagemProxy != null) {
                var imagem = imagemProxy.getImage();
                var permissoes = permissaoService.getByUsuarioAndImagem(idUsuario, imagem.getId());
                if (permissoes != null && permissoes.size() > 0) {
                    var tipoPermissoes = permissoes.stream().map(p -> p.getTipo()).collect(Collectors.toList());
                    if (tipoPermissoes != null && tipoPermissoes.size() > 0 && tipoPermissoes.contains(TipoPermissaoEnum.VISUALIZAR)) {
                        new VisualizarImagemPresenter(presenter.getContainer(), imagem);
                    } else {
                        pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_VISUALIZAR, imagem.getId());
                    }
                } else {
                    pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_VISUALIZAR, imagem.getId());
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma imagem", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListaImagemVisualizarPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluir() {
        try {
            var imagemProxy = presenter.getImagemSelecionada();
            if (imagemProxy != null) {
                var imagem = imagemProxy.getImage();
                var permissoes = permissaoService.getByUsuarioAndImagem(idUsuario, imagem.getId());
                if (permissoes != null && permissoes.size() > 0) {
                    var tipoPermissoes = permissoes.stream().map(p -> p.getTipo()).collect(Collectors.toList());
                    if (tipoPermissoes != null && tipoPermissoes.size() > 0 && tipoPermissoes.contains(TipoPermissaoEnum.EXCLUIR)) {
                        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir a imagem?", "", JOptionPane.YES_NO_OPTION);
                        if (opcao == 0) {
                            zelador.addImagemMemento(imagem.getMemento());
                            imagemService.delete(imagem.getId());
                            presenter.criarLista();
                            JOptionPane.showMessageDialog(null, "Imagem excluída com sucesso!", "", JOptionPane.OK_OPTION);
                        }
                    } else {
                        pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_EXCLUIR, imagem.getId());
                    }
                } else {
                    pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_EXCLUIR, imagem.getId());
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma imagem", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListaImagemVisualizarPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void compartilhar() {
        try {
            var imagemProxy = presenter.getImagemSelecionada();
            if (imagemProxy != null) {
                var imagem = imagemProxy.getImage();
                var permissoes = permissaoService.getByUsuarioAndImagem(idUsuario, imagem.getId());
                if (permissoes != null && permissoes.size() > 0) {
                    var tipoPermissoes = permissoes.stream().map(p -> p.getTipo()).collect(Collectors.toList());
                    if (tipoPermissoes != null && tipoPermissoes.size() > 0 && tipoPermissoes.contains(TipoPermissaoEnum.EXCLUIR)) {
                        new CompartilharImagemPresenter(presenter.getContainer(), idUsuario, imagem.getId());
                    } else {
                        pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_COMPARTILHAR, imagem.getId());
                    }
                } else {
                    pedirSolicitacao(TipoNotificacaoEnum.SOLICITACAO_COMPARTILHAR, imagem.getId());
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma imagem", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListaImagemVisualizarPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void restaurar() {
        try {
            if (zelador.temRegistro()) {
                var imagemMemento = zelador.getUltimo();
                var imagem = new Imagem();
                imagem.restauraMemento(imagemMemento);
                imagemService.restaurar(imagem.getId());
                this.presenter.criarLista();
            } else {
                JOptionPane.showMessageDialog(null, "Não tem resgistro para restaurar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void pedirSolicitacao(TipoNotificacaoEnum tipo, Long idImagem) {
        new SolicitarImagemPresenter(presenter.getContainer(), tipo, idImagem, idUsuario);
    }

}
