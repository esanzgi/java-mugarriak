import dbconnection.DBCrud;
import dbconnection.DBHelper;
import model.Picture;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class PictureViewer extends JFrame {
    private JComboBox<String> photographerComboBox;
    private JXDatePicker datePicker;
    private JList<String> pictureList;
    private ImageIcon pictureImage;
    private  JLabel imageLabel;
    List<Picture> pictures;


    public PictureViewer(){
        setTitle("Photography");
        setLayout(new GridLayout(2,2));
        setSize(800,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        photographerComboBox = new JComboBox<>();
        datePicker = new JXDatePicker();
        pictureList = new JList<>();
        imageLabel = new JLabel();



        JPanel photographerPanel = new JPanel();
        photographerComboBox.setPreferredSize(new Dimension(150,25));
        photographerComboBox.addItem(null);
        photographerComboBox.addActionListener(e -> loadPictureList((String) photographerComboBox.getSelectedItem()));
        this.loadPhotographersComboBox();

        photographerPanel.add(new JLabel("Photographer: "));
        photographerPanel.add(photographerComboBox);


        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Photos after: "));
        datePicker.addActionListener(e -> loadPictureList((String) photographerComboBox.getSelectedItem()));
        datePanel.add(datePicker);


        JScrollPane pictureListPanel = new JScrollPane(pictureList);
        pictureListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pictureList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() ==  2){
                    for(Picture p: pictures){
                        if(pictureList.getSelectedValue().equals(p.getTitle())){
                            loadPictureImage(p.getFile());
                            new DBCrud(DBHelper.getConnection()).updateVisits(p.getPictureId());
                        }
                    }
                }
            }
        });

        JPanel picturePanel = new JPanel();
        picturePanel.add(imageLabel);


        add(photographerPanel);
        add(datePanel);
        add(pictureListPanel);
        add(picturePanel);

        pack();
        setVisible(true);
    }

    private void loadPhotographersComboBox(){
        DBCrud c = new DBCrud(DBHelper.getConnection());
        List<String> photographerNames = c.getPhotographerNames();

        for (String p: photographerNames){
            photographerComboBox.addItem(p);
        }
    }

    private void loadPictureList(String photographerName){
        DBCrud c = new DBCrud(DBHelper.getConnection());

        DefaultListModel<String> pictureTitles = new DefaultListModel<>();

        if(datePicker.getDate() != null){
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String newDate = formatDate.format(datePicker.getDate());

            pictures = c.getPicuresByPhotographerAndDate(photographerName, newDate);
        }else{
            pictures = c.getPicuresByPhotographer(photographerName);
        }

        for (Picture p: pictures){
            pictureTitles.addElement(p.getTitle());
        }
        this.pictureList.setModel(pictureTitles);
    }

    private void loadPictureImage(String imagePath){
        pictureImage = new ImageIcon("src/"+imagePath);
        Image image = pictureImage.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        pictureImage = new ImageIcon(image);
        imageLabel.setIcon(pictureImage);
    }

    public static void main(String[] args) {
        new PictureViewer();

    }
}
