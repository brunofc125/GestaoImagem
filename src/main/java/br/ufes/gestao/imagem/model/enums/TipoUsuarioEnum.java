package br.ufes.gestao.imagem.model.enums;

public enum TipoUsuarioEnum {
    ADMIN( "Administrador" ), USUARIO( "Usuário" );
    
    private final String descricao;

    TipoUsuarioEnum(String descricao) {
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
