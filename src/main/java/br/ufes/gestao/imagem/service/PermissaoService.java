/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.Permissao;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import br.ufes.gestao.imagem.repository.PermissaoRepository;
import br.ufes.gestao.imagem.repository.UsuarioRepository;
import java.util.List;

/**
 *
 * @author bruno
 */
public class PermissaoService {

    private PermissaoRepository permissaoRepository;
    private UsuarioRepository usuarioRepository;

    public PermissaoService() {
        permissaoRepository = new PermissaoRepository();
        usuarioRepository = new UsuarioRepository();
    }

    public List<Permissao> getByUsuarioAndImagem(Long idUsuario, Long idImagem) throws Exception {
        return permissaoRepository.getByUsuarioAndImagem(idUsuario, idImagem);
    }

    public void insert(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario != null) {
            var usuario = usuarioRepository.getById(idUsuario);
            if (TipoUsuarioEnum.ADMIN.equals(usuario.getTipo())) {
                throw new Exception("Não é possível alterar permissões de administradores");
            }
        }
        permissaoRepository.insert(tipoPermissoes, idUsuario, idImagem);
    }

    public void insertTodasImagens(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario) throws Exception {
        if (idUsuario != null) {
            var usuario = usuarioRepository.getById(idUsuario);
            if (TipoUsuarioEnum.ADMIN.equals(usuario.getTipo())) {
                throw new Exception("Não é possível alterar permissões de administradores");
            }
        }
        permissaoRepository.insertTodasImagens(tipoPermissoes, idUsuario);
    }

}
