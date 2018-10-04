import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class ComputeGreeks extends JFrame {

  JFrame mainFrame;
  BSOption optionCall;
  BSOption optionPut;


  public ComputeGreeks(double s, double k, double t, double r, double q, double vol) {
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
    JLabel callLabel = new JLabel("CALCULATING THE GREEKS OF A CALL OPTION");
    callLabel.setBounds(250, 5, 500, 30);
    mainFrame.add(callLabel);
    callLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    callLabel.setForeground(Color.BLUE);
    callLabel.setVisible(true);
    JLabel enterLabel = new JLabel("After inserting a value, please press enter each time.");
    enterLabel.setBounds(30, 50, 400, 30);
    mainFrame.add(enterLabel);
    enterLabel.setFont(new Font("Calibri", Font.ITALIC, 13));
    enterLabel.setVisible(true);


    //// DELTA CALL
    JLabel deltaCallLabel = new JLabel("Delta:   ");
    deltaCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    deltaCallLabel.setForeground(Color.darkGray);
    deltaCallLabel.setBounds(690, 10, 100, 40);
    mainFrame.add(deltaCallLabel);
    deltaCallLabel.setVisible(true);
    JLabel greekCall1 = new JLabel("");         // label with calculated value
    greekCall1.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall1.setForeground(Color.darkGray);
    greekCall1.setBounds(763, 10, 200, 40);
    mainFrame.add(greekCall1);
    greekCall1.setVisible(true);

    //// VEGA CALL
    JLabel vegaCallLabel = new JLabel("Vega:    ");
    vegaCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    vegaCallLabel.setForeground(Color.darkGray);
    vegaCallLabel.setBounds(690, 40, 100, 40);
    mainFrame.add(vegaCallLabel);
    vegaCallLabel.setVisible(true);
    JLabel greekCall2 = new JLabel("");
    greekCall2.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall2.setBounds(763, 40, 300, 40);
    mainFrame.add(greekCall2);
    greekCall2.setVisible(true);

    //// PSI CALL
    JLabel psiCallLabel = new JLabel("Psi:     ");
    psiCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    psiCallLabel.setForeground(Color.darkGray);
    psiCallLabel.setBounds(690, 70, 100, 40);
    mainFrame.add(psiCallLabel);
    psiCallLabel.setVisible(true);
    JLabel greekCall3 = new JLabel("");
    greekCall3.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall3.setBounds(763, 70, 300, 40);
    mainFrame.add(greekCall3);
    greekCall3.setVisible(true);

    //// THETA CALL
    JLabel thetaCallLabel = new JLabel("Theta:   ");
    thetaCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    thetaCallLabel.setForeground(Color.darkGray);
    thetaCallLabel.setBounds(690, 100, 100, 40);
    mainFrame.add(thetaCallLabel);
    thetaCallLabel.setVisible(true);
    JLabel greekCall4 = new JLabel("");
    greekCall4.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall4.setBounds(763, 100, 300, 40);
    mainFrame.add(greekCall4);
    greekCall4.setVisible(true);

    //// RHO CALL
    JLabel rhoCallLabel = new JLabel("Rho:     ");
    rhoCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    rhoCallLabel.setForeground(Color.darkGray);
    rhoCallLabel.setBounds(690, 130, 100, 40);
    mainFrame.add(rhoCallLabel);
    rhoCallLabel.setVisible(true);
    JLabel greekCall5 = new JLabel("");
    greekCall5.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall5.setBounds(763, 130, 300, 40);
    mainFrame.add(greekCall5);
    greekCall5.setVisible(true);

    //// GAMMA CALL
    JLabel gammaCallLabel = new JLabel("Gamma:   ");
    gammaCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    gammaCallLabel.setForeground(Color.darkGray);
    gammaCallLabel.setBounds(690, 160, 100, 40);
    mainFrame.add(gammaCallLabel);
    gammaCallLabel.setVisible(true);
    JLabel greekCall6 = new JLabel("");
    greekCall6.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall6.setBounds(763, 160, 300, 40);
    mainFrame.add(greekCall6);
    greekCall6.setVisible(true);

    //// VOLGA CALL
    JLabel volgaCallLabel = new JLabel("Volga:   ");
    volgaCallLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    volgaCallLabel.setForeground(Color.darkGray);
    volgaCallLabel.setBounds(690, 190, 100, 40);
    mainFrame.add(volgaCallLabel);
    volgaCallLabel.setVisible(true);
    JLabel greekCall7 = new JLabel("");
    greekCall7.setFont(new Font("Calibri", Font.BOLD, 17));
    greekCall7.setBounds(763, 190, 300, 40);
    mainFrame.add(greekCall7);
    greekCall7.setVisible(true);


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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
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
          double[] greeksCall = optionCall.computeGreeks();
          greekCall1.setText(String.valueOf(greeksCall[0]));
          greekCall2.setText(String.valueOf(greeksCall[1]));
          greekCall3.setText(String.valueOf(greeksCall[2]));
          greekCall4.setText(String.valueOf(greeksCall[3]));
          greekCall5.setText(String.valueOf(greeksCall[4]));
          greekCall6.setText(String.valueOf(greeksCall[5]));
          greekCall7.setText(String.valueOf(greeksCall[6]));
       }
    });

    double[] greeksCall = optionCall.computeGreeks();
    greekCall1.setText(String.valueOf(greeksCall[0]));
    greekCall2.setText(String.valueOf(greeksCall[1]));
    greekCall3.setText(String.valueOf(greeksCall[2]));
    greekCall4.setText(String.valueOf(greeksCall[3]));
    greekCall5.setText(String.valueOf(greeksCall[4]));
    greekCall6.setText(String.valueOf(greeksCall[5]));
    greekCall7.setText(String.valueOf(greeksCall[6]));

    //// label for PUT
    JLabel putLabel = new JLabel("CALCULATING THE GREEKS OF A PUT OPTION");
    putLabel.setBounds(250, 340, 500, 30);
    mainFrame.add(putLabel);
    putLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    putLabel.setForeground(Color.BLUE);
    putLabel.setVisible(true);
    JLabel enterLabel2 = new JLabel("After inserting a value, please press enter each time.");
    enterLabel2.setBounds(30, 385, 400, 30);
    mainFrame.add(enterLabel2);
    enterLabel2.setFont(new Font("Calibri", Font.ITALIC, 13));
    enterLabel2.setVisible(true);


    //// DELTA PUT
    JLabel deltaPutLabel = new JLabel("Delta:   ");
    deltaPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    deltaPutLabel.setForeground(Color.darkGray);
    deltaPutLabel.setBounds(690, 345, 100, 40);
    mainFrame.add(deltaPutLabel);
    deltaPutLabel.setVisible(true);
    JLabel greekPut1 = new JLabel("");         // label with calculated value
    greekPut1.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut1.setForeground(Color.darkGray);
    greekPut1.setBounds(763, 345, 300, 40);
    mainFrame.add(greekPut1);
    greekPut1.setVisible(true);

    //// VEGA PUT
    JLabel vegaPutLabel = new JLabel("Vega:    ");
    vegaPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    vegaPutLabel.setForeground(Color.darkGray);
    vegaPutLabel.setBounds(690, 375, 100, 40);
    mainFrame.add(vegaPutLabel);
    vegaPutLabel.setVisible(true);
    JLabel greekPut2 = new JLabel("");
    greekPut2.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut2.setBounds(763, 375, 300, 40);
    mainFrame.add(greekPut2);
    greekPut2.setVisible(true);

    //// PSI PUT
    JLabel psiPutLabel = new JLabel("Psi:     ");
    psiPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    psiPutLabel.setForeground(Color.darkGray);
    psiPutLabel.setBounds(690, 405, 100, 40);
    mainFrame.add(psiPutLabel);
    psiPutLabel.setVisible(true);
    JLabel greekPut3 = new JLabel("");
    greekPut3.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut3.setBounds(763, 405, 300, 40);
    mainFrame.add(greekPut3);
    greekPut3.setVisible(true);

    //// THETA PUT
    JLabel thetaPutLabel = new JLabel("Theta:   ");
    thetaPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    thetaPutLabel.setForeground(Color.darkGray);
    thetaPutLabel.setBounds(690, 435, 100, 40);
    mainFrame.add(thetaPutLabel);
    thetaPutLabel.setVisible(true);
    JLabel greekPut4 = new JLabel("");
    greekPut4.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut4.setBounds(763, 435, 300, 40);
    mainFrame.add(greekPut4);
    greekPut4.setVisible(true);

    //// RHO PUT
    JLabel rhoPutLabel = new JLabel("Rho:     ");
    rhoPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    rhoPutLabel.setForeground(Color.darkGray);
    rhoPutLabel.setBounds(690, 465, 100, 40);
    mainFrame.add(rhoPutLabel);
    rhoPutLabel.setVisible(true);
    JLabel greekPut5 = new JLabel("");
    greekPut5.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut5.setBounds(763, 465, 300, 40);
    mainFrame.add(greekPut5);
    greekPut5.setVisible(true);

    //// GAMMA PUT
    JLabel gammaPutLabel = new JLabel("Gamma:   ");
    gammaPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    gammaPutLabel.setForeground(Color.darkGray);
    gammaPutLabel.setBounds(690, 495, 100, 40);
    mainFrame.add(gammaPutLabel);
    gammaPutLabel.setVisible(true);
    JLabel greekPut6 = new JLabel("");
    greekPut6.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut6.setBounds(763, 495, 300, 40);
    mainFrame.add(greekPut6);
    greekPut6.setVisible(true);

    //// VOLGA PUT
    JLabel volgaPutLabel = new JLabel("Volga:   ");
    volgaPutLabel.setFont(new Font("Calibri", Font.BOLD, 17));
    volgaPutLabel.setForeground(Color.darkGray);
    volgaPutLabel.setBounds(690, 525, 100, 40);
    mainFrame.add(volgaPutLabel);
    volgaPutLabel.setVisible(true);
    JLabel greekPut7 = new JLabel("");
    greekPut7.setFont(new Font("Calibri", Font.BOLD, 17));
    greekPut7.setBounds(763, 525, 300, 40);
    mainFrame.add(greekPut7);
    greekPut7.setVisible(true);


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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
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
          double[] greeksPut = optionPut.computeGreeks();
          greekPut1.setText(String.valueOf(greeksPut[0]));
          greekPut2.setText(String.valueOf(greeksPut[1]));
          greekPut3.setText(String.valueOf(greeksPut[2]));
          greekPut4.setText(String.valueOf(greeksPut[3]));
          greekPut5.setText(String.valueOf(greeksPut[4]));
          greekPut6.setText(String.valueOf(greeksPut[5]));
          greekPut7.setText(String.valueOf(greeksPut[6]));
       }
    });

    double[] greeksPut = optionPut.computeGreeks();
    greekPut1.setText(String.valueOf(greeksPut[0]));
    greekPut2.setText(String.valueOf(greeksPut[1]));
    greekPut3.setText(String.valueOf(greeksPut[2]));
    greekPut4.setText(String.valueOf(greeksPut[3]));
    greekPut5.setText(String.valueOf(greeksPut[4]));
    greekPut6.setText(String.valueOf(greeksPut[5]));
    greekPut7.setText(String.valueOf(greeksPut[6]));

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
