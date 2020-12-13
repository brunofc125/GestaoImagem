package br.ufes.gestao.imagem.dao.factory;

import br.ufes.gestao.imagem.dao.factory.abstracao.BaseAbstractDAOFactory;
import br.ufes.gestao.imagem.dao.interfaces.IUsuarioDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.dao.sqlite.impl.UsuarioDAOSQLite;

public class UsuarioDAOSqliteFactory extends BaseAbstractDAOFactory<IUsuarioDAO> {

    public UsuarioDAOSqliteFactory() {
        super("SQLITE");
    }

    @Override
    public IUsuarioDAO cria() {
        return new UsuarioDAOSQLite(new SqliteManager());
    }

}
