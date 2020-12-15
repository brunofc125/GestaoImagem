package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.NotificacaoDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;
import br.ufes.gestao.imagem.model.Notificacao;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import java.util.List;

public class NotificacaoRepository {
    
    private INotificacaoDAO notificacaoDAO;
    
    public NotificacaoRepository() {
        this.notificacaoDAO = NotificacaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception {
        notificacaoDAO.insertSolicitação(tipoPermissao, idUsuario, idImagem);
    }
    
    public void insert(TipoNotificacaoEnum tipoPermissao, Long idUsuarioRementente, Long idUsuarioDestinatario, Long idImagem) throws Exception {
        notificacaoDAO.insert(tipoPermissao, idUsuarioRementente, idUsuarioDestinatario, idImagem);
    }
    
    public Long getQtdNotificacoes(Long idUsuario) throws Exception {
        return notificacaoDAO.getQtdNotificacoes(idUsuario);
    }
    
    public List<Notificacao> getByUsuario(Long idUsuarioDestinatario) throws Exception {
        return notificacaoDAO.getByUsuario(idUsuarioDestinatario);
    }
    
    public void visualizar(Long idUsuario) throws Exception {
        notificacaoDAO.visualizar(idUsuario);
    }
    
}
