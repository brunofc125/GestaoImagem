/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.usuario;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.service.UsuarioService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import br.ufes.gestao.imagem.view.usuario.state.InclusaoManterPresenter;
import br.ufes.gestao.imagem.view.usuario.state.ManterUsuarioPresenterState;
import br.ufes.gestao.imagem.view.usuario.state.VisualizacaoManterPresenter;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ManterUsuarioPresenter extends BaseInternalFramePresenter<ManterUsuarioView> {

    private ManterUsuarioPresenterState state;
    private UsuarioService usuarioService;
    private Usuario usuario;

    public ManterUsuarioPresenter(JDesktopPane container, Usuario usuario) {
        super(container, new ManterUsuarioView());
        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("Usuário não informada");
        }
        this.usuarioService = new UsuarioService();

        try {
            this.usuario = usuarioService.getById(usuario.getId());
            this.setState(new VisualizacaoManterPresenter(this, this.usuario));
            getView().setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ManterUsuarioPresenter(JDesktopPane container, boolean isFirstUser) {
        super(container, new ManterUsuarioView());
        this.usuarioService = new UsuarioService();
        this.usuario = new Usuario();
        try {
            this.setState(new InclusaoManterPresenter(this, isFirstUser));
            getView().setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setState(ManterUsuarioPresenterState state) {
        this.state = state;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
