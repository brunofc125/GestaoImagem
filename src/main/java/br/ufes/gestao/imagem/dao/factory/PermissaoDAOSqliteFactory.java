package br.ufes.gestao.imagem.dao.factory;

import br.ufes.gestao.imagem.dao.factory.abstracao.BaseAbstractDAOFactory;
import br.ufes.gestao.imagem.dao.interfaces.IPermissaoDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.dao.sqlite.impl.PermissaoDAOSQLite;

public class PermissaoDAOSqliteFactory extends BaseAbstractDAOFactory<IPermissaoDAO> {

    public PermissaoDAOSqliteFactory() {
        super("SQLITE");
    }

    @Override
    public IPermissaoDAO cria() {
        return new PermissaoDAOSQLite(new SqliteManager());
    }

}
