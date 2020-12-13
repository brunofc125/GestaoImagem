package br.ufes.gestao.imagem.view.tela_principal.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;

public class UsuarioTelaPrincipalPresenter extends TelaPrincipalPresenterState {

    public UsuarioTelaPrincipalPresenter(TelaPrincipalPresenter presenter, Usuario usuario) {
        super(presenter);
        config(usuario);
    }

    private void config(Usuario usuario) {
        var view = presenter.getView();
        setRodaPe(usuario);
        enabledAll();
        view.getMnUsuario().setVisible(false);
    }

   
}
