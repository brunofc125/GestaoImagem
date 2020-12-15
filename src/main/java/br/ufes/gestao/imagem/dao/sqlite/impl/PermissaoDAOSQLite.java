package br.ufes.gestao.imagem.dao.sqlite.impl;

import br.ufes.gestao.imagem.dao.interfaces.IPermissaoDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.model.Permissao;
import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissaoDAOSQLite implements IPermissaoDAO {

    private SqliteManager manager;

    public PermissaoDAOSQLite(SqliteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public List<Permissao> getByUsuarioAndImagem(Long idUsuario, Long idImagem) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" SELECT p.id_usuario, p.id_imagem, p.tipo ");
            sql.append(" FROM Permissao p ");
            sql.append(" WHERE p.id_usuario = ? AND p.id_imagem = ?; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);
            ps.setLong(2, idImagem);
            ResultSet rs = ps.executeQuery();

            List<Permissao> permissoes = new ArrayList<>();

            while (rs.next()) {
                var permissao = new Permissao();
                var imagem = new Imagem();
                var usuario = new Usuario();
                usuario.setId(rs.getLong(1));
                imagem.setId(rs.getLong(2));
                permissao.setImagem(imagem);
                permissao.setUsuario(usuario);
                permissao.setTipo(TipoPermissaoEnum.valueOf(rs.getString(3)));
                permissoes.add(permissao);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return permissoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void insert(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario, Long idImagem) throws Exception {
        try {
            var hasUnion = false;
            var count = 0;
            var sql = new StringBuilder();
            if (tipoPermissoes.size() > 0) {
                sql.append(" insert into Permissao (id_usuario, id_imagem, tipo)  ");
                if (tipoPermissoes.contains(TipoPermissaoEnum.COMPARTILHAR)) {
                    sql.append(" SELECT ?, ?, 'COMPARTILHAR' WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'COMPARTILHAR' and p2.id_usuario = ? AND p2.id_imagem = ?) ");
                    hasUnion = true;
                    count++;
                }
                if (tipoPermissoes.contains(TipoPermissaoEnum.VISUALIZAR)) {
                    if (hasUnion) {
                        sql.append(" UNION ");
                    }
                    sql.append(" SELECT ?, ?, 'VISUALIZAR' WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'VISUALIZAR' and p2.id_usuario = ? AND p2.id_imagem = ?) ");
                    hasUnion = true;
                    count++;
                }
                if (tipoPermissoes.contains(TipoPermissaoEnum.EXCLUIR)) {
                    if (hasUnion) {
                        sql.append(" UNION ");
                    }
                    sql.append(" SELECT ?, ?, 'EXCLUIR' WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'EXCLUIR' and p2.id_usuario = ? AND p2.id_imagem = ?) ");
                    count++;
                }
                sql.append("; ");
            }

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            deleteAllPermissoes(conn, idUsuario, idImagem);

            if (tipoPermissoes.size() > 0) {
                PreparedStatement ps = conn.prepareStatement(sql.toString());
                for (int i = 1; i <= count * 4; i++) {
                    ps.setLong(i, (i % 2 == 0) ? idImagem : idUsuario);
                }

                ps.executeUpdate();
            }

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
    public void insertTodasImagens(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario) throws Exception {
        try {
            var hasUnion = false;
            var count = 0;
            var sql = new StringBuilder();
            if (tipoPermissoes.size() > 0) {
                sql.append(" insert into Permissao (id_usuario, id_imagem, tipo)  ");
                if (tipoPermissoes.contains(TipoPermissaoEnum.COMPARTILHAR)) {
                    sql.append(" SELECT ?, i.id, 'COMPARTILHAR' FROM Imagem i WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'COMPARTILHAR' and p2.id_usuario = ? AND p2.id_imagem = i.id) ");
                    hasUnion = true;
                    count++;
                }
                if (tipoPermissoes.contains(TipoPermissaoEnum.VISUALIZAR)) {
                    if (hasUnion) {
                        sql.append(" UNION ");
                    }
                    sql.append(" SELECT ?, i.id, 'VISUALIZAR' FROM Imagem i WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'VISUALIZAR' and p2.id_usuario = ? AND p2.id_imagem = i.id) ");
                    hasUnion = true;
                    count++;
                }
                if (tipoPermissoes.contains(TipoPermissaoEnum.EXCLUIR)) {
                    if (hasUnion) {
                        sql.append(" UNION ");
                    }
                    sql.append(" SELECT ?, i.id, 'EXCLUIR' FROM Imagem i WHERE  ");
                    sql.append(" NOT EXISTS(select * from Permissao p2 where p2.tipo = 'EXCLUIR' and p2.id_usuario = ? AND p2.id_imagem = i.id) ");
                    count++;
                }
                sql.append("; ");
            }

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            deleteAllPermissoes(conn, idUsuario, null);

            if (tipoPermissoes.size() > 0) {
                PreparedStatement ps = conn.prepareStatement(sql.toString());
                for (int i = 1; i <= count * 2; i++) {
                    ps.setLong(i, idUsuario);
                }

                ps.executeUpdate();
            }

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    private void deleteAllPermissoes(Connection conn, Long idUsuario, Long idImagem) throws Exception {
        var sql = new StringBuilder();
        sql.append("DELETE FROM Permissao WHERE id_usuario = ? ");
        if (idImagem != null) {
            sql.append(" AND id_imagem = ? ;");
        }
        sql.append(" ;");
        PreparedStatement ps = conn.prepareStatement(sql.toString());
        ps.setLong(1, idUsuario);
        if (idImagem != null) {
            ps.setLong(2, idImagem);
        }

        ps.executeUpdate();
    }

}
