package br.ufes.gestao.imagem.dao.sqlite.impl;

import br.ufes.gestao.imagem.dao.interfaces.IImagemDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.model.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImagemDAOSQLite implements IImagemDAO {

    private SqliteManager manager;

    public ImagemDAOSQLite(SqliteManager manager) {
        if (manager == null) {
            throw new RuntimeException("Manager fornecido é inválido");
        }

        this.manager = manager;
    }

    @Override
    public Imagem getById(Long id) throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" SELECT i.id, i.caminho, i.excluida ");
            sql.append(" FROM Imagem i ");
            sql.append(" WERE i.id = ?; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            var imagem = new Imagem();

            while (rs.next()) {
                imagem.setId(rs.getLong(1));
                imagem.setCaminho(rs.getString(2));
                imagem.setExcluida(rs.getInt(3) == 1);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return imagem;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            String sql = "UPDATE Imagem SET excluida = 1 WHERE id = ?;";

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
    public void restaurar(Long id) throws Exception {
        try {
            String sql = "UPDATE Imagem SET excluida = 0 WHERE id = ?;";

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
    public List<Imagem> getAll() throws Exception {
        try {
            var sql = new StringBuilder();
            sql.append(" SELECT i.id, i.caminho, i.excluida ");
            sql.append(" FROM Imagem i ");
            sql.append(" WHERE i.excluida = 0; ");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            List<Imagem> imagens = new ArrayList<>();

            while (rs.next()) {
                var imagem = new Imagem();
                imagem.setId(rs.getLong(1));
                imagem.setCaminho(rs.getString(2));
                imagem.setExcluida(rs.getInt(3) == 1);
                imagens.add(imagem);
            }

            this.manager.fechaTransacao();
            this.manager.close();

            return imagens;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            throw new Exception("Erro ao buscar");
        }
    }

}
