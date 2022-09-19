package org.stacksAndQueuesLab;

import com.google.common.base.Splitter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import javax.swing.*;

public class Calculator implements ActionListener {

  // Swing instance variables
  JFrame frame;
  JTextField field1;
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
      buttonPow,
      buttonSqrt,
      buttonMod,
      buttonFact,
      buttonParen,
      buttonParen1;

  // Instance variables that will be used for our math
  ArrayDeque<Character> opStack = new ArrayDeque<>();
  ArrayDeque<Double> numStack = new ArrayDeque<>();

  public Calculator() {
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
    buttonPow = new JButton("^");
    panel5.add(buttonPow);
    buttonSqrt = new JButton("\u221A");
    panel5.add(buttonSqrt);
    buttonFact = new JButton("!");
    panel5.add(buttonFact);
    buttonMod = new JButton("%");
    panel5.add(buttonMod);
    buttonParen = new JButton("(");
    panel5.add(buttonParen);
    buttonParen1 = new JButton(")");
    panel5.add(buttonParen1);

    // Add implemented actionListener method to each button
    button1.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    button5.addActionListener(this);
    button6.addActionListener(this);
    button7.addActionListener(this);
    buttonClear.addActionListener(this);
    button8.addActionListener(this);
    button9.addActionListener(this);
    button0.addActionListener(this);
    buttonDivide.addActionListener(this);
    buttonTimes.addActionListener(this);
    buttonMinus.addActionListener(this);
    buttonPlus.addActionListener(this);
    buttonPow.addActionListener(this);
    buttonSqrt.addActionListener(this);
    buttonFact.addActionListener(this);
    buttonMod.addActionListener(this);
    buttonEquals.addActionListener(this);
    buttonParen.addActionListener(this);
    buttonParen1.addActionListener(this);
    // ...
    // ...
    // YOUR CODE HERE

    // Add panels and everything to the actual frame
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
    Calculator c = new Calculator();
    System.out.println(c.calculate("1 + ( 2 * 3 )"));
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    String buttonName = ae.getActionCommand();
    if ("AC".equals(buttonName)) {
      field1.setText("");
    } else if ("=".equals(buttonName)) {
      String expression = field1.getText();
      expression = expression.trim();
      field1.setText(calculate(expression));
    } else if ("+".equals(buttonName)
        || "-".equals(buttonName)
        || "*".equals(buttonName)
        || "/".equals(buttonName)
        || "^".equals(buttonName)
        || "%".equals(buttonName)
        || "!".equals(buttonName)
        || "\u221A".equals(buttonName)) {
      field1.setText(field1.getText() + " " + buttonName + " ");
    } else if ("(".equals(buttonName)) {
      field1.setText(field1.getText() + buttonName + " ");
    } else if (")".equals(buttonName)) {
      field1.setText(field1.getText() + " " + buttonName);
    } else {
      field1.setText(field1.getText() + buttonName);
    }
  }

  public String calculate(String expression) {
    Iterable<String> tokens = Splitter.on(' ').split(expression);
    for (String token : tokens) {
      char ch = token.charAt(0);
      if (ch >= '0' && ch <= '9') {
        double num = Double.parseDouble(token);
        numStack.push(num);
      } else if (isOp(ch)) {
        if (!opStack.isEmpty() && getPrecedence(ch) <= getPrecedence(opStack.peek())) {
          while (!opStack.isEmpty() && getPrecedence(ch) <= getPrecedence(opStack.peek())) {
            Character op = opStack.pop();
            processOp(op);
          }
        }
        opStack.push(ch);
      } else if (ch == '(') {
        opStack.push(ch);
      } else if (ch == ')') {
        while (!opStack.isEmpty() && isOp(opStack.peek())) {
          Character op = opStack.pop();
          processOp(op);
        }
        if (!opStack.isEmpty() && opStack.peek() == '(') {
          opStack.pop();
        } else {
          throw new IllegalArgumentException("Invalid expression");
        }
      }
    }
    while (!opStack.isEmpty() && isOp(opStack.peek())) {
      Character op = opStack.pop();
      processOp(op);
    }
    double result = numStack.pop();
    if (!opStack.isEmpty() || !numStack.isEmpty()) {
      throw new IllegalArgumentException("Invalid expression");
    } else {
      return Double.toString(result);
    }
  }

  public boolean isOp(Character x) {
    return x == '+'
        || x == '-'
        || x == '*'
        || x == '/'
        || x == '^'
        || x == '%'
        || x == '!'
        || x == '\u221A';
  }

  public int getPrecedence(Character x) {
    if (x == '+' || x == '-') {
      return 1;
    } else if (x == '*' || x == '/' || x == '%' || x == '^') {
      return 2;
    }
    return 0;
  }

  public void processOp(Character x) {
    double a;
    double b;
    if (numStack.isEmpty()) {
      throw new IllegalArgumentException("Invalid expression");
    } else {
      b = numStack.pop();
    }
    if (numStack.isEmpty()) {
      a = 1;
    } else {
      a = numStack.pop();
    }
    double r =
        switch (x) {
          case '+' -> a + b;
          case '-' -> a - b;
          case '*' -> a * b;
          case '/' -> a / b;
          case '^' -> Math.pow(a, b);
          case '%' -> a % b;
          case '!' -> factorial(b);
          case '\u221A' -> a * Math.sqrt(b);
          default -> throw new IllegalArgumentException("Invalid expression");
        };
    numStack.push(r);
  }

  private double factorial(double a) {
    double r = 1;
    for (int i = 1; i <= a; i++) {
      r = r * i;
    }
    return r;
  }
}
