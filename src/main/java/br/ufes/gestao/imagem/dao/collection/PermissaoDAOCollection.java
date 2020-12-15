package br.ufes.gestao.imagem.dao.collection;

import br.ufes.gestao.imagem.dao.factory.PermissaoDAOSqliteFactory;
import br.ufes.gestao.imagem.dao.interfaces.IPermissaoDAO;

public final class PermissaoDAOCollection extends BaseDAOCollection<IPermissaoDAO> {

    private static PermissaoDAOCollection instancia;

    private PermissaoDAOCollection() {
        addFactory(new PermissaoDAOSqliteFactory());
    }

    public static PermissaoDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new PermissaoDAOCollection();
        }

        return instancia;
    }

}
