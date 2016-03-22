/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tiasa
 */
public class ImageView extends JPanel implements Observer {
    ImageModel model;
    ImageView(ImageModel model) {
        super();
        this.model = model;
        setPreferredSize(new Dimension(300,300));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        BufferedImage img = null ;
        try {
            img= ImageIO.read(new File(model.getPath()));
        } catch(Exception e) {
            System.out.println("File not found.");
        }
         ImageIcon im = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        JButton image = new JButton(im);
        image.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame imageFrame = new JFrame(model.getName());
                try {
                    imageFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(model.getPath())).getScaledInstance(700, 700, java.awt.Image.SCALE_SMOOTH))));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                imageFrame.setPreferredSize(new Dimension(700,700));
                imageFrame.pack();
                imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                imageFrame.setVisible(true);
            }
        
        });
        JLabel imageName = new JLabel(model.getName());
        JLabel date = new JLabel(model.getCreationDate());
        RateWidget rating = new RateWidget(model,model.getRate());
        model.addObserver(rating);
        JButton clear = new JButton("Clear Filter");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating.clearFilter();
            }
        
        });
        if (model.getGridView()) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(image,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(imageName,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(date,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(rating,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(clear,c);
        } else {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        add(image,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 1;
        add(imageName,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 2;
        add(date,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 3;
        add(rating,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 4;
        add(clear,c);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
       
    }
    
}
