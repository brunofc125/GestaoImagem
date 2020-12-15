package br.ufes.gestao.imagem.model;

import br.ufes.gestao.imagem.model.enums.TipoPermissaoEnum;

public class Permissao {
    private Usuario usuario;
    private Imagem imagem;
    private TipoPermissaoEnum tipo;

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

    public TipoPermissaoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPermissaoEnum tipo) {
        this.tipo = tipo;
    }
    
}
