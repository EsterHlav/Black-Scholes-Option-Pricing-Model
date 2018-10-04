// eh2757 May 2017
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

public class Smile extends JFrame{

   JFrame mainFrame;
   BSOption BS;
   double[] times;
   double[] prices;
   double[] strikes;
   double guess;

   public Smile() {
      JFrame mainFrame = new JFrame("Volatility smile");
      mainFrame.setSize(1200, 700);
      mainFrame.setLayout(new BorderLayout());
      int sizeX = 1000;
      int sizeY = 600;

      BSOption BS = new BSOption(927.13, 900, 1, 0.011, 0, 0.1, 10, 'c');
      System.out.printf("Implied vol: %f", BS.ImpliedVol_NewtonRaphson(BS.price, guess, 300, Math.pow(10,-5)));
      guess = 0.19;
      // market data on GOOG stock: https://finance.yahoo.com/quote/GOOG/options?date=1513296000
      // call of December, so T-t is about 0.6666 so with T=1, we need t of about 0.33333
      times = new double[]{0.3, 0.3333, 0.4};
      strikes = new double[]{900, 915, 920, 925, 930, 935, 940, 960};
      prices = new double[]{76.43, 57.05, 62.10, 59.30, 57.45, 54.44 ,52.3, 43.5};
      List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);;

      JGraph graph = new JGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");

      // controls of figure
      JPanel southPanel = new JPanel(new GridLayout(2,4));

      JLabel paramtext1 = new JLabel("                Spot price: ");
      paramtext1.setVisible(true);
      JTextField param1 = new JTextField(20);
      param1.setText(String.valueOf(BS.S));
      param1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.S = Double.parseDouble(param1.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext1);
      southPanel.add(param1);

      JLabel paramtext2 = new JLabel("Maturity [y]: ");
      paramtext2.setVisible(true);
      JTextField param2 = new JTextField(20);
      param2.setText(String.valueOf(BS.T));
      param2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.T = Double.parseDouble(param2.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext2);
      southPanel.add(param2);

      JLabel paramtext3 = new JLabel("Risk-free rate: ");
      paramtext3.setVisible(true);
      JTextField param3 = new JTextField(20);
      param3.setText(String.valueOf(BS.r));
      param3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.r = Double.parseDouble(param3.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext3);
      southPanel.add(param3);

      JLabel paramtext4 = new JLabel("Yield-rate: ");
      paramtext4.setVisible(true);
      JTextField param4 = new JTextField(20);
      param4.setText(String.valueOf(BS.q));
      param4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.q = Double.parseDouble(param4.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext4);
      southPanel.add(param4);

      JLabel paramtext8 = new JLabel("        Times w/ space: ");
      paramtext8.setVisible(true);
      JTextField param8 = new JTextField(20);
      param8.setText(convertArrayDoubleToString(times));
      param8.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            times = convertStringToArrayDouble(param8.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext8);
      southPanel.add(param8);

      JLabel paramtext5 = new JLabel("Option's prices: ");
      paramtext5.setVisible(true);
      JTextField param5 = new JTextField(40);
      param5.setText(convertArrayDoubleToString(prices));
      param5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            prices = convertStringToArrayDouble(param5.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext5);
      southPanel.add(param5);

      JLabel paramtext10 = new JLabel("Strike prices: ");
      paramtext10.setVisible(true);
      JTextField param10 = new JTextField(40);
      param10.setText(convertArrayDoubleToString(strikes));
      param10.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            strikes = convertStringToArrayDouble(param10.getText());
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext10);
      southPanel.add(param10);

      JLabel paramtext9 = new JLabel("c/p for call/put: ");
      paramtext9.setVisible(true);
      JTextField param9 = new JTextField(20);
      param9.setText(String.valueOf(BS.type));
      param9.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.type = param9.getText().charAt(0);
            List<double[]> smileValue = computeVolatilitySmile(BS, strikes, prices, times, guess);
            graph.setGraph(smileValue, strikes, new String[]{"Strike price", "Volatility"}, new int[]{sizeX, sizeY}, "Volatility smile of option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext9);
      southPanel.add(param9);

      // button to go back to menu of Option Pricing Model
      JButton menuButtonPricing4 = new JButton("⌂");
      menuButtonPricing4.setBounds(-2, 630, 46, 24);
      mainFrame.add(menuButtonPricing4);
      menuButtonPricing4.setVisible(true);
      menuButtonPricing4.addActionListener( new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
                 mainFrame.dispose();
          }
      });

      mainFrame.add(southPanel, BorderLayout.SOUTH);
      mainFrame.getContentPane().add(graph, BorderLayout.CENTER);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);
   }

   public static double[] linspace(double min, double max, int points) {
       double[] d = new double[points];
       for (int i = 0; i < points; i++){
           d[i] = min + i * (max - min) / (points - 1);
       }
       return d;
   }

   public List<double[]> computeVolatilitySmile(BSOption BS, double[] strikes, double[] prices, double[] times, double guess) {
      List<double[]> res = new ArrayList<double[]>();
      int length = strikes.length;
      for (int i=0; i < times.length; i++) {
         double[] vector = new double[length];
         for (int j=0; j < length; j++) {
            vector[j] = BS.ImpliedVol_SecantSmile(prices[j], strikes[j], times[i], guess, 0.3, 200,  Math.pow(10,-3));
         }
         res.add(vector);
      }
      return res;
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

   public double[] convertStringToArrayDouble(String s) { // "0.11 3 5" -> {0.11,3,5}
      String[] items = s.split(" ");
      List<Double> array = new ArrayList<Double>();
      for (String item : items) {
         if (!item.equals("")) {
            array.add(Double.valueOf(item));
         }
      }

      // reconvert to double
      double[] res = new double[array.size()];
      int counter = 0;
      for (double item: array) {
         res[counter] = item;
         counter++;
      }
      return res;
   }

   public String convertArrayDoubleToString(double[] array) { // {0.11,3,5} -> "0.11 3 5"
      String res = "";
      for (double value : array) {
         res = res + " " + String.valueOf(value);
      }
      return res;
   }

   public static void main(String[] args) {
      JFrame smilePlot = new Smile();
   }
}
