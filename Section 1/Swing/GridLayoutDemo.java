import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo {
    private JFrame mainframe;
    private JPanel mainpanel;

    private JTextField myNumber1;
    private JLabel myOperator;
    private JTextField myNumber2;
    private JLabel myEquation;
    private JLabel myResult;

    private JButton myAdd;
    private JButton myMinus;
    private JButton myTimes;
    private JButton myDiv;
    private JButton myOK;

    public GridLayoutDemo () {
        prepareGUI();
    }

    public static void main(String[] args) {
        GridLayoutDemo gridlayoutdemo = new GridLayoutDemo();
    }

    private void prepareGUI() {
        mainframe = new JFrame("Calculator");
        mainframe.setSize(500, 200);
        mainframe.setLayout(new GridLayout(2, 5));

        myNumber1 = new JTextField();
        myNumber1.setHorizontalAlignment(JTextField.CENTER);
        myNumber2 = new JTextField();
        myNumber2.setHorizontalAlignment(JTextField.CENTER);
        
        myOperator = new JLabel();
        myEquation = new JLabel("=", JLabel.CENTER);
        myResult = new JLabel();

        myAdd = new JButton("+");
        myMinus = new JButton("-");
        myTimes = new JButton("*");
        myDiv = new JButton("/");
        myOK = new JButton("OK");

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        mainframe.add(myNumber1);
        mainframe.add(myOperator);
        mainframe.add(myNumber2);
        mainframe.add(myEquation);
        mainframe.add(myResult);
        mainframe.add(myAdd);
        mainframe.add(myMinus);
        mainframe.add(myTimes);
        mainframe.add(myDiv);
        mainframe.add(myOK);
        mainframe.setVisible(true);
    }
}