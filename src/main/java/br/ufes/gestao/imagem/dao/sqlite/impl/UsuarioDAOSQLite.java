package br.ufes.gestao.imagem.dao.sqlite.impl;

import br.ufes.gestao.imagem.dao.interfaces.IUsuarioDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.model.Usuario;
import br.ufes.gestao.imagem.model.enums.TipoUsuarioEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAOSQLite implements IUsuarioDAO {

    private SqliteManager manager;

    public UsuarioDAOSQLite(SqliteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append("INSERT INTO Usuario");
            sql.append("(nome, login, senha, tipo, excluido)");
            sql.append(" VALUES(?, ?, ?, ?, 0);");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo().name());

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
    public void update(Usuario usuario) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" UPDATE Usuario SET ");
            sql.append("  nome = ?, ");
            sql.append("  senha = ? ");
            sql.append(" WERE id = ?; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setLong(2, usuario.getId());

            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao atualizar");
        }
    }

    @Override
    public Usuario getById(Long id) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" SELECT u.id, u.nome, u.login, u.tipo, u.excluido ");
            sql.append(" FROM Usuario u ");
            sql.append(" WERE id = ?; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setLogin(rs.getString(3));
                usuario.setTipo(TipoUsuarioEnum.valueOf(rs.getString(4)));
                usuario.setExcluido(rs.getInt(5) == 1);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Usuario getByLogin(String login, String senha) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" SELECT u.id, u.nome, u.login, u.tipo, u.excluido ");
            sql.append(" FROM Usuario u ");
            sql.append(" WHERE login = ? AND senha = ? AND excluido = 0; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setLogin(rs.getString(3));
                usuario.setTipo(TipoUsuarioEnum.valueOf(rs.getString(4)));
                usuario.setExcluido(rs.getInt(5) == 1);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            String sql = "UPDATE Usuario SET excluido = 1 WHERE id = ?;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao deletar");
        }
    }

    @Override
    public boolean loginExists(String login) throws Exception {
        try {
            String sql = "SELECT id FROM Usuario WHERE login = ?;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            this.manager.fechaTransacao();
            this.manager.close();

            return rs.next();
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

}
