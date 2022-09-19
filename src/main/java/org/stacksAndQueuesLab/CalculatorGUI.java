package org.stacksAndQueuesLab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// You only need to edit the designated parts of the actionPerformed() method
// You should leave the rest of the code as is

public class CalculatorGUI implements ActionListener {
  JFrame frame;
  JPanel panel1, panel2, panel3, panel4, panel5;
  JButton button1,
      button2,
      button3,
      button4,
      button5,
      button6,
      button7,
      button8,
      button9,
      button0,
      buttonDivide,
      buttonTimes,
      buttonMinus,
      buttonPlus,
      buttonClear,
      buttonEquals,
      buttonLeft,
      buttonRight,
      buttonExponent;
  JTextField field1;

  public CalculatorGUI() {
    // 1. Create the frame (the window that will pop up)
    frame = new JFrame("Calculator");
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    // 2. Choose what happens when you click the exit button
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 3. Create components and put them in the frame

    field1 = new JTextField(15);
    field1.setHorizontalAlignment(SwingConstants.RIGHT);

    panel1 = new JPanel();
    button7 = new JButton("7");
    panel1.add(button7);
    button8 = new JButton("8");
    panel1.add(button8);
    button9 = new JButton("9");
    panel1.add(button9);
    buttonDivide = new JButton("/");
    panel1.add(buttonDivide);

    panel2 = new JPanel();
    button4 = new JButton("4");
    panel2.add(button4);
    button5 = new JButton("5");
    panel2.add(button5);
    button6 = new JButton("6");
    panel2.add(button6);
    buttonTimes = new JButton("*");
    panel2.add(buttonTimes);

    panel3 = new JPanel();
    button1 = new JButton("1");
    panel3.add(button1);
    button2 = new JButton("2");
    panel3.add(button2);
    button3 = new JButton("3");
    panel3.add(button3);
    buttonMinus = new JButton("-");
    panel3.add(buttonMinus);

    panel4 = new JPanel();
    button0 = new JButton("0");
    panel4.add(button0);
    buttonClear = new JButton("AC");
    panel4.add(buttonClear);
    buttonEquals = new JButton("=");
    panel4.add(buttonEquals);
    buttonPlus = new JButton("+");
    panel4.add(buttonPlus);

    panel5 = new JPanel();
    buttonLeft = new JButton("(");
    panel5.add(buttonLeft);
    buttonRight = new JButton(")");
    panel5.add(buttonRight);
    buttonExponent = new JButton("^");
    panel5.add(buttonExponent);

    // Add implemented actionListener method to each button
    button1.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    button5.addActionListener(this);
    button6.addActionListener(this);
    button7.addActionListener(this);
    button8.addActionListener(this);
    button9.addActionListener(this);
    button0.addActionListener(this);

    buttonPlus.addActionListener(this);
    buttonMinus.addActionListener(this);
    buttonTimes.addActionListener(this);
    buttonDivide.addActionListener(this);
    buttonEquals.addActionListener(this);
    buttonLeft.addActionListener(this);
    buttonRight.addActionListener(this);
    buttonExponent.addActionListener(this);
    buttonClear.addActionListener(this);

    frame.add(field1);
    frame.add(panel1);
    frame.add(panel2);
    frame.add(panel3);
    frame.add(panel4);
    frame.add(panel5);

    // 4. Size the frame
    frame.pack();

    // 5. Show the frame
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    CalculatorGUI x = new CalculatorGUI();
  }

  public void actionPerformed(ActionEvent ae) {
    // buttonName will be equal to the String that labels
    // whatever button was pressed
    String buttonName = ae.getActionCommand();

    // Number buttons
    // This is just a shorthand for:
    // if (buttonName.equals("1") || buttonName.equals("2") || ...
    if ("1234567890".contains(buttonName)) {
      field1.setText(field1.getText() + buttonName);
    }
    // Clear button
    else if (buttonName.equals("AC")) {
      field1.setText("");
    }
    // Left parens
    else if (buttonName.equals("(")) {
      field1.setText(field1.getText() + "( ");
    }
    // Right parens
    else if (buttonName.equals(")")) {
      field1.setText(field1.getText() + " )");
    }
    // Plus button
    else if (buttonName.equals("+")) {
      field1.setText(field1.getText() + " + ");
    }
    // **************************************
    // Add code here for the other operations: -, *, /, ^
    // **************************************
    else if (buttonName.equals("-")) {
      field1.setText(field1.getText() + " - ");
    } else if (buttonName.equals("*")) {
      field1.setText(field1.getText() + " * ");
    } else if (buttonName.equals("/")) {
      field1.setText(field1.getText() + " / ");
    } else if (buttonName.equals("^")) {
      field1.setText(field1.getText() + " ^ ");
    }

    // **************************************
    // Add one line of code here to complete the Equals button
    // **************************************
    else if (buttonName.equals("=")) {
      // Expression will be equal to the current expression
      // that the user has inputted
      String expression = field1.getText();

      // Replace the 0 below with the code you'd use to calculate the answer
      // You will need to do ShuntingYard.methodName() to access a method from
      // your ShuntingYard.java file
      int answer = ShuntingYard.evaluate(ShuntingYard.convert(expression));
      // ** You only need to edit the line above **

      field1.setText("" + answer);
    }
  }
}
