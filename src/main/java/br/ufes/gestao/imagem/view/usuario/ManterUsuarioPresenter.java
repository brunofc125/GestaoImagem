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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import br.ufes.gestao.imagem.observer.IObservador;
import br.ufes.gestao.imagem.observer.IObservado;

/**
 *
 * @author bruno
 */
public class ManterUsuarioPresenter extends BaseInternalFramePresenter<ManterUsuarioView> implements IObservado {

    private ManterUsuarioPresenterState state;
    private UsuarioService usuarioService;
    private Usuario usuario;   
    private List<IObservador> observers;

    public ManterUsuarioPresenter(JDesktopPane container, Long idUsuario) {
        super(container, new ManterUsuarioView());
        if (idUsuario == null) {
            throw new RuntimeException("Usuário não informada");
        }
        this.observers = new ArrayList<>();
        this.usuarioService = new UsuarioService();
        
        try {
            this.usuario = usuarioService.getById(idUsuario);
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
        this.observers = new ArrayList<>();
        
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

    @Override
    public void attachObserver(IObservador observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(IObservador observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObservador observer : observers) {
            observer.update();
        }
    }

}
