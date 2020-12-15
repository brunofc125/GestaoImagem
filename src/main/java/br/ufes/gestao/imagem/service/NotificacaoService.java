/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import br.ufes.gestao.imagem.repository.NotificacaoRepository;

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
    
}
