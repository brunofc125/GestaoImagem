package br.ufes.gestao.imagem.dao.interfaces;

import br.ufes.gestao.imagem.model.Usuario;

public interface IUsuarioDAO {

    public void insert(Usuario usuario) throws Exception;

    public void update(Usuario usuario) throws Exception;

    public Usuario getById(Long id) throws Exception;

    public Usuario getByLogin(String login, String senha) throws Exception;

    public void delete(Long id) throws Exception;

    public boolean loginExists(String login) throws Exception;

}
