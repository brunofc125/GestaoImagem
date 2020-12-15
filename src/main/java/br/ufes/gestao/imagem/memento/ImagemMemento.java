/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.memento;

/**
 *
 * @author bruno
 */
public class ImagemMemento {

    private Long id;
    private String caminho;
    private boolean excluida;

    public ImagemMemento(Long id, String caminho, boolean excluida) {
        this.id = id;
        this.caminho = caminho;
        this.excluida = excluida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public boolean isExcluida() {
        return excluida;
    }

    public void setExcluida(boolean excluida) {
        this.excluida = excluida;
    }
}
