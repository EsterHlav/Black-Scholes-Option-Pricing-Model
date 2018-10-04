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

// graphics to build graph
public class JGraph extends JPanel {

   JPanel graph;
   int npoints; // number of simulations
   List<double[]> data;
   double[] x;
   int[] dimensions;
   String[] axes;
   int[] center;
   double[] vranges; // vertical axe ymin ymax
   String title;

   public JGraph(List<double[]> data, double[] points, String[] axes, int[] dimensions, String title) {
      this.npoints = data.size();
      this.x = points;
      this.data = data;
      this.axes = axes;
      this.dimensions = dimensions;
      this.vranges = findRange(data);
      this.center = new int[]{ (int) Math.round(dimensions[0]*0.2), (int) Math.round(dimensions[1]*0.9)};
      this.title = title;

      graph = new JPanel();
      graph.setPreferredSize(new Dimension(dimensions[0],dimensions[1]));
      graph.setLayout(null);
   }

   public void setGraph(List<double[]> data, double[] points, String[] axes, int[] dimensions, String title) {
      this.npoints = data.size();
      this.x = points;
      this.data = data;
      this.axes = axes;
      this.dimensions = dimensions;
      this.vranges = findRange(data);
      this.center = new int[]{ (int) Math.round(dimensions[0]*0.2), (int) Math.round(dimensions[1]*0.9)};
      this.title = title;
   }

   public void setGBM(List<double[]> data) {
      this.data = data;
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setColor(Color.BLACK);

      // Draw Text
      g2.drawString(title,dimensions[0]/2,20);

      // Draw axes
      // y
      g2.drawLine(center[0], center[1], dimensions[0]/5, dimensions[1]/10);
      // x
      g2.drawLine(center[0], center[1], dimensions[0]/10*9, dimensions[1]/10*9);

      // set name of axes
      g2.drawString(axes[1],dimensions[0]/8,dimensions[1]/15);
      g2.drawString(axes[0],dimensions[0]*95/100,dimensions[1]*9/10);


      // draw ticks and value (only 10 of them)
      int dx = dimensions[0]*7/10/9; // 70% of x is covered, want 9+1 points -> dx = range(x)/(n-1)
      int dy = dimensions[1]*8/10/9;

      int n = x.length;
      double deltaX = (x[n-1]-x[0])/9;
      double deltaY = (vranges[1]-vranges[0])/9;
      // for display purposes
      DecimalFormat df = new DecimalFormat("#.##");
      df.setRoundingMode(RoundingMode.CEILING);
      for (int i=0; i < 10; i++) {
         // x axe
         g2.drawLine(center[0]+dx*i, dimensions[1]*91/100, center[0]+dx*i, dimensions[1]*89/100);
         g2.drawString(df.format(x[0]+deltaX*i), center[0]+dx*i-dimensions[0]/100, dimensions[1]*95/100);
         // y axe
         g2.drawLine(dimensions[0]*19/100, center[1]-dy*i, dimensions[0]*21/100, center[1]-dy*i);
         g2.drawString(df.format(vranges[0]+deltaY*i), dimensions[0]*10/100, center[1]-dy*i+dimensions[1]/100);
      }

      // draw points
      Random rand = new Random(); // to random change colors
      for (int j=0; j<npoints; j++) { // for each simulation
         float rc = rand.nextFloat();
         float gc = rand.nextFloat();
         float bc = rand.nextFloat();
         Color randomColor = new Color(rc, gc, bc);
         g2.setColor(randomColor);
         double[] vector = this.data.get(j);
         int[] xyold = translateToPixel(x[0], vector[0]);
         for (int k=0; k<vector.length-1; k++) { // for each simulation
            int[] xy = translateToPixel(x[k+1], vector[k+1]);
            g2.drawLine(xyold[0], xyold[1],xy[0], xy[1]);
            xyold = xy;
         }
      }
   }

   public int[] translateToPixel(double xVal, double yVal) {
      int xT = center[0] + (int)((xVal-x[0])/(x[x.length-1]-x[0])*dimensions[0]*7/10);
      int yT = center[1] - (int)((yVal-vranges[0])/(vranges[vranges.length-1]-vranges[0])*dimensions[1]*8/10);
      return (new int[]{xT, yT});
   }

   public double[] findRange(List<double[]> l) {
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
}
