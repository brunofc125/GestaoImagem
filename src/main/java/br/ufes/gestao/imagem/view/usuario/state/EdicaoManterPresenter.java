package br.ufes.gestao.imagem.view.usuario.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.usuario.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

public class EdicaoManterPresenter extends ManterUsuarioPresenterState {

    public EdicaoManterPresenter(ManterUsuarioPresenter presenter) {
        super(presenter);
        init();
    }

    private void init() {
        var view = presenter.getView();
        view.getBtnExcluir().setVisible(false);
        view.getBtnSalvar().setVisible(true);
        view.getBtnSalvar().setText("Salvar");
        view.getTxtLogin().setEditable(false);

        view.getBtnSalvar().addActionListener((ae) -> {
            salvar();
        });
    }

    @Override
    public void salvar() {
        if (senhasConferem()) {
            try {
                Usuario usuario = getDados();
                if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
                    usuarioService.update(usuario);
                }
                fechar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
