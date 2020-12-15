/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import br.ufes.gestao.imagem.repository.PermissaoRepository;
import br.ufes.gestao.imagem.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PermissaoRepository permissaoRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
        permissaoRepository = new PermissaoRepository();
    }

    public Usuario insert(Usuario usuario) throws Exception {
        var usuarioBanco = this.usuarioRepository.insert(usuario);
        if(TipoUsuarioEnum.ADMIN.equals(usuarioBanco.getTipo())) {
            List<TipoPermissaoEnum> list = new ArrayList<>();
            list.add(TipoPermissaoEnum.EXCLUIR);
            list.add(TipoPermissaoEnum.VISUALIZAR);
            list.add(TipoPermissaoEnum.COMPARTILHAR);
            permissaoRepository.insertTodasImagens(list, usuarioBanco.getId());
        }
        return usuarioBanco;
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

    public List<Usuario> filter(String nome) throws Exception {
        return this.usuarioRepository.filter(nome);
    }

    public void delete(Long id) throws Exception {
        this.usuarioRepository.delete(id);
    }

    public boolean loginExists(String login) throws Exception {
        return this.usuarioRepository.loginExists(login);
    }

    public Long getQtdUsuariosAtivos() throws Exception {
        return this.usuarioRepository.getQtdUsuariosAtivos();
    }

}
