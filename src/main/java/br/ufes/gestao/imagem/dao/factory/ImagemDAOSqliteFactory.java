package br.ufes.gestao.imagem.dao.factory;

import br.ufes.gestao.imagem.dao.factory.abstracao.BaseAbstractDAOFactory;
import br.ufes.gestao.imagem.dao.interfaces.IImagemDAO;
import br.ufes.gestao.imagem.dao.manager.SqliteManager;
import br.ufes.gestao.imagem.dao.sqlite.impl.ImagemDAOSQLite;

public class ImagemDAOSqliteFactory extends BaseAbstractDAOFactory<IImagemDAO> {

    public ImagemDAOSqliteFactory() {
        super("SQLITE");
    }

    @Override
    public IImagemDAO cria() {
        return new ImagemDAOSQLite(new SqliteManager());
    }

}
