package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.PermissaoDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.IPermissaoDAO;
import br.ufes.gestao.imagem.model.Permissao;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import java.util.List;

public class PermissaoRepository {

    private IPermissaoDAO permissaoDAO;

    public PermissaoRepository() {
        this.permissaoDAO = PermissaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }

    public List<Permissao> getByUsuarioAndImagem(Long idUsuario, Long idImagem) throws Exception {
        return permissaoDAO.getByUsuarioAndImagem(idUsuario, idImagem);
    }

    public void insert(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Usuário não informado");
        }
        if (idImagem == null) {
            throw new Exception("Imagem não informado");
        }
        permissaoDAO.insert(tipoPermissoes, idUsuario, idImagem);
    }

    public void insertTodasImagens(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Usuário não informado");
        }
        permissaoDAO.insertTodasImagens(tipoPermissoes, idUsuario);
    }

}
