/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Tiasa
 */
public class FilterWidget extends JPanel implements Observer {
    ImageCollectionModel model;
    RateButton rate1,rate2,rate3,rate4,rate5;
    FilterWidget(ImageCollectionModel model) {
        super();
        this.model = model;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        rate1 = new RateButton(false,1,20,20);
        rate2 = new RateButton(false,2,20,20);
        rate3 = new RateButton(false,3,20,20);
        rate4 = new RateButton(false,4,20,20);
        rate5 = new RateButton(false,5,20,20);
        rate1.addMouseListener(new RateController());
        rate2.addMouseListener(new RateController());
        rate3.addMouseListener(new RateController());
        rate4.addMouseListener(new RateController());
        rate5.addMouseListener(new RateController());
        add(rate1);
        add(rate2);
        add(rate3);
        add(rate4);
        add(rate5);
    }
    private RateButton getButton(int rate) {
        switch (rate) {
            case 1:
                return rate1;
            case 2:
                return rate2;
            case 3:
                return rate3;
            case 4:
                return rate4;
            case 5:
                return rate5;
            default:
                return null;
        }
    }
    public void clearFilter() {
        for (int i = 1; i<=5; i++) {
            getButton(i).setFilled(false);
            getButton(i).showNormal();
        }
        model.setRate(0);
    }
    private class RateController extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent evt)
            {
                RateButton b = (RateButton)evt.getComponent();
                int rate = b.getRate();
                for (int i = 1; i<=rate; i++) {
                    getButton(i).showFilled();
                }
                for (int i = rate+1; i<=5; i++) {
                    getButton(i).showNotFilled();
                }
            }
            public void mouseExited(MouseEvent evt)
            {
                for (int i = 1; i<=5; i++) {
                    getButton(i).showNormal();
                }
            }
            public void mouseClicked(MouseEvent evt) {
                RateButton b = (RateButton)evt.getComponent();
                int rate = b.getRate();
                model.setRate(rate);
            }
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg!=null) {
        int rate = (int) arg;
        for (int i = 1; i<=rate; i++) {
            getButton(i).setFilled(true);
        }
        for (int i = rate+1; i<=5; i++) {
            getButton(i).setFilled(false);
        }
        }
    }
    
}
