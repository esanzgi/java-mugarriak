import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainWindow extends JFrame implements ActionListener{
    private JComboBox<String> comboBox;
    private JCheckBox checkBox;
    private JTextField textField;
    private JLabel imageLabel;
    private JButton save;


    private static final String rutaFolder = "images/";
    private static final String password = "damocles";

    public MainWindow(){

        this.logIn();

        setPreferredSize(new Dimension(600,400));
        this.pack();
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane.showMessageDialog(null,"Bye!");
                dispose();
            }
        });

        getContentPane().setLayout(new BorderLayout());


        JPanel northPanel = new JPanel(new GridLayout(3,1));

        this.comboBox = new JComboBox<>();
        JPanel comboPanel = new JPanel();
        comboBox.setPreferredSize(new Dimension(200,30));
        comboPanel.add(this.comboBox);
        this.load_combo();
        this.comboBox.addActionListener(new ComboListener(this));



        String rutaImage = rutaFolder + comboBox.getSelectedItem();
        this.imageLabel = new JLabel(new ImageIcon(rutaImage));
        this.imageLabel.setPreferredSize(new Dimension(200,200));

        this.checkBox = new JCheckBox("Save your comment");
        this.checkBox.setSelected(true);

        northPanel.add(comboPanel);
        northPanel.add(this.imageLabel);
        northPanel.add(this.checkBox);


        JPanel eastPanel = new JPanel(new BorderLayout());
        this.textField = new JTextField(20);

        eastPanel.add(this.textField, BorderLayout.SOUTH);
        eastPanel.setBorder(BorderFactory.createEmptyBorder(0,0,40,10));

        JPanel southPanel = new JPanel();
        this.save = new JButton("Save");
        southPanel.add(this.save);
        this.save.addActionListener(this);

        getContentPane().add(northPanel,  BorderLayout.WEST);
        getContentPane().add(eastPanel, BorderLayout.EAST);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        setVisible(true);


    }


    private void load_combo(){
        File folder = new File(rutaFolder);
        File[] listFiles = folder.listFiles();

        for (File file: listFiles){
            String fileName = file.getName();
            if(fileName.endsWith(".png")||fileName.endsWith(".jpg")){
                this.comboBox.addItem(fileName);
            }
        }
    }

    public void load_image(){
        String rutaImage = rutaFolder + comboBox.getSelectedItem();
        ImageIcon imageIcon = new ImageIcon(rutaImage);
        this.imageLabel.setIcon(imageIcon);
    }

    private void saveComment(){
        String imageName = (String) this.comboBox.getSelectedItem();
        String comment = this.textField.getText();

        if(checkBox.isSelected()){
            try(FileWriter file = new FileWriter((rutaFolder+"comments/"+imageName+".txt"),true)){
                file.write(imageName+" "+comment+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void logIn(){
        String input = JOptionPane.showInputDialog("Input password");

        if(!input.equals(password)){
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.save)){
            this.saveComment();
        }
    }


    public static void main(String[] args) {
        new MainWindow();
    }


}

