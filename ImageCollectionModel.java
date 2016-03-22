/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

/**
 *
 * @author Tiasa
 */
public class ImageCollectionModel extends Observable {
    private List<ImageModel> images=null;
    int rate;
    boolean gridView;
    ImageCollectionModel() {
        images = new ArrayList<ImageModel>();
        rate=0;
        gridView = true;
    }
    public void setImages(List<File> files) {
        for ( File image : files ) {
            Path p = Paths.get(image.getAbsolutePath());
            try {
                BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                ImageModel im = new ImageModel();
                im.setPath(image.getAbsolutePath());
                im.setName(image.getName());
                im.setCreationDate(df.format(attr.creationTime().toMillis()));
                im.setRate(0);
                im.setGridView(true);
                images.add(im);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        setChanged();
        notifyObservers();
    }
    public List<ImageModel> getImages() {
        if (rate==0) return images;
        List<ImageModel> filtered = images.stream().filter(p -> p.getRate()==rate).collect(Collectors.toList());
        return filtered;
    }
    public void setRate(int rate) {
        this.rate = rate;
        setChanged();
        notifyObservers(rate);
    }
    public boolean getGridView() {
        return gridView;
    }
    public void setGridView(boolean gridView) {
        this.gridView = gridView;
        setChanged();
        notifyObservers();
    }
    public void saveData(String file)
{
    try {
     XMLEncoder e = new XMLEncoder(
                          new BufferedOutputStream(
                              new FileOutputStream(file)));
       e.writeObject(images);
       e.close();
    } catch (Exception e) {
        System.out.println("not working");
    }
}
    public void loadData(String file) {
        try {
        XMLDecoder d = new XMLDecoder(
                          new BufferedInputStream(
                              new FileInputStream(file)));
       images = (List<ImageModel>) d.readObject();
       d.close();      
       setChanged();
       notifyObservers();
       
        } catch (Exception e) {
            System.out.println("no saved data");
        }
    }
}
