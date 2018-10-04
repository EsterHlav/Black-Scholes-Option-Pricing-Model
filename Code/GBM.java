import java.lang.Math;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class GBM extends JFrame{

   JFrame mainFrame;
   double drift;
   double volatility;
   double maturity;
   double Sini;
   int numberOfSimulations;
   int numberOfPoints;

   public GBM() {
      JFrame mainFrame = new JFrame("Simulation Geometric Brownian Motion");
      mainFrame.setSize(1000, 700);
      mainFrame.setLayout(new BorderLayout());
      drift = 0.005;
      volatility = 0.1;
      maturity = 5.0;
      Sini = 100;
      numberOfSimulations = 100;
      numberOfPoints = 250;
      int sizeX = 900;
      int sizeY = 600;


      List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
      //printList(gbm);
      double[] range = findRange(gbm);
      double[] t = linspace(0, maturity, numberOfPoints);
      JGraph graph = new JGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
      //graph.setVisible(true);

      // controls of figure
      JPanel southPanel = new JPanel(new GridLayout(2,6));

      JLabel paramtext1 = new JLabel("                         Drift: ");
      paramtext1.setVisible(true);
      JTextField param1 = new JTextField(20);
      param1.setText(String.valueOf(drift));
      param1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            drift = Double.parseDouble(param1.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext1);
      southPanel.add(param1);

      JLabel paramtext2 = new JLabel("   Volatility: ");
      paramtext2.setVisible(true);
      JTextField param2 = new JTextField(20);
      param2.setText(String.valueOf(volatility));
      param2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            volatility = Double.parseDouble(param2.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext2);
      southPanel.add(param2);

      JLabel paramtext3 = new JLabel("          Maturity (years): ");
      paramtext3.setVisible(true);
      JTextField param3 = new JTextField(20);
      param3.setText(String.valueOf(maturity));
      param3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            maturity = Double.parseDouble(param3.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext3);
      southPanel.add(param3);

      JLabel paramtext4 = new JLabel("                         S(0): ");
      paramtext4.setVisible(true);
      JTextField param4 = new JTextField(20);
      param4.setText(String.valueOf(Sini));
      param4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Sini = Double.parseDouble(param4.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext4);
      southPanel.add(param4);

      JLabel paramtext5 = new JLabel("   Number of simulations: ");
      paramtext5.setVisible(true);
      JTextField param5 = new JTextField(20);
      param5.setText(String.valueOf(numberOfSimulations));
      param5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            numberOfSimulations = Integer.valueOf(param5.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext5);
      southPanel.add(param5);

      JLabel paramtext6 = new JLabel("          Number of points: ");
      paramtext6.setVisible(true);
      JTextField param6 = new JTextField(20);
      param6.setText(String.valueOf(numberOfPoints));
      param6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            numberOfPoints = Integer.valueOf(param6.getText());
            List<double[]> gbm = generateGBM(drift, volatility, maturity, Sini, numberOfSimulations, numberOfPoints);
            double[] t = linspace(0, maturity, numberOfPoints);
            graph.setGraph(gbm, t, new String[]{"time", "S(time)"}, new int[]{sizeX, sizeY}, "Simulation GBM");
            graph.repaint();
         }
      });
      southPanel.add(paramtext6);
      southPanel.add(param6);

      // click enter after inserting a value
      JLabel enterLabelEnter = new JLabel("After inserting a value, please press enter each time.");
      enterLabelEnter.setBounds(105, 595, 400, 30);
      mainFrame.add(enterLabelEnter);
      enterLabelEnter.setFont(new Font("Calibri", Font.ITALIC, 13));
      enterLabelEnter.setVisible(true);

      // button to go back to menu of Option Pricing Model
      JButton menuButtonPricingGBM = new JButton("⌂ Menu");
      menuButtonPricingGBM.setBounds(7, 625, 86, 43);
      mainFrame.add(menuButtonPricingGBM);
      menuButtonPricingGBM.setVisible(true);
      menuButtonPricingGBM.addActionListener( new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
                 mainFrame.dispose();
          }
      });


      mainFrame.add(southPanel, BorderLayout.SOUTH);
      mainFrame.getContentPane().add(graph, BorderLayout.CENTER);
      mainFrame.setVisible(true);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static List<double[]> generateGBM(double drift, double vol, double T, double S0, int numberSimulations, int length) {
      Random rnd = new Random();
      double Deltat = (T - 0) / (length - 1);
      double sqrtDeltat = Math.sqrt(Deltat);
      List<double[]> res = new ArrayList<double[]>();
      for (int i=0; i < numberSimulations; i++) {
         // create vector of simulations with first valeu S0;
         double[] vectorS = new double[length];
         vectorS[0] = S0;
         for (int j=1; j < length; j++) {
            vectorS[j] = vectorS[j-1]*(1+drift*Deltat+vol*rnd.nextGaussian()*sqrtDeltat);
         }
         res.add(vectorS);
      }
      return res;
   }

   public static double[] linspace(double min, double max, int points) {
       double[] d = new double[points];
       for (int i = 0; i < points; i++){
           d[i] = min + i * (max - min) / (points - 1);
       }
       return d;
   }


   public static void printList(List<double[]> l) {
      int n = l.size();
      int m = l.get(0).length;

      // loop print
      for (int i=0; i < n; i++) {
         double[] vectorS = l.get(i);
         System.out.printf("Array %d: ", i+1);
         for (int j=0; j < m; j++) {
            System.out.printf("%f ", vectorS[j]);
         }
         System.out.printf("\n");
      }
   }

   public static double[] findRange(List<double[]> l) {
      int n = l.size();
      int m = l.get(0).length;

      double max = Double.MIN_VALUE;
      double min = Double.MAX_VALUE;
      for (int i=0; i < n; i++) {
         double[] vector = l.get(i);
         for (double val : vector) {
            max = Math.max(max, val);
            min = Math.min(min, val);
         }
      }
      return new double[]{min-(max-min)*0.05,max+(max-min)*0.05};
   }

   // for debug
  //  public static void main(String[] args) {
  //     JFrame simuGBM = new GBM();
  //  }
}
