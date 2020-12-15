package br.ufes.gestao.imagem.model;

import br.ufes.gestao.imagem.memento.ImagemMemento;
import br.ufes.gestao.imagem.proxy.IProxyImage;
import javax.swing.ImageIcon;

public class Imagem implements IProxyImage {

    private Long id;
    private String caminho;
    private boolean excluida;

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

    @Override
    public ImageIcon getImageIcon() throws Exception {
        return new ImageIcon(this.caminho);
    }

    @Override
    public Imagem getImage() {
        return this;
    }

    public ImagemMemento getMemento() {
        return new ImagemMemento(id, caminho, excluida);
    }

    public void restauraMemento(ImagemMemento imagemMemento) {
        this.id = imagemMemento.getId();
        this.caminho = imagemMemento.getCaminho();
        this.excluida = imagemMemento.isExcluida();
    }

}
