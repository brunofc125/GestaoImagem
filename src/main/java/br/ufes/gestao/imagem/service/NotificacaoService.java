/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.Notificacao;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import br.ufes.gestao.imagem.repository.NotificacaoRepository;
import java.util.List;

/**
 *
 * @author bruno
 */
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService() {
        notificacaoRepository = new NotificacaoRepository();
    }

    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception {
        notificacaoRepository.insertSolicitação(tipoPermissao, idUsuario, idImagem);
    }

    public void insert(TipoNotificacaoEnum tipoPermissao, Long idUsuarioRementente, Long idUsuarioDestinatario, Long idImagem) throws Exception {
        notificacaoRepository.insert(tipoPermissao, idUsuarioRementente, idUsuarioDestinatario, idImagem);
    }

    public Long getQtdNotificacoes(Long idUsuario) throws Exception {
        return notificacaoRepository.getQtdNotificacoes(idUsuario);
    }

    public List<Notificacao> getByUsuario(Long idUsuarioDestinatario) throws Exception {
        return notificacaoRepository.getByUsuario(idUsuarioDestinatario);
    }

    public void visualizar(Long idUsuario) throws Exception {
        notificacaoRepository.visualizar(idUsuario);
    }

}
