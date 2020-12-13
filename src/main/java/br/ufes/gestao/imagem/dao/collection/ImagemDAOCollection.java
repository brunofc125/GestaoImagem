package br.ufes.gestao.imagem.dao.collection;

import br.ufes.gestao.imagem.dao.factory.ImagemDAOSqliteFactory;
import br.ufes.gestao.imagem.dao.interfaces.IImagemDAO;

public final class ImagemDAOCollection extends BaseDAOCollection<IImagemDAO> {

    private static ImagemDAOCollection instancia;

    private ImagemDAOCollection() {
        addFactory(new ImagemDAOSqliteFactory());

    }

    public static ImagemDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new ImagemDAOCollection();
        }

        return instancia;
    }

}
