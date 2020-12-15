package br.ufes.gestao.imagem.dao.interfaces;

import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;

public interface INotificacaoDAO {

    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception;
}
