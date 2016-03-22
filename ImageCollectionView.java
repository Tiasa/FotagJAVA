/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Tiasa
 */
public class ImageCollectionView extends JPanel implements Observer {
    ImageCollectionModel model;
    ImageCollectionView (ImageCollectionModel model) {
        super();
        this.model = model;
        setLayout(new GridLayout(3,0));
    }
    @Override
    public void update(Observable o, Object arg) {
        List<ImageModel> images = model.getImages();
        removeAll();
        if (model.getGridView()) {
            setLayout(new GridLayout(0,3));
        } else {
            setLayout(new GridLayout(0,1));
        }
        for (ImageModel m : images) {
            m.setGridView(model.getGridView());
            ImageView image = new ImageView(m);
            m.deleteObservers();
            m.addObserver(image);
            add(image);
            
        }
        revalidate();
        repaint();
    }
    
}
