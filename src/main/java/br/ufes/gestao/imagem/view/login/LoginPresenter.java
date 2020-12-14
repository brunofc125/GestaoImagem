/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.login;

import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import br.ufes.gestao.imagem.service.UsuarioService;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import br.ufes.gestao.imagem.view.tela_principal.state.AdministradorTelaPrincipalPresenter;
import br.ufes.gestao.imagem.view.tela_principal.state.UsuarioTelaPrincipalPresenter;
import br.ufes.gestao.imagem.view.usuario.ManterUsuarioPresenter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class LoginPresenter extends BaseInternalFramePresenter<LoginView> {

    private TelaPrincipalPresenter telaPrincipalPresenter;
    private UsuarioService usuarioService;

    public LoginPresenter(JDesktopPane container, TelaPrincipalPresenter telaPrincipalPresenter) {
        super(container, new LoginView());
        this.usuarioService = new UsuarioService();
        this.telaPrincipalPresenter = telaPrincipalPresenter;
        var view = getView();

        view.getBtnLogin().addActionListener((e) -> {
            logar();
        });

        view.getBtnLogin().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {
                    logar();
                }
            }
        });

        view.getTxtLogin().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {
                    logar();
                }
            }
        });

        view.getTxtSenha().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {
                    logar();
                }
            }
        });

        this.getView().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                exibirCadastro();
            }

            @Override
            public void focusLost(FocusEvent e) {
                exibirCadastro();
            }
        });

        getView().setVisible(true);
    }

    private void logar() {
        var view = getView();
        var login = view.getTxtLogin().getText();
        var senha = new String(view.getTxtSenha().getPassword());

        try {
            var usuario = usuarioService.getByLogin(login, senha);
            if (usuario != null && usuario.getId() != null) {
                this.telaPrincipalPresenter.setUsuarioLogado(usuario);
                if (TipoUsuarioEnum.ADMIN.equals(usuario.getTipo())) {
                    this.telaPrincipalPresenter.setState(new AdministradorTelaPrincipalPresenter(this.telaPrincipalPresenter, usuario));
                } else if (TipoUsuarioEnum.USUARIO.equals(usuario.getTipo())) {
                    this.telaPrincipalPresenter.setState(new UsuarioTelaPrincipalPresenter(this.telaPrincipalPresenter, usuario));
                }
                view.setVisible(false);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void exibirCadastro() {
        var view = getView();
        try {
            Long qtd = usuarioService.getQtdUsuariosAtivos();
            if (qtd == 0) {
                view.getBtnCadastrar().setVisible(true);
                view.getBtnCadastrar().addActionListener((e) -> {
                    new ManterUsuarioPresenter(getContainer(), true);
                });
            } else {
                view.getBtnCadastrar().setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
