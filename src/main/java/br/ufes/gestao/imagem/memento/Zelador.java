/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public class Zelador {

    private List<ImagemMemento> historico;

    public Zelador() {
        historico = new ArrayList<>();
    }

    public ImagemMemento getUltimo() {
        if (historico.isEmpty()) {
            return null;
        }
        return historico.remove(historico.size() - 1);
    }

    public boolean temRegistro() {
        return !this.historico.isEmpty();
    }

    public void addImagemMemento(ImagemMemento imagemMemento) {
        this.historico.add(imagemMemento);
    }

}
