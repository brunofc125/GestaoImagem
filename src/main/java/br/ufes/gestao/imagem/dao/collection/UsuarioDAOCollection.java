package br.ufes.gestao.imagem.dao.collection;

import br.ufes.gestao.imagem.dao.factory.UsuarioDAOSqliteFactory;
import br.ufes.gestao.imagem.dao.interfaces.IUsuarioDAO;

public final class UsuarioDAOCollection extends BaseDAOCollection<IUsuarioDAO> {

    private static UsuarioDAOCollection instancia;

    private UsuarioDAOCollection() {
        addFactory(new UsuarioDAOSqliteFactory());
    }

    public static UsuarioDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAOCollection();
        }

        return instancia;
    }

}
