package br.ufes.gestao.imagem.model.enums;

public enum TipoPermissao {
    VISUALIZAR("Visualizar"),
    EXCLUIR("Excluir"),
    COMPARTILHAR("Compartilhar");

    private final String descricao;

    TipoPermissao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoPermissao getEnum(String cod) {
        switch (cod) {
            case "ADMIN":
                return VISUALIZAR;
            case "USUARIO":
                return EXCLUIR;
            case "COMPARTILHAR":
                return COMPARTILHAR;
            default:
                throw new RuntimeException("Não é possível converter esse tipo de usuario");
        }
    }

}
