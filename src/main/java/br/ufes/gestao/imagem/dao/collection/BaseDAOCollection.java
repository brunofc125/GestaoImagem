package br.ufes.gestao.imagem.dao.collection;

import br.ufes.gestao.imagem.dao.factory.abstracao.BaseAbstractDAOFactory;
import java.util.ArrayList;
import java.util.List;

public class BaseDAOCollection<K> {

    private final List<BaseAbstractDAOFactory> factories = new ArrayList<>();

    public void addFactory(BaseAbstractDAOFactory factory) {
        if (factory == null) {
            throw new RuntimeException("Factory fornecida é inválida");
        }

        if (factories.stream().filter(f -> f.getClass().equals(factory.getClass())).findAny().isPresent()) {
            throw new RuntimeException("Esta factory já foi adicionada");
        }

        this.factories.add(factory);
    }

    public K cria(String banco) {
        if (banco == null || banco.isBlank()) {
            throw new RuntimeException("Banco fornecido é inválido");
        }

        for (var factory : factories) {
            if (factory.aceita(banco)) {
                return (K) factory.cria();
            }
        }

        return null;
    }

}
