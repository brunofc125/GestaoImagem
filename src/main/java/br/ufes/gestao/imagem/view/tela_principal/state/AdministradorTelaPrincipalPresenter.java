package br.ufes.gestao.imagem.view.tela_principal.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;
import br.ufes.gestao.imagem.view.usuario.ListaUsuarioPresenter;

public class AdministradorTelaPrincipalPresenter extends TelaPrincipalPresenterState {

    public AdministradorTelaPrincipalPresenter(TelaPrincipalPresenter presenter, Usuario usuario) {
        super(presenter);
        
        presenter.getView().getItmUsuarioCadastro().addActionListener((e) -> {
            new ListaUsuarioPresenter(presenter.getView().getDesktop());
        });
        
        config(usuario);
    }

    private void config(Usuario usuario) {
        setRodaPe(usuario);
        enabledAll();
    }

}
