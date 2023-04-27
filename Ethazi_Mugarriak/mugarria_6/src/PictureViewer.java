import model.Photographer;
import model.Picture;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class PictureViewer extends JFrame {
    private JComboBox<Photographer> photographerComboBox;
    private JXDatePicker datePicker;
    private JList<Picture> pictureList;
    private JLabel pictureLabel;


    public PictureViewer(){
        setTitle("Photography");
        setLayout(new GridLayout(2,2));
        setSize(800,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        photographerComboBox = new JComboBox<>();
        datePicker = new JXDatePicker();
        pictureList = new JList<>();
        pictureLabel = new JLabel();


        JPanel photographerPanel = new JPanel();
        photographerComboBox.setPreferredSize(new Dimension(150,25));
        photographerPanel.add(new JLabel("Photographer: "));
        photographerPanel.add(photographerComboBox);


        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Photos after: "));
        datePanel.add(datePicker);


        JScrollPane pictureListPanel = new JScrollPane(pictureList);
        pictureListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel picturePanel = new JPanel();



        add(photographerPanel);
        add(datePanel);
        add(pictureListPanel);


        pack();
        setVisible(true);
    }

    private void photographersComboBox(){
        JComboBox<Photographer> comboBox = new JComboBox<>();
    }

    /*
    public List<String> getPhotographers(){
        try {

        }catch (){

        }
    }

     */

    public static void main(String[] args) {
        new PictureViewer();
    }
}