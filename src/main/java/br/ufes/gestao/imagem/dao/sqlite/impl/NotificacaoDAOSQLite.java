package br.ufes.gestao.imagem.dao.sqlite.impl;

import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.model.enums.TipoNotificacaoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificacaoDAOSQLite implements INotificacaoDAO {

    private SqliteManager manager;

    public NotificacaoDAOSQLite(SqliteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public void insertSolicitação(TipoNotificacaoEnum tipoPermissao, Long idUsuario, Long idImagem) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" insert into Notificacao (descricao, data_envio, id_usuario_remetente, id_usuario_destinatario, visualizada, tipo, id_imagem) ");
            sql.append(" select ?, ?, ?, admin.id, 0, ?, ? FROM Usuario admin where admin.tipo = 'ADMIN'; ");
            if (idImagem != null) {
                sql.append(" select ?, ?, ?, admin.id, 0, ?, ? FROM Usuario admin where admin.tipo = 'ADMIN'; ");
            } else {
                sql.append(" select ?, ?, ?, admin.id, 0, ?, null FROM Usuario admin where admin.tipo = 'ADMIN'; ");

            }
            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, "Solicitação");
            ps.setString(2, LocalDateTime.now().format(formatter));
            ps.setLong(3, idUsuario);

            ps.setString(4, tipoPermissao.name());
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

}
