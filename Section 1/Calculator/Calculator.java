import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame mainframe;
    // Mainframe
    private JTextField myNumber1;
    private JTextField myNumber2;
    // blocks in line 1

    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private static final int COL = 5;
    private static final int ROW = 2;
    // Can you stop telling me these are magic number?!


    public Calculator () {
        prepareGUI(); 
        // Construct GUI
    } 
    // Constructor

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.showGUI();
    } 
    // Construct instance

    private void showGUI() {
      mainframe.setVisible(true);
    }
    // Show the window

    private void prepareGUI() {
        JLabel myEquation;
        JLabel myResult;
        JLabel myOperator;
        // Editable components in line 1

        JButton myAdd;
        JButton myMinus;
        JButton myTimes;
        JButton myDiv;
        JButton myOK;
        // Const blocks in line 2

        mainframe = new JFrame("Calculator");
        mainframe.setSize(WIDTH, HEIGHT);
        mainframe.setLayout(new GridLayout(ROW, COL));
        // Set size and layout of mainframe

        myNumber1 = new JTextField();
        myNumber1.setHorizontalAlignment(JTextField.CENTER);
        myNumber2 = new JTextField();
        myNumber2.setHorizontalAlignment(JTextField.CENTER);
        // Set alignment of two JTextField
        
        myOperator = new JLabel("", JLabel.CENTER);
        myEquation = new JLabel("=", JLabel.CENTER);
        myResult = new JLabel("", JLabel.CENTER);
        // Set content and alignment of JLabels

        myAdd = new JButton("+");
        myMinus = new JButton("-");
        myTimes = new JButton("*");
        myDiv = new JButton("/");
        myOK = new JButton("OK");
        // Set content of JButtons

        myAdd.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("+");
          }
        });
        myMinus.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("-");  
          }
        });
        myTimes.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("*");
          }
        });
        myDiv.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            myOperator.setText("/");
          }
        });
        myOK.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {     
            operations();     
          }
        });
        // Bind Listeners to JButtons

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // Set close operation

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
        // Add blocks into the Grid
    }

    public final void operations() {
      Double number1 = 0.0, number2 = 0.0, result; 
      // Declare and initialize doubles
      try {
          number1 = Double.parseDouble(myNumber1.getText());
          number2 = Double.parseDouble(myNumber2.getText());
      } catch(NumberFormatException e) {
        myResult.setText("Invalid Format!");
        return;
      } 
      // Parse numbers in the JTextFields. If not valid format, sent messages.

      switch(myOperator.getText()) {
        case "+":
          result = number1 + number2;
          myResult.setText(result.toString());
          break;
        case "-":
          result = number1 - number2;
          myResult.setText(result.toString());
          break;
        case "*":
          result = number1 * number2;
          myResult.setText(result.toString());
          break;
        case "/":
          if (number2 == 0){
            myResult.setText("Divided by 0!");
          }
          else {
            result = number1 / number2;
            myResult.setText(result.toString());
          }
          break;
        default:
          myResult.setText("No operator!");
          break;
      }
      // Do operations due to the operator
    }
}