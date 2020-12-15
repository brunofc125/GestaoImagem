package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.UsuarioDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.IUsuarioDAO;
import br.ufes.gestao.imagem.model.Usuario;
import java.util.List;

public class UsuarioRepository {

    private IUsuarioDAO usuarioDAO;

    public UsuarioRepository() {
        this.usuarioDAO = UsuarioDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }

    public Usuario insert(Usuario usuario) throws Exception {
        validate(usuario, true);
        return this.usuarioDAO.insert(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        validate(usuario, false);
        if (usuario.getId() == null) {
            throw new Exception("Usuário não informado");
        }
        this.usuarioDAO.update(usuario);
    }

    public Usuario getById(Long id) throws Exception {
        return this.usuarioDAO.getById(id);
    }

    public Usuario getByLogin(String login, String senha) throws Exception {
        return this.usuarioDAO.getByLogin(login, senha);
    }

    public List<Usuario> filter(String nome) throws Exception {
        return this.usuarioDAO.filter(nome);
    }

    public void delete(Long id) throws Exception {
        this.usuarioDAO.delete(id);
    }

    public boolean loginExists(String login) throws Exception {
        return this.usuarioDAO.loginExists(login);
    }

    public Long getQtdUsuariosAtivos() throws Exception {
        return usuarioDAO.getQtdUsuariosAtivos();
    }

    private void validate(Usuario usuario, boolean isInsert) throws Exception {
        if (usuario == null) {
            throw new Exception("Usuário não informado");
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new Exception("Nome do usuário não informado");
        }
        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new Exception("Login do usuário não informado");
        } else if (loginExists(usuario.getLogin()) && isInsert) {
            throw new Exception("Já existe usuário com este login");
        }
        if (isInsert && (usuario.getSenha() == null || usuario.getSenha().isBlank())) {
            throw new Exception("Senha do usuário não informada");
        }
        if (usuario.getTipo() == null) {
            throw new Exception("Tipo do usuário não informado");
        }
    }
}
