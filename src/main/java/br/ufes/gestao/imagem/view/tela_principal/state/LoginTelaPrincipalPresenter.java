package br.ufes.gestao.imagem.view.tela_principal.state;

import br.ufes.gestao.imagem.view.login.LoginPresenter;
import br.ufes.gestao.imagem.view.tela_principal.TelaPrincipalPresenter;

public class LoginTelaPrincipalPresenter extends TelaPrincipalPresenterState {

    public LoginTelaPrincipalPresenter(TelaPrincipalPresenter presenter) {
        super(presenter);
        config();
    }

    private void config() {
        disabledAll();
        exitViews();
        new LoginPresenter(presenter.getView().getDesktop(), presenter);
    }

   
}
