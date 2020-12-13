package br.ufes.gestao.imagem.dao.interfaces;

import br.ufes.gestao.imagem.model.Imagem;
import java.util.List;

public interface IImagemDAO {

    public List<Imagem> getAll() throws Exception;

    public Imagem getById(Long id) throws Exception;

    public void delete(Long id) throws Exception;

}
