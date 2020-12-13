/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.repository.UsuarioRepository;

/**
 *
 * @author bruno
 */
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }
    
    public void insert(Usuario usuario) throws Exception {
        this.usuarioRepository.insert(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        this.usuarioRepository.update(usuario);
    }

    public Usuario getById(Long id) throws Exception {
        return this.usuarioRepository.getById(id);
    }

    public Usuario getByLogin(String login, String senha) throws Exception {
        return this.usuarioRepository.getByLogin(login, senha);
    }

    public void delete(Long id) throws Exception {
        this.usuarioRepository.delete(id);
    }

    public boolean loginExists(String login) throws Exception {
        return this.usuarioRepository.loginExists(login);
    }
    
    
}
