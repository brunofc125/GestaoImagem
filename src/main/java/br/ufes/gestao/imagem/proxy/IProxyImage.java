/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.proxy;

import br.ufes.gestao.imagem.model.Imagem;
import javax.swing.ImageIcon;

/**
 *
 * @author bruno
 */
public interface IProxyImage {
    public ImageIcon getImageIcon() throws Exception;
    public Imagem getImage();
}
