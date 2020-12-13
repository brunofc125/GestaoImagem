package br.ufes.gestao.imagem.dao.factory.abstracao;

public abstract class BaseAbstractDAOFactory<T> {

    protected String bancoFabricado;

    public BaseAbstractDAOFactory(String bancoFabricado) {
        if(bancoFabricado == null || bancoFabricado.isBlank()) {
            throw new RuntimeException("ERRO: Instância nula para a construção da DAO");
        }
        this.bancoFabricado = bancoFabricado;
    }
    
    public abstract T cria();

    public final boolean aceita(String banco) {
        return banco.equalsIgnoreCase(bancoFabricado);
    }
}
