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

public class PayoffDiagram extends JFrame{

   JFrame mainFrame;
   BSOption BS;
   double[] times;
   double Smin;
   double Smax;
   int length;

   public PayoffDiagram() {
      JFrame mainFrame = new JFrame("Payoff Diagram");
      mainFrame.setSize(1200, 700);
      mainFrame.setLayout(new BorderLayout());
      int sizeX = 1000;
      int sizeY = 600;

      times = new double[]{1.0, 3.0 ,5.0 };
      BSOption BS = new BSOption(120, 100, 5, 0.02, 0.01, 0.1, 1, 'c');
      Smin = 70;
      Smax=170;
      length = 250;
      List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
      // printList(payoff);

      double[] range = findRange(payoff);
      double[] t = linspace(Smin, Smax, length);
      JGraph graph = new JGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");

      // controls of figure
      JPanel southPanel = new JPanel(new GridLayout(2,5));

      JLabel paramtext1 = new JLabel("                   Strike: ");
      paramtext1.setVisible(true);
      JTextField param1 = new JTextField(20);
      param1.setText(String.valueOf(BS.K));
      param1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.K = Double.parseDouble(param1.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
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
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
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
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
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
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext4);
      southPanel.add(param4);

      JLabel paramtext5 = new JLabel("Volatility: ");
      paramtext5.setVisible(true);
      JTextField param5 = new JTextField(20);
      param5.setText(String.valueOf(BS.vol));
      param5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.vol = Double.parseDouble(param5.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext5);
      southPanel.add(param5);

      JLabel paramtext6 = new JLabel("          S minimum: ");
      paramtext6.setVisible(true);
      JTextField param6 = new JTextField(20);
      param6.setText(String.valueOf(Smin));
      param6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Smin = Double.parseDouble(param6.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext6);
      southPanel.add(param6);

      JLabel paramtext7 = new JLabel("S maximum: ");
      paramtext7.setVisible(true);
      JTextField param7 = new JTextField(20);
      param7.setText(String.valueOf(Smax));
      param7.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Smax = Double.parseDouble(param7.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext7);
      southPanel.add(param7);

      JLabel paramtext8 = new JLabel("Times w/ space: ");
      paramtext8.setVisible(true);
      JTextField param8 = new JTextField(20);
      param8.setText(convertArrayDoubleToString(times));
      param8.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            times = convertStringToArrayDouble(param8.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext8);
      southPanel.add(param8);

      JLabel paramtext9 = new JLabel("Number of points: ");
      paramtext9.setVisible(true);
      JTextField param9 = new JTextField(20);
      param9.setText(String.valueOf(length));
      param9.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            length = Integer.valueOf(param9.getText());
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext9);
      southPanel.add(param9);

      JLabel paramtext10 = new JLabel("c/p for call/put): ");
      paramtext10.setVisible(true);
      JTextField param10 = new JTextField(20);
      param10.setText(String.valueOf(BS.type));
      param10.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BS.type = param10.getText().charAt(0);
            List<double[]> payoff = computePayOffDiagram(BS, times, Smin, Smax, length);
            double[] t = linspace(Smin, Smax, length);
            graph.setGraph(payoff, t, new String[]{"Spot price", "Payoff/Price"}, new int[]{sizeX, sizeY}, "Payoff Diagram for option");
            graph.repaint();
         }
      });
      southPanel.add(paramtext10);
      southPanel.add(param10);


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

   public List<double[]> computePayOffDiagram(BSOption option, double[] times, double Smin, double Smax, int length) {
      int n = times.length;
      List<double[]> res = new ArrayList<double[]>();
      double deltaS = (Smax - Smin) / (length - 1);
      for (int i=0; i < n; i++) {
         double[] vectorS = new double[length];
         for (int j=0; j < length; j++) {
            vectorS[j] = option.computePriceDiagram(Smin + j*deltaS, times[i]);
         }
         res.add(vectorS);
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

  //  public static void main(String[] args) {
  //     JFrame diagram = new PayoffDiagram();
  //  }
}
