package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.NotificacaoDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;

public class NotificacaoRepository {

    private INotificacaoDAO notificacaoDAO;

    public NotificacaoRepository() {
        this.notificacaoDAO = NotificacaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }

    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception {
        notificacaoDAO.insertSolicitação(tipoPermissao, idUsuario, idImagem);
    }
    
}
