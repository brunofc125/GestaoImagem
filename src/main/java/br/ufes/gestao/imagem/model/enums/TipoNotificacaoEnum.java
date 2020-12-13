package br.ufes.gestao.imagem.model.enums;

public enum TipoNotificacaoEnum {
    TESTE( "Administrador" );
    
    private final String descricao;

    TipoNotificacaoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public TipoNotificacaoEnum getEnum(String cod) {
        switch(cod) {
            case "TESTE":
                return TESTE;
            default:
                throw new RuntimeException("Não é possível converter esse tipo de notificacao");
        }
    }

}
