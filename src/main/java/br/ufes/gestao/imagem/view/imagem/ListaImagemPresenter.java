/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.gestao.imagem.view.imagem;

import br.ufes.gestao.imagem.model.Imagem;
import br.ufes.gestao.imagem.proxy.IProxyImage;
import br.ufes.gestao.imagem.proxy.ImagemProxy;
import br.ufes.gestao.imagem.service.ImagemService;
import br.ufes.gestao.imagem.util.imagem.ImagemListRenderer;
import br.ufes.gestao.imagem.view.imagem.state.ListaImagemPermissaoPresenter;
import br.ufes.gestao.imagem.view.imagem.state.ListaImagemPresenterState;
import br.ufes.gestao.imagem.view.imagem.state.ListaImagemVisualizarPresenter;
import br.ufes.gestao.imagem.view.presenter.BaseInternalFramePresenter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;

/**
 *
 * @author bruno
 */
public class ListaImagemPresenter extends BaseInternalFramePresenter<ListaImagemView> {

    private Map<String, IProxyImage> imageMap;
    private ImagemService imagemService;
    private ListaImagemPresenterState state;

    public ListaImagemPresenter(JDesktopPane container, Long idUsuario, boolean isVisualizar) {
        super(container, new ListaImagemView());
        this.imagemService = new ImagemService();
        var view = getView();

        if(isVisualizar) {
            this.setState(new ListaImagemVisualizarPresenter(this, idUsuario));
        } else {
            this.setState(new ListaImagemPermissaoPresenter(this, idUsuario));
        }

        this.criarLista();
        view.setVisible(true);
    }

    public void criarLista() {
        try {
            List<Imagem> listImagem = imagemService.getAll();
            if (listImagem != null && listImagem.size() > 0) {
                String[] listCaminho = getCaminhos(listImagem);
                imageMap = createImageMap(listImagem);
                var list = getView().getListImagem();
                list.setListData(listCaminho);
                list.setCellRenderer(new ImagemListRenderer(imageMap));
            }
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }

    }
    
    public IProxyImage getImagemSelecionada() {
        var caminho = getView().getListImagem().getSelectedValue();
        if(caminho != null) {
            return this.imageMap.get(caminho);
        }
        return null;
    }

    private Map<String, IProxyImage> createImageMap(List<Imagem> list) {
        Map<String, IProxyImage> map = new HashMap<>();
        list.forEach(img -> {
            map.put(img.getCaminho(), new ImagemProxy(img));
        });
        return map;
    }

    private String[] getCaminhos(List<Imagem> imagens) {
        String[] caminhos = new String[imagens.size()];
        int i = 0;
        for (var img : imagens) {
            caminhos[i] = img.getCaminho();
            i++;
        }
        return caminhos;
    }

    public void setState(ListaImagemPresenterState state) {
        this.state = state;
    }
    
    public boolean isTodas() {
        return getView().getChkTodasImagens().isSelected();
    }

}
