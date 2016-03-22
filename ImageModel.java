/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Observable;

/**
 *
 * @author Tiasa
 */
public class ImageModel extends Observable{
    String path;
    String name;
    String creationDate;
    int rate;
    boolean gridView;
    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void setCreationDate(String date) {
        this.creationDate = date;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setRate(int rate) {
        this.rate = rate;
        setChanged();
        notifyObservers(rate);
    }
    public int getRate() {
        return rate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setGridView(boolean g) {
        this.gridView = g;
    }
    public boolean getGridView() {
        return gridView;
    }
}
