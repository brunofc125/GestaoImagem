package br.ufes.gestao.imagem.dao.collection;

import br.ufes.gestao.imagem.dao.factory.NotificacaoDAOSqliteFactory;
import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;

public final class NotificacaoDAOCollection extends BaseDAOCollection<INotificacaoDAO> {

    private static NotificacaoDAOCollection instancia;

    private NotificacaoDAOCollection() {
        addFactory(new NotificacaoDAOSqliteFactory());
    }

    public static NotificacaoDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new NotificacaoDAOCollection();
        }

        return instancia;
    }

}
