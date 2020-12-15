package br.ufes.gestao.imagem.dao.factory;

import br.ufes.gestao.imagem.dao.factory.abstracao.BaseAbstractDAOFactory;
import br.ufes.gestao.imagem.dao.interfaces.INotificacaoDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.dao.sqlite.impl.NotificacaoDAOSQLite;

public class NotificacaoDAOSqliteFactory extends BaseAbstractDAOFactory<INotificacaoDAO> {

    public NotificacaoDAOSqliteFactory() {
        super("SQLITE");
    }

    @Override
    public INotificacaoDAO cria() {
        return new NotificacaoDAOSQLite(new SqliteManager());
    }

}
