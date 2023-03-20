import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JPanel centerPanel;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton button1;
    private JButton button2;

    public MyWindow(){
        this.setSize(1000,400);
        this.setTitle("Try Yourself");

        panels();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void panels(){
        this.getContentPane().setLayout(new BorderLayout());

        this.northPanel = new JPanel(new FlowLayout());
        this.northPanel.add(new JCheckBox("Katniss"));
        this.northPanel.add(new JCheckBox("Peeta"));
        this.getContentPane().add(northPanel, BorderLayout.NORTH);

        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new BoxLayout(eastPanel,BoxLayout.Y_AXIS));
        this.eastPanel.add(Box.createVerticalGlue());
        this.eastPanel.setPreferredSize(new Dimension(250,0));
        JRadioButton[] radioButtons = new JRadioButton[3];
        radioButtons[0] = new JRadioButton("OPT 1");
        radioButtons[1] = new JRadioButton("OPT 2");
        radioButtons[2] = new JRadioButton("OPT 3");

        for (JRadioButton radioButton: radioButtons){
            this.eastPanel.add(radioButton);
            this.buttonGroup.add(radioButton);
        }

        radioButtons[0].setSelected(true);
        this.eastPanel.add(Box.createVerticalGlue());
        this.getContentPane().add(eastPanel, BorderLayout.EAST);


        this.southPanel = new JPanel();
        this.southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        this.southPanel.setPreferredSize(new Dimension(0,50));
        this.button1 = new JButton("But 1");
        this.button2 = new JButton("But 2");
        this.southPanel.add(button1);
        this.southPanel.add(button2);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);


        this.centerPanel = new JPanel(new GridLayout(2,2));
        JLabel[] labels = new JLabel[4];
        for (JLabel label: labels){
            label = new JLabel(new ImageIcon("flowers.jpg"));
            this.centerPanel.add(label);
        }

        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new MyWindow();
    }
}
