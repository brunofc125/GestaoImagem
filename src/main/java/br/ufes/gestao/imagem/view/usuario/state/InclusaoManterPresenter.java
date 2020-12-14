package br.ufes.gestao.imagem.view.usuario.state;

import br.ufes.gestao.imagem.exception.BusinessException;
import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import br.ufes.gestao.imagem.view.usuario.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

public class InclusaoManterPresenter extends ManterUsuarioPresenterState {

    public InclusaoManterPresenter(ManterUsuarioPresenter presenter, boolean isFirstUser) {
        super(presenter);
        init(isFirstUser);
    }

    private void init(boolean isFirstUser) {
        var view = presenter.getView();
        view.getBtnExcluir().setVisible(false);
        view.getBtnSalvar().setVisible(true);
        view.getBtnSalvar().setText("Salvar");
        view.getTxtLogin().setEditable(true);

        if (isFirstUser) {
            view.getCbTipo().setSelectedItem(TipoUsuarioEnum.ADMIN);
            view.getCbTipo().setEnabled(false);
        } else {
            view.getCbTipo().setEnabled(true);
        }

        view.getBtnSalvar().addActionListener((ae) -> {
            salvar();
        });
    }

    @Override
    public void salvar() {
        if (senhasConferem()) {
            try {
                Usuario usuario = getDados();
                usuarioService.insert(usuario);
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Sucesso", JOptionPane.OK_OPTION);
                fechar();
                
            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
