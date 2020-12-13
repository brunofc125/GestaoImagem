package br.ufes.gestao.imagem.proxy;

import br.ufes.gestao.imagem.model.Imagem;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagemProxy implements IProxyImage {

    private Imagem imagem;

    public ImagemProxy(Imagem imagem) {
        if (imagem == null) {
            throw new RuntimeException("Instância do parâmetro nula na criação da ImagemProxy");
        }
        this.imagem = imagem;
    }

    @Override
    public ImageIcon getImageIcon() throws Exception {
        var imagemArquivo = ImageIO.read(new File(this.imagem.getCaminho()));
        var imagemEscalada = imagemArquivo.getScaledInstance(50, 50, Image.SCALE_FAST);
        return new ImageIcon(imagemEscalada);
    }

}
