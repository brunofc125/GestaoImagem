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
        enableCampos();
        exibirSenhas(true);
        view.getCbTipo().setEnabled(false);
        view.getBtnExcluir().setVisible(false);
        view.getBtnSalvar().setVisible(true);
        view.getBtnSalvar().setText("Salvar");
        view.getTxtLogin().setEditable(false);
        setDados(presenter.getUsuario());
        view.getBtnSalvar().addActionListener((ae) -> {
            salvar();
        });
    }

    @Override
    public void salvar() {
        if (senhasConferem()) {
            try {
                Usuario usuario = getDados();
                usuarioService.update(usuario);
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso", "Sucesso", JOptionPane.OK_OPTION);
                this.presenter.notifyObservers();                
                fechar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
