/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.repository;

import br.ufes.gestao.imagem.dao.collection.ImagemDAOCollection;
import br.ufes.gestao.imagem.dao.interfaces.IImagemDAO;
import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.model.Permissao;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ImagemRepository {

    private IImagemDAO imagemDAO;

    public ImagemRepository() {
        this.imagemDAO = ImagemDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }

    public List<Imagem> getAll() throws Exception {
        return imagemDAO.getAll();
    }

}
