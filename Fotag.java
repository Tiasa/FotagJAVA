/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 *
 * @author Tiasa
 */
public class Fotag {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame fotagFrame = new JFrame("Fotag!");
        ImageCollectionModel fmodel = new ImageCollectionModel();
        Toolbar tlbr = new Toolbar(fmodel);
        ImageCollectionView canvas = new ImageCollectionView(fmodel);
        JScrollPane canvasContainer = new JScrollPane(canvas);
        canvasContainer.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        fmodel.addObserver(canvas);
        fmodel.addObserver(tlbr);
        fmodel.notifyObservers();
        JPanel p = new JPanel (new BorderLayout());
        p.add(tlbr,BorderLayout.NORTH);
        p.add(canvasContainer,BorderLayout.CENTER);
        fotagFrame.getContentPane().add(p);
        fotagFrame.setPreferredSize(new Dimension(1000,1000));
	fotagFrame.pack();
	fotagFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fotagFrame.addWindowListener(new WindowAdapter(){
        @Override
        public void windowClosing(WindowEvent e) {
    int confirmed = JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to exit the program?", "Exit Program Message Box",
        JOptionPane.YES_NO_OPTION);

    if (confirmed == JOptionPane.YES_OPTION) {
        fmodel.saveData("Saved");
      fotagFrame.dispose();
    }
  }
        });
        fmodel.loadData("Saved");
        fotagFrame.setBackground(Color.WHITE);
	fotagFrame.setVisible(true);
    }
    
}
