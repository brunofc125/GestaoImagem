package br.ufes.gestao.imagem.view.usuario.state;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.view.usuario.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

public class VisualizacaoManterPresenter extends ManterUsuarioPresenterState {

    private Long idUsuario;

    public VisualizacaoManterPresenter(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter);
        if (usuario != null && usuario.getId() != null) {
            idUsuario = usuario.getId();
        }
        init(usuario);
    }

    private void init(Usuario usuario) {
        setDados(usuario);
        disableCampos();
        exibirSenhas(false);
        var view = presenter.getView();

        view.getBtnExcluir().setVisible(true);
        view.getBtnSalvar().setVisible(true);
        view.getBtnSalvar().setText("Habilitar Edição");

        view.getBtnSalvar().addActionListener((ae) -> {
            editar();
        });

        view.getBtnExcluir().addActionListener((ae) -> {
            excluir();
        });
    }

    @Override
    public void excluir() {
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "", JOptionPane.YES_NO_OPTION);
        if (opcao == 0) {
            try {
                if (idUsuario != null) {
                    usuarioService.delete(idUsuario);
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso", "", JOptionPane.OK_OPTION);
                    this.presenter.notifyObservers();
                    fechar();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.OK_OPTION);
            }
        }
    }

    @Override
    public void editar() {
        presenter.setState(new EdicaoManterPresenter(presenter));
    }
}
