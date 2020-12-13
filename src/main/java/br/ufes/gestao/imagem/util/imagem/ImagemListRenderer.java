package br.ufes.gestao.imagem.util.imagem;

import br.ufes.gestao.imagem.proxy.IProxyImage;
import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ImagemListRenderer extends DefaultListCellRenderer {

    private final Map<String, IProxyImage> imageMap;

    public ImagemListRenderer(Map<String, IProxyImage> imageMap) {
        this.imageMap = imageMap;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        try {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            //label.setText("");
            label.setIcon(imageMap.get((String) value).getImageIcon());
            return label;
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
        return null;
    }
}
