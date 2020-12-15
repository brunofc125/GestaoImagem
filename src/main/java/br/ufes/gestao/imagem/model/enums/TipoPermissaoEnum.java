package br.ufes.gestao.imagem.model.enums;

public enum TipoPermissaoEnum {
    VISUALIZAR("Visualizar"),
    EXCLUIR("Excluir"),
    COMPARTILHAR("Compartilhar");

    private final String descricao;

    TipoPermissaoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
