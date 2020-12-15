package br.ufes.gestao.imagem.dao.interfaces;

import br.ufes.gestao.imagem.model.Notificacao;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import java.util.List;

public interface INotificacaoDAO {

    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception;

    public void insert(TipoNotificacaoEnum tipoPermissao, Long idUsuarioRementente, Long idUsuarioDestinatario, Long idImagem) throws Exception;

    public Long getQtdNotificacoes(Long idUsuario) throws Exception;

    public List<Notificacao> getByUsuario(Long idUsuarioDestinatario) throws Exception;

    public void visualizar(Long idUsuario) throws Exception;

}
