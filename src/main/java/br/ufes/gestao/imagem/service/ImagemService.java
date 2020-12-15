/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.service;

import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.repository.ImagemRepository;
import java.util.List;

/**
 *
 * @author bruno
 */
public class ImagemService {

    private ImagemRepository imagemRepository;

    public ImagemService() {
        imagemRepository = new ImagemRepository();
    }

    public List<Imagem> getAll() throws Exception {
        return imagemRepository.getAll();
    }

    public void delete(Long id) throws Exception {
        imagemRepository.delete(id);
    }

    public void restaurar(Long id) throws Exception {
        imagemRepository.restaurar(id);
    }

}
