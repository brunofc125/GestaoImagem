package br.ufes.gestao.imagem.dao.sqlite.impl;

import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.model.Notificacao;
import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAOSQLite implements INotificacaoDAO {

    private SqliteManager manager;

    public NotificacaoDAOSQLite(SqliteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public void insertSolicitação(TipoNotificacaoEnum tipo, Long idUsuario, Long idImagem) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" insert into Notificacao (descricao, data_envio, id_usuario_remetente, id_usuario_destinatario, visualizada, tipo, id_imagem) ");
            if (idImagem != null) {
                sql.append(" select ?, ?, ?, admin.id, 0, ?, ? FROM Usuario admin where admin.tipo = 'ADMIN' and admin.excluido = 0; ");
            } else {
                sql.append(" select ?, ?, ?, admin.id, 0, ?, null FROM Usuario admin where admin.tipo = 'ADMIN' and admin.excluido = 0; ");
            }
            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, "Solicitação");
            ps.setString(2, LocalDateTime.now().format(formatter));
            ps.setLong(3, idUsuario);

            ps.setString(4, tipo.name());
            if (idImagem != null) {
                ps.setLong(5, idImagem);
            }

            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public void insert(TipoNotificacaoEnum tipoPermissao, Long idUsuarioRementente, Long idUsuarioDestinatario, Long idImagem) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" insert into Notificacao (descricao, data_envio, id_usuario_remetente, id_usuario_destinatario, visualizada, tipo, id_imagem) ");
            sql.append(" VALUES('Compartilhamento', ?, ?, ?, 0, ?, ?);");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            ps.setString(1, LocalDateTime.now().format(formatter));
            ps.setLong(2, idUsuarioRementente);
            ps.setLong(3, idUsuarioDestinatario);
            ps.setString(4, tipoPermissao.name());
            ps.setLong(5, idImagem);

            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public Long getQtdNotificacoes(Long idUsuario) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append("SELECT COUNT(n.id) FROM Notificacao n ");
            sql.append(" WHERE n.id_usuario_destinatario = ? ");
            sql.append(" AND n.visualizada = 0 ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);

            ResultSet rs = ps.executeQuery();
            var qtd = rs.getLong(1);

            this.manager.fechaTransacao();
            this.manager.close();

            return qtd;

        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public List<Notificacao> getByUsuario(Long idUsuarioDestinatario) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" select ");
            sql.append("     n.id, ");
            sql.append("     n.descricao, ");
            sql.append("     n.data_envio, ");
            sql.append("     n.visualizada, ");
            sql.append("     n.tipo, ");
            sql.append("     u.id, ");
            sql.append("     u.nome ");
            sql.append(" from Notificacao n ");
            sql.append(" inner join Usuario u on n.id_usuario_remetente = u.id ");
            sql.append(" where n.id_usuario_destinatario = ? ");
            sql.append(" order by  n.data_envio desc; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuarioDestinatario);
            ResultSet rs = ps.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            List<Notificacao> notificacoes = new ArrayList<>();

            while (rs.next()) {
                var notificacao = new Notificacao();
                var usuario = new Usuario();
                notificacao.setId(rs.getLong(1));
                notificacao.setDescricao(rs.getString(2));
                var data = LocalDateTime.parse(rs.getString(3), formatter);
                notificacao.setDataEnvio(data);
                notificacao.setVisualizada(rs.getLong(4) == 1);
                notificacao.setTipo(TipoNotificacaoEnum.valueOf(rs.getString(5)));
                usuario.setId(rs.getLong(6));
                usuario.setNome(rs.getString(7));
                notificacao.setUsuarioRemetente(usuario);
                notificacoes.add(notificacao);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return notificacoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public void visualizar(Long idUsuario) throws Exception {
        try {
            String sql = "UPDATE Notificacao SET visualizada = 1 WHERE id_usuario_destinatario = ? AND visualizada = 0;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, idUsuario);
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao deletar");
        }
    }

}
