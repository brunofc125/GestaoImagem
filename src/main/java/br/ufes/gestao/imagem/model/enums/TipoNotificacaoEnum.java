package br.ufes.gestao.imagem.model.enums;

public enum TipoNotificacaoEnum {
    SOLICITACAO_VISUALIZAR("Solicitação Visualizar"),
    SOLICITACAO_EXCLUIR("Solicitação Excluir"),
    SOLICITACAO_COMPARTILHAR("Solicitação Compartilhar"),
    COMPARTILHAMENTO("Compartilhamento");
    
    private final String descricao;

    TipoNotificacaoEnum(String descricao) {
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
