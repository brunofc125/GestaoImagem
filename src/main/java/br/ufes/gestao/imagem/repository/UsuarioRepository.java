package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.UsuarioDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.IUsuarioDAO;
import br.ufes.gestao.imagem.model.Usuario;

public class UsuarioRepository {

    private IUsuarioDAO usuarioDAO;

    public UsuarioRepository() {
        this.usuarioDAO = UsuarioDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }

    public void insert(Usuario usuario) throws Exception {
        this.usuarioDAO.insert(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        this.usuarioDAO.update(usuario);
    }

    public Usuario getById(Long id) throws Exception {
        return this.usuarioDAO.getById(id);
    }

    public Usuario getByLogin(String login, String senha) throws Exception {
        return this.usuarioDAO.getByLogin(login, senha);
    }

    public void delete(Long id) throws Exception {
        this.usuarioDAO.delete(id);
    }

    public boolean loginExists(String login) throws Exception {
        return this.usuarioDAO.loginExists(login);
    }
}
