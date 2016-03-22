/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Tiasa
 */
public class Toolbar extends JPanel implements Observer{
    ImageCollectionModel model;
    @Override
    public void update(Observable o, Object arg) {
    }
    Toolbar(ImageCollectionModel model) {
        super();
        this.model = model;
        setPreferredSize(new Dimension(80,80));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ImageIcon gridIcon = new ImageIcon(getClass().getResource("grid.png"));
        JButton grid = new JButton(gridIcon);
        grid.setOpaque(false);
        grid.setContentAreaFilled(false);
        //grid.setBorderPainted(false);
        grid.setPreferredSize(new Dimension(60,60));
        grid.getModel().setPressed(true);
       
        ImageIcon listIcon = new ImageIcon(getClass().getResource("list.png"));
        JButton list = new JButton(listIcon);
        list.setOpaque(false);
        list.setContentAreaFilled(false);
        //list.setBorderPainted(false);
        list.setPreferredSize(new Dimension(60,60));
         grid.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.getModel().setPressed(true);
                list.getModel().setPressed(false);
                grid.repaint();
                list.repaint();
                model.setGridView(true);
            }
        
        });
         list.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                list.getModel().setPressed(true);
                grid.getModel().setPressed(false);
                list.repaint();
                grid.repaint();
                model.setGridView(false);
            }
         
         });
        JLabel flabel = new JLabel("Fotag!");
        flabel.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
        ImageIcon folderIcon = new ImageIcon(getClass().getResource("folder.png"));
        JButton folder = new JButton(folderIcon);
        folder.setOpaque(false);
        folder.setContentAreaFilled(false);
        folder.setBorderPainted(false);
        folder.setPreferredSize(new Dimension(50,50));
        folder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                                JFileChooser fileChooser = new JFileChooser();
                                fileChooser.setMultiSelectionEnabled(true);
                                fileChooser.setFileFilter(filter);
                                int retrival = fileChooser.showOpenDialog(null);
                                if (retrival == JFileChooser.APPROVE_OPTION) {
                                    try {                                 
                                        model.setImages(Arrays.asList(fileChooser.getSelectedFiles()));
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
			}
		});
        JLabel filterlabel = new JLabel("Filter By: ");
        filterlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        FilterWidget fw = new FilterWidget(model);
        model.addObserver(fw);
        JButton clear = new JButton("Clear Filter");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fw.clearFilter();
            }
        
        });
        add(Box.createRigidArea(new Dimension(25,0)));
        add(grid);
        add(Box.createRigidArea(new Dimension(15,0)));
        add(list);
        add(Box.createRigidArea(new Dimension(20,0)));
        add(flabel);
        add(Box.createHorizontalGlue());
        add(folder);
        add(Box.createRigidArea(new Dimension(25,0)));
        add(filterlabel);
        add(Box.createRigidArea(new Dimension(5,0)));
        add(fw);
        add(Box.createRigidArea(new Dimension(5,0)));
        add(clear);
        add(Box.createRigidArea(new Dimension(25,0)));
    }
    
}
