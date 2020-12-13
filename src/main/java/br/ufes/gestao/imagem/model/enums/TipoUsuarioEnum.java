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
    
    public TipoUsuarioEnum getEnum(String cod) {
        switch(cod) {
            case "ADMIN":
                return ADMIN;
            case "USUARIO":
                return USUARIO;
            default:
                throw new RuntimeException("Não é possível converter esse tipo de usuario");
        }
    }

}
