package br.ufes.gestao.imagem.view.usuario.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import br.ufes.gestao.imagem.service.UsuarioService;
import br.ufes.gestao.imagem.view.usuario.ManterUsuarioPresenter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public abstract class ManterUsuarioPresenterState {

    protected ManterUsuarioPresenter presenter;
    protected UsuarioService usuarioService;

    public ManterUsuarioPresenterState(ManterUsuarioPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter nao informada");
        }
        this.presenter = presenter;
        var view = this.presenter.getView();
        removerActionListeners(view.getBtnExcluir());
        removerActionListeners(view.getBtnFechar());
        removerActionListeners(view.getBtnSalvar());
        presenter.getView().getCbTipo().setModel(new DefaultComboBoxModel(TipoUsuarioEnum.values()));
        presenter.getView().getCbTipo().setSelectedIndex(0);
        view.getBtnFechar().addActionListener((e) -> {
            this.fechar();
        });
        this.usuarioService = new UsuarioService();
    }

    public void fechar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }

    public void salvar() {

    }

    public void editar() {

    }

    public void excluir() {

    }

    protected Usuario getDados() {
        var view = presenter.getView();
        var usuario = presenter.getUsuario();
        usuario.setNome(view.getTxtNome().getText());
        usuario.setLogin(view.getTxtLogin().getText());
        usuario.setSenha(new String(view.getTxtSenha().getPassword()));
        usuario.setTipo((TipoUsuarioEnum)view.getCbTipo().getSelectedItem());
        return usuario;
    }

    protected void setDados(Usuario usuario) {
        var view = presenter.getView();
        view.getTxtNome().setText(usuario.getNome());
        view.getTxtLogin().setText(usuario.getLogin());
        view.getCbTipo().setSelectedItem(usuario.getTipo());
    }

    protected void disableCampos() {
        var view = presenter.getView();
        view.getTxtNome().setEditable(false);
        view.getTxtLogin().setEditable(false);
        view.getTxtSenha().setEditable(false);
        view.getTxtConfirmarSenha().setEditable(false);
        view.getCbTipo().setEnabled(false);
    }

    protected void enableCampos() {
        var view = presenter.getView();
        view.getTxtNome().setEditable(true);
        view.getTxtLogin().setEditable(true);
        view.getTxtSenha().setEditable(true);
        view.getTxtConfirmarSenha().setEditable(true);
        view.getCbTipo().setEnabled(true);
    }
    
    protected void exibirSenhas(boolean exibir) {
        var view = presenter.getView();
        view.getTxtSenha().setVisible(exibir);
        view.getLblSenha().setVisible(exibir);
        view.getTxtConfirmarSenha().setVisible(exibir);
        view.getLblConfirmarSenha().setVisible(exibir);
    }  

    protected boolean senhasConferem() {
        var senha = new String(presenter.getView().getTxtSenha().getPassword());
        var senhaNovamente = new String(presenter.getView().getTxtConfirmarSenha().getPassword());
        return senha.equals(senhaNovamente);
    }

    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
}
