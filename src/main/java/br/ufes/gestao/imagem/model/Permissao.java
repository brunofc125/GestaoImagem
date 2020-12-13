package br.ufes.gestao.imagem.model;

import br.ufes.gestao.imagem.model.enums.TipoPermissao;

public class Permissao {
    private Usuario usuario;
    private Imagem imagem;
    private TipoPermissao tipo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public TipoPermissao getTipo() {
        return tipo;
    }

    public void setTipo(TipoPermissao tipo) {
        this.tipo = tipo;
    }
    
}
