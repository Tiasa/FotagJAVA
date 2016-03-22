/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Tiasa
 */
public class RateButton extends JButton {
    ImageIcon sicon,siconFilled;
    int rate;
    boolean filled;
    RateButton(boolean filled,int rate,int width, int height) {
        super();
        this.rate = rate;
        this.filled = filled;
        sicon = new ImageIcon(getClass().getResource("star.png"));
        siconFilled = new ImageIcon(getClass().getResource("starFilled.png"));
        Image img = sicon.getImage() ;  
        Image newimg = img.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;  
        sicon = new ImageIcon( newimg );
        img = siconFilled.getImage() ;  
        newimg = img.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;  
        siconFilled = new ImageIcon( newimg );
        if (filled) {
            setIcon(siconFilled);
        } else {
            setIcon(sicon);
        }
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(width,height));
    }
    public void showFilled() {
        setIcon(siconFilled);
    }
    public void showNotFilled() {
        setIcon(sicon);
    }
    public void showNormal() {
        if (filled) {
            setIcon(siconFilled);
        } else {
            setIcon(sicon);
        }
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public int getRate() {
        return rate;
    }
}
