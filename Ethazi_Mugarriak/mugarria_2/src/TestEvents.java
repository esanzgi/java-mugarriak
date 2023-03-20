
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.text.NavigationFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

import static javax.swing.ScrollPaneConstants.*;

public class TestEvents extends JFrame implements ActionListener {
    private JComboBox<String> comboBox;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JButton clear;
    private JButton close;
    private JTextArea textArea;
    private JPanel textPanel;

    public TestEvents(){
        this.setTitle("Tests Events: FIles");
        this.setSize(800,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());

        String[] fileList = {"python.txt", "c.txt", "java.txt"};
        this.comboBox = new JComboBox<>(fileList);
        this.comboBox.setPreferredSize(new Dimension(250, this.comboBox.getPreferredSize().height));
        this.comboBox.addActionListener(this);

        this.clear = new JButton("Clear");
        this.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        }); //laburtuta: this.clear.addActionListener(e -> textArea.setText(""));

        this.westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.westPanel.setPreferredSize(new Dimension(400,0));
        this.westPanel.add(this.comboBox);
        this.westPanel.add(this.clear);
        this.getContentPane().add(this.westPanel, BorderLayout.WEST);


        this.eastPanel = new JPanel(new BorderLayout());


        this.textArea = new JTextArea();
        this.textArea.setPreferredSize(null);
        this.textArea.setEditable(false);
        this.textArea.setFont(new Font("Arial",Font.ITALIC, 14));
        this.textArea.setLineWrap(true); //Dena lerro batean ez idazteko eta panelaren tamainatik ez ateratzeko testua
        this.textArea.setWrapStyleWord(true); //Hitzak ez banatzeko lerro saltoa egitean

        JScrollPane scrollPane = new JScrollPane(this.textArea);
        scrollPane.setPreferredSize(new Dimension(370, 310));
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

        this.close = new JButton("Close");
        this.close.setSize(100,50);

        this.close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastPanel.setVisible(false);
            }
        });

        this.eastPanel.add(scrollPane, BorderLayout.NORTH);
        this.eastPanel.add(this.close, BorderLayout.SOUTH);
        this.getContentPane().add(this.eastPanel, BorderLayout.EAST);

        this.pack();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource().equals(this.comboBox)){
            String fileName = (String) this.comboBox.getSelectedItem();
            this.eastPanel.setVisible(true);
            this.loadFile(fileName);
        }
    }

    private void loadFile(String selectedFile){
        try(FileReader fr = new FileReader("files/"+selectedFile);
            BufferedReader br = new BufferedReader(fr)){
            this.textArea.setText("");
            String text;
            while((text=br.readLine())!=null){
                this.textArea.append(text);
            }
            this.textArea.setCaretPosition(0); //Scrollbar goitik hasteko

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this,"File Not Found", "ERROR!",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        TestEvents testEvents = new TestEvents();
        testEvents.setVisible(true);

    }

}
