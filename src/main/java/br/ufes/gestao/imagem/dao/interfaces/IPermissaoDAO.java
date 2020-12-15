package br.ufes.gestao.imagem.dao.interfaces;

import br.ufes.gestao.imagem.model.Permissao;
import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;
import java.util.List;

public interface IPermissaoDAO {

    public List<Permissao> getByUsuarioAndImagem(Long idUsuario, Long idImagem) throws Exception;

    public void insert(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario, Long idImagem) throws Exception;

    public void insertTodasImagens(List<TipoPermissaoEnum> tipoPermissoes, Long idUsuario) throws Exception;

}
