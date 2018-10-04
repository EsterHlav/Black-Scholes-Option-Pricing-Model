import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class ComputeOptionPrice extends JFrame {

  JFrame mainFrame;
  BSOption optionCall;
  BSOption optionPut;

  public ComputeOptionPrice(double s, double k, double t, double r, double q, double vol) {
    optionCall = new BSOption(s, k, t, r, q, vol, 0, 'c');
    optionPut = new BSOption(s, k, t, r, q, vol, 0, 'p');

    // create window
    JFrame mainFrame = new JFrame("Calculating the Price of a Call and Put");
    mainFrame.setSize(1000, 700);
    mainFrame.setLayout(null);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.getContentPane().setBackground(Color.WHITE);


    //// label for CALL
    JLabel callLabel = new JLabel("CALCULATING PRICE OF A CALL OPTION");
    callLabel.setBounds(300, 5, 400, 30);
    mainFrame.add(callLabel);
    callLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    callLabel.setForeground(Color.BLUE);
    callLabel.setVisible(true);
    JLabel enterLabel = new JLabel("After inserting a value, please press enter each time.");
    enterLabel.setBounds(30, 50, 400, 30);
    mainFrame.add(enterLabel);
    enterLabel.setFont(new Font("Calibri", Font.ITALIC, 13));
    enterLabel.setVisible(true);


    // parameters and rest of UI for call (upper part)
    JLabel resultLabel = new JLabel("The value of your call is ");
    resultLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    resultLabel.setBounds(683, 150, 300, 40);
    mainFrame.add(resultLabel);
    resultLabel.setVisible(true);
    JLabel resultPriceCall = new JLabel(String.valueOf(optionCall.computePrice()));
    resultPriceCall.setFont(new Font("Calibri", Font.BOLD, 17));
    resultPriceCall.setBounds(700, 180, 300, 40);
    mainFrame.add(resultPriceCall);
    resultPriceCall.setVisible(true);

    //// SPOT PRICE
    JLabel paramtext1 = new JLabel("Spot price S:    ");
    paramtext1.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext1.setBounds(30, 80, 150, 40);
    mainFrame.add(paramtext1);
    paramtext1.setVisible(true);
    JTextField param1 = new JTextField(20);         // text next to label
    mainFrame.add(param1);
    param1.setBackground(Color.cyan);
    param1.setBounds(175, 87, 100, 30);
    param1.setVisible(true);
    param1.setText(String.valueOf(optionCall.S));
    param1.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.S = Double.parseDouble(param1.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });


    //// STRIKE PRICE
    JLabel paramtext2 = new JLabel("Strike price K:  ");
    paramtext2.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext2.setBounds(330, 80, 150, 40);
    mainFrame.add(paramtext2);
    paramtext2.setVisible(true);
    JTextField param2 = new JTextField(20);         // text next to label
    mainFrame.add(param2);
    param2.setBackground(Color.cyan);
    param2.setBounds(475, 87, 100, 30);
    param2.setVisible(true);
    param2.setText(String.valueOf(optionCall.K));
    param2.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.K = Double.parseDouble(param2.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });


    //// MATURITY
    JLabel paramtext3 = new JLabel("Maturity T:      ");
    paramtext3.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext3.setBounds(30, 170, 150, 40);
    mainFrame.add(paramtext3);
    paramtext3.setVisible(true);
    JTextField param3 = new JTextField(20);         // text next to label
    mainFrame.add(param3);
    param3.setBackground(Color.cyan);
    param3.setBounds(175, 177, 100, 30);
    param3.setVisible(true);
    param3.setText(String.valueOf(optionCall.T));
    param3.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.T = Double.parseDouble(param3.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });


    //// VOLATILITY
    JLabel paramtext4 = new JLabel("Volatility :     ");
    paramtext4.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext4.setBounds(330, 170, 150, 40);
    mainFrame.add(paramtext4);
    paramtext4.setVisible(true);
    JTextField param4 = new JTextField(20);         // text next to label
    mainFrame.add(param4);
    param4.setBackground(Color.cyan);
    param4.setBounds(475, 177, 100, 30);
    param4.setVisible(true);
    param4.setText(String.valueOf(optionCall.vol));
    param4.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.vol = Double.parseDouble(param4.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });


    //// RISK-FREE RATE
    JLabel paramtext5 = new JLabel("Riskfree Rate r:  ");
    paramtext5.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext5.setBounds(30, 260, 150, 40);
    mainFrame.add(paramtext5);
    paramtext5.setVisible(true);
    JTextField param5 = new JTextField(20);         // text next to label
    mainFrame.add(param5);
    param5.setBackground(Color.cyan);
    param5.setBounds(175, 267, 100, 30);
    param5.setVisible(true);
    param5.setText(String.valueOf(optionCall.r));
    param5.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.r = Double.parseDouble(param5.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });

    //// ANNUAL YIELD
    JLabel paramtext6 = new JLabel("Annual Yield q:  ");
    paramtext6.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext6.setBounds(330, 260, 150, 40);
    mainFrame.add(paramtext6);
    paramtext6.setVisible(true);
    JTextField param6 = new JTextField(20);         // text next to label
    mainFrame.add(param6);
    param6.setBackground(Color.cyan);
    param6.setBounds(475, 267, 100, 30);
    param6.setVisible(true);
    param6.setText(String.valueOf(optionCall.q));
    param6.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionCall.q = Double.parseDouble(param6.getText());
          resultPriceCall.setText(String.valueOf(optionCall.computePrice()));
       }
    });



    //// label for PUT
    JLabel putLabel = new JLabel("CALCULATING PRICE OF A PUT OPTION");
    putLabel.setBounds(300, 340, 400, 30);
    mainFrame.add(putLabel);
    putLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    putLabel.setForeground(Color.BLUE);
    putLabel.setVisible(true);
    JLabel enterLabel2 = new JLabel("After inserting a value, please press enter each time.");
    enterLabel2.setBounds(30, 385, 400, 30);
    mainFrame.add(enterLabel2);
    enterLabel2.setFont(new Font("Calibri", Font.ITALIC, 13));
    enterLabel2.setVisible(true);


    // parameters and rest of UI for put (down part)
    JLabel resultLabelPut = new JLabel("The value of your put is  ");
    resultLabelPut.setFont(new Font("Calibri", Font.BOLD, 17));
    resultLabelPut.setBounds(683, 490, 300, 40);
    mainFrame.add(resultLabelPut);
    resultLabelPut.setVisible(true);
    JLabel resultPricePut = new JLabel(String.valueOf(optionPut.computePrice()));
    resultPricePut.setFont(new Font("Calibri", Font.BOLD, 17));
    resultPricePut.setBounds(700, 520, 300, 40);
    mainFrame.add(resultPricePut);
    resultPricePut.setVisible(true);

    //// SPOT PRICE
    JLabel paramtext1b = new JLabel("Spot price S:    ");
    paramtext1b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext1b.setBounds(30, 415, 150, 40);
    mainFrame.add(paramtext1b);
    paramtext1b.setVisible(true);
    JTextField param1b = new JTextField(20);         // text next to label
    mainFrame.add(param1b);
    param1b.setBackground(Color.cyan);
    param1b.setBounds(175, 422, 100, 30);
    param1b.setVisible(true);
    param1b.setText(String.valueOf(optionPut.S));
    param1b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.S = Double.parseDouble(param1b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });


    //// STRIKE PRICE
    JLabel paramtext2b = new JLabel("Strike price K:  ");
    paramtext2b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext2b.setBounds(330, 415, 150, 40);
    mainFrame.add(paramtext2b);
    paramtext2b.setVisible(true);
    JTextField param2b = new JTextField(20);         // text next to label
    mainFrame.add(param2b);
    param2b.setBackground(Color.cyan);
    param2b.setBounds(475, 422, 100, 30);
    param2b.setVisible(true);
    param2b.setText(String.valueOf(optionPut.K));
    param2b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.K = Double.parseDouble(param2b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });


    //// MATURITY
    JLabel paramtext3b = new JLabel("Maturity T:      ");
    paramtext3b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext3b.setBounds(30, 505, 150, 40);
    mainFrame.add(paramtext3b);
    paramtext3b.setVisible(true);
    JTextField param3b = new JTextField(20);         // text next to label
    mainFrame.add(param3b);
    param3b.setBackground(Color.cyan);
    param3b.setBounds(175, 512, 100, 30);
    param3b.setVisible(true);
    param3b.setText(String.valueOf(optionPut.T));
    param3b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.T = Double.parseDouble(param3b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });


    //// VOLATILITY
    JLabel paramtext4b = new JLabel("Volatility :     ");
    paramtext4b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext4b.setBounds(330, 505, 150, 40);
    mainFrame.add(paramtext4b);
    paramtext4b.setVisible(true);
    JTextField param4b = new JTextField(20);         // text next to label
    mainFrame.add(param4b);
    param4b.setBackground(Color.cyan);
    param4b.setBounds(475, 512, 100, 30);
    param4b.setVisible(true);
    param4b.setText(String.valueOf(optionPut.vol));
    param4b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.vol = Double.parseDouble(param4b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });


    //// RISK-FREE RATE
    JLabel paramtext5b = new JLabel("Riskfree Rate r:  ");
    paramtext5b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext5b.setBounds(30, 595, 150, 40);
    mainFrame.add(paramtext5b);
    paramtext5b.setVisible(true);
    JTextField param5b = new JTextField(20);         // text next to label
    mainFrame.add(param5b);
    param5b.setBackground(Color.cyan);
    param5b.setBounds(175, 602, 100, 30);
    param5b.setVisible(true);
    param5b.setText(String.valueOf(optionPut.r));
    param5b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.r = Double.parseDouble(param5b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });

    //// ANNUAL YIELD
    JLabel paramtext6b = new JLabel("Annual Yield q:  ");
    paramtext6b.setFont(new Font("Calibri", Font.BOLD, 17));
    paramtext6b.setBounds(330, 595, 150, 40);
    mainFrame.add(paramtext6b);
    paramtext6b.setVisible(true);
    JTextField param6b = new JTextField(20);         // text next to label
    mainFrame.add(param6b);
    param6b.setBackground(Color.cyan);
    param6b.setBounds(475, 602, 100, 30);
    param6b.setVisible(true);
    param6b.setText(String.valueOf(optionPut.q));
    param6b.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          optionPut.q = Double.parseDouble(param6b.getText());
          resultPricePut.setText(String.valueOf(optionPut.computePrice()));
       }
    });

    // back button to be able to return to the menu of option pricing model
    JButton backButtontoModel = new JButton("↩ Back");
    backButtontoModel.setVisible(true);
    backButtontoModel.setBounds(745, 600, 100, 40);
    mainFrame.add(backButtontoModel);
    backButtontoModel.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
          mainFrame.dispose();
        }
    });

    mainFrame.setVisible(true);
  }

}
