import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class OptionPricing {

  public static void main (String [] args) {
    launchMainMenu();
  }

  public static void launchMainMenu() {
    JFrame frame = new JFrame("Black-Scholes Option Pricing Model");

    // setting up the frame - size, color, etc.
    frame.setLayout(null);
    frame.setSize(600, 600);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.getContentPane().setBackground(Color.WHITE);

    // initial text
    JLabel helloLabel = new JLabel("Welcome to a Black-Scholes Option Pricing program!");
    helloLabel.setLocation(140,5);
    helloLabel.setSize(400, 50);
    helloLabel.setForeground(Color.blue);
    frame.add(helloLabel);

    JLabel option1 = new JLabel("To learn about derivative contracts ");
    JLabel option1a = new JLabel(" click 'Introduction to Calls & Puts'");
    option1.setLocation(42, 135);
    option1.setSize(400, 15);
    option1a.setLocation(42, 155);
    option1a.setSize(400, 15);
    frame.add(option1);
    frame.add(option1a);

    JLabel option2 = new JLabel("To proceed to Black-Scholes ");
    JLabel option2a = new JLabel(" click 'Option Pricing Model'");
    option2.setLocation(355, 135);
    option2.setSize(400, 15);
    option2a.setLocation(355, 155);
    option2a.setSize(400, 15);
    frame.add(option2);
    frame.add(option2a);


    // images on the initial window
    ImageIcon image1 = new ImageIcon("put.call.jpg");
    JLabel label1 = new JLabel(image1);
    label1.setVisible(true);
    label1.setBounds(5, 60, 300, 400);
    frame.add(label1);

    ImageIcon image2 = new ImageIcon("simulation.png");
    JLabel label2 = new JLabel(image2);
    frame.add(label2);
    label2.setVisible(true);
    label2.setBounds(300, 60, 300, 400);

    // adding two buttons for the initial screen
    // adding button for introduction/quiz
    JButton introButton = new JButton("Introduction to Calls & Puts");
    introButton.setVisible(true);
    introButton.setBounds(5,360,285,50);
    introButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
          JFrame launch = new launchIntro();
          frame.dispose();
        }
    });
    frame.add(introButton);

    // adding button for option pricing
    JButton pricingButton = new JButton("Option Pricing Model");
    pricingButton.setVisible(true);
    pricingButton.setBounds(315,360,280,50);
    pricingButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
          launchPricing();
          frame.dispose();
        }
    });
    frame.add(pricingButton);

    // copyright label
    JLabel copyright = new JLabel("© 2017 Ester Hlavnova");
    copyright.setLocation(440,560);
    copyright.setSize(200, 10);
    frame.add(copyright);

    // setting frame visible
    frame.setVisible(true);
  }

  // function to make a new window frame for Introduction
  public static class launchIntro extends JFrame {
     JFrame frameIntro;
     int indexSlide;
     int numberOfSlides;

     launchIntro() {
     frameIntro = new JFrame("Introduction to Options");
     indexSlide = 0;

     // setting up the new frame window
     frameIntro.setLayout(null);
     frameIntro.setSize(1000, 700);
     frameIntro.setResizable(false);
     frameIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frameIntro.setLocationRelativeTo(null);


     ArrayList<ImageIcon> listIcons = new ArrayList<ImageIcon>();
     listIcons.add(new ImageIcon("Slide01.png"));
     listIcons.add(new ImageIcon("Slide02.png"));
     listIcons.add(new ImageIcon("Slide03.png"));
     listIcons.add(new ImageIcon("Slide04.png"));
     listIcons.add(new ImageIcon("Slide05.png"));
     listIcons.add(new ImageIcon("Slide06.png"));
     listIcons.add(new ImageIcon("Slide07.png"));
     JLabel slide = new JLabel(listIcons.get(0));
     int numberOfSlides = listIcons.size();
     slide.setVisible(true);
     slide.setBounds(120, -15, 800, 600);
     frameIntro.add(slide);

     // display pqge number
     JLabel pageNumber = new JLabel("1/7");
     pageNumber.setVisible(true);
     pageNumber.setBounds(770, 600, 100, 40);
     frameIntro.add(pageNumber);


     // creating next buttons to move between slides ahead
     JButton nextButton = new JButton("Next ↪");
     nextButton.setVisible(true);
     nextButton.setBounds(800, 600, 100, 40);
     frameIntro.add(nextButton);
     nextButton.addActionListener( new ActionListener()
     {
         public void actionPerformed(ActionEvent e)
         {
           indexSlide++;
           if (indexSlide >= numberOfSlides) indexSlide = 0; // go to first slide, avoid indexOutofBounds
           slide.setIcon(listIcons.get(indexSlide));
           pageNumber.setText(String.format("%d/7",indexSlide+1));
         }
     });

     // creating back buttons to move between slides
     JButton backButton = new JButton("↩ Back");
     backButton.setVisible(true);
     backButton.setBounds(100, 600, 100, 40);
     frameIntro.add(backButton);
     backButton.addActionListener( new ActionListener()
     {
         public void actionPerformed(ActionEvent e)
         {
           indexSlide--;
           if (indexSlide < 0) indexSlide = numberOfSlides-1; // go to last slide, avoid indexOutofBounds
           slide.setIcon(listIcons.get(indexSlide));
           pageNumber.setText(String.format("%d/7",indexSlide+1));
         }
     });

     // creating a main manu button that returns the user to the initial slide
     JButton menuButton = new JButton("⌂ Menu");
     menuButton.setVisible(true);
     menuButton.setBounds(400, 600, 100, 40);
     frameIntro.add(menuButton);
     menuButton.addActionListener( new ActionListener()
     {
         public void actionPerformed(ActionEvent e)
         {
                launchMainMenu();
                frameIntro.dispose();
         }
     });

     // creating a quiz button that takes the user to a little quiz abotu options
     JButton quizButton = new JButton("Quiz ✍");
     quizButton.setVisible(true);
     quizButton.setBounds(500, 600, 100, 40);
     frameIntro.add(quizButton);
     quizButton.addActionListener( new ActionListener()
     {
         public void actionPerformed(ActionEvent e)
         {
                launchQuiz();
                frameIntro.dispose();
         }
     });


     frameIntro.setVisible(true);
    }
  }


  // function to make a new window frame for Model
  public static void launchPricing () {
    JFrame framePricing = new JFrame("Option Pricing Model");

    // setting up the new frame window
    framePricing.setLayout(null);
    framePricing.setSize(600, 600);
    framePricing.setResizable(false);
    framePricing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePricing.setLocationRelativeTo(null);
    framePricing.getContentPane().setBackground(Color.WHITE);

    //// COMPUTATION
    // image for the computation
    ImageIcon imComp = new ImageIcon("calculation.gif");
    JLabel imComp1 = new JLabel(imComp);
    imComp1.setBounds(300, 40, 300, 200);
    framePricing.add(imComp1);
    imComp1.setVisible(true);

    // label for Computation section
    JLabel computationLabel = new JLabel("Go to Computation ➥");
    computationLabel.setBounds(50, 10, 200, 50);
    framePricing.add(computationLabel);
    computationLabel.setFont(new Font("Serif", Font.BOLD, 20));
    computationLabel.setForeground(Color.BLUE);
    computationLabel.setVisible(true);

    // button for calculating optiong price
    JButton computeOptionPriceButton = new JButton("Compute Option Price");
    computeOptionPriceButton.setVisible(true);
    computeOptionPriceButton.setBounds(40, 60, 200, 40);
    framePricing.add(computeOptionPriceButton);
    computeOptionPriceButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame f = new ComputeOptionPrice(120, 100, 5, 0.005, 0.001, 0.1);
        }
    });

    // button for calculating implied volatility
    JButton computeImpliedVolatilityButton = new JButton("Compute Implied Volatility");
    computeImpliedVolatilityButton.setVisible(true);
    computeImpliedVolatilityButton.setBounds(40, 120, 200, 40);
    framePricing.add(computeImpliedVolatilityButton);
    computeImpliedVolatilityButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame f = new ComputeImpliedVol(120, 120, 1, 0.005, 0, 5);
        }
    });

    // button for calculating the greeks
    JButton computeGreeksButton = new JButton("Compute Greeks");
    computeGreeksButton.setVisible(true);
    computeGreeksButton.setBounds(40, 180, 200, 40);
    framePricing.add(computeGreeksButton);
    computeGreeksButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame f = new ComputeGreeks(120, 100, 5, 0.005, 0.001, 0.1);
        }
    });


    //// SIMULATION
    // image for the simulation
    ImageIcon imSim = new ImageIcon("simulationblue.png");
    JLabel imSim1 = new JLabel(imSim);
    imSim1.setBounds(290, 315, 300, 200);
    framePricing.add(imSim1);
    imSim1.setVisible(true);

    // label for Computation section
    JLabel simulationLabel = new JLabel("Go to Simulation ➥");
    simulationLabel.setBounds(50, 275, 200, 50);
    framePricing.add(simulationLabel);
    simulationLabel.setFont(new Font("Serif", Font.BOLD, 22));
    simulationLabel.setForeground(Color.BLUE);
    simulationLabel.setVisible(true);


    // button for calculating optiong price
    JButton simulateGBMbutton = new JButton("Geometric Brownian Motion");
    simulateGBMbutton.setVisible(true);
    simulateGBMbutton.setBounds(40, 330, 200, 40);
    framePricing.add(simulateGBMbutton);
    simulateGBMbutton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame simuGBM = new GBM();
        }
    });

    // button for calculating implied volatility
    JButton payoffDiagramButton = new JButton("Payoff/Value Diagram");
    payoffDiagramButton.setBounds(40, 390, 200, 40);
    framePricing.add(payoffDiagramButton);
    payoffDiagramButton.setVisible(true);
    payoffDiagramButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame diagram = new PayoffDiagram();
        }
    });

    // button for calculating the greeks
    JButton volatilitySmileButton = new JButton("Volatility Smile");
    volatilitySmileButton.setBounds(40, 450, 200, 40);
    framePricing.add(volatilitySmileButton);
    volatilitySmileButton.setVisible(true);
    volatilitySmileButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
              JFrame smilePlot = new Smile();
        }
    });

    // button to go back to main manu
    JButton menuButtonPricing = new JButton("⌂ Menu");
    menuButtonPricing.setBounds(245, 523, 86, 43);
    framePricing.add(menuButtonPricing);
    menuButtonPricing.setVisible(true);
    menuButtonPricing.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               launchMainMenu();
               framePricing.dispose();
        }
    });


    framePricing.setVisible(true);
  }


  // function to make a new window frame for Model
  public static void launchQuiz () {
    JFrame frameQuizA = new JFrame("Test Your Acquired Knowledge!");

    // setting up the new frame window
    frameQuizA.setLayout(null);
    frameQuizA.setSize(1000, 700);
    frameQuizA.setResizable(false);
    frameQuizA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameQuizA.setLocationRelativeTo(null);

    // Quiz window 1, Quiz part 1 and 2
    ImageIcon quizApic = new ImageIcon("Slide09.png");
    JLabel quizPartA = new JLabel(quizApic);
    quizPartA.setLayout(null);
    quizPartA.setVisible(true);
    quizPartA.setBounds(-20, -70, 900, 755);
    frameQuizA.add(quizPartA);

    // Radio Buttons for true or false questions - 6 times for 6 questions
    JRadioButton trueButton1 = new JRadioButton("True");
    trueButton1.setBounds(750, 120, 70, 50);
    trueButton1.setVisible(true);
    JRadioButton falseButton1 = new JRadioButton("False");
    falseButton1.setBounds(820, 120, 70, 50);
    falseButton1.setVisible(true);
    ButtonGroup group1 = new ButtonGroup();
    group1.add(trueButton1);
    group1.add(falseButton1);
    quizPartA.add(trueButton1);
    quizPartA.add(falseButton1);

    JRadioButton trueButton2 = new JRadioButton("True");
    trueButton2.setBounds(750, 160, 70, 50);
    trueButton2.setVisible(true);
    JRadioButton falseButton2 = new JRadioButton("False");
    falseButton2.setBounds(820, 160, 70, 50);
    falseButton2.setVisible(true);
    ButtonGroup group2 = new ButtonGroup();
    group2.add(trueButton2);
    group2.add(falseButton2);
    quizPartA.add(trueButton2);
    quizPartA.add(falseButton2);

    JRadioButton trueButton3 = new JRadioButton("True");
    trueButton3.setBounds(750, 200, 70, 50);
    trueButton3.setVisible(true);
    JRadioButton falseButton3 = new JRadioButton("False");
    falseButton3.setBounds(820, 200, 70, 50);
    falseButton3.setVisible(true);
    ButtonGroup group3 = new ButtonGroup();
    group3.add(trueButton3);
    group3.add(falseButton3);
    quizPartA.add(trueButton3);
    quizPartA.add(falseButton3);

    JRadioButton trueButton4 = new JRadioButton("True");
    trueButton4.setBounds(750, 240, 70, 50);
    trueButton4.setVisible(true);
    JRadioButton falseButton4 = new JRadioButton("False");
    falseButton4.setBounds(820, 240, 70, 50);
    falseButton4.setVisible(true);
    ButtonGroup group4 = new ButtonGroup();
    group4.add(trueButton4);
    group4.add(falseButton4);
    quizPartA.add(trueButton4);
    quizPartA.add(falseButton4);

    JRadioButton trueButton5 = new JRadioButton("True");
    trueButton5.setBounds(750, 280, 70, 50);
    trueButton5.setVisible(true);
    JRadioButton falseButton5 = new JRadioButton("False");
    falseButton5.setBounds(820, 280, 70, 50);
    falseButton5.setVisible(true);
    ButtonGroup group5 = new ButtonGroup();
    group5.add(trueButton5);
    group5.add(falseButton5);
    quizPartA.add(trueButton5);
    quizPartA.add(falseButton5);

    JRadioButton trueButton6 = new JRadioButton("True");
    trueButton6.setBounds(750, 320, 70, 50);
    trueButton6.setVisible(true);
    JRadioButton falseButton6 = new JRadioButton("False");
    falseButton6.setBounds(820, 320, 70, 50);
    falseButton6.setVisible(true);
    ButtonGroup group6 = new ButtonGroup();
    group6.add(trueButton6);
    group6.add(falseButton6);
    quizPartA.add(trueButton6);
    quizPartA.add(falseButton6);



    // ComboBox for possible answers for matching pictures
    Integer[] answerOptions1 = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList1 = new JComboBox<Integer>(answerOptions1);
    answerOptionsList1.setBounds(220, 440, 60, 50);
    answerOptionsList1.setSelectedIndex(0);
    quizPartA.add(answerOptionsList1);
    answerOptionsList1.setVisible(true);

    Integer[] answerOptions2 = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList2 = new JComboBox<Integer>(answerOptions2);
    answerOptionsList2.setBounds(490, 440, 60, 50);
    answerOptionsList2.setSelectedIndex(0);
    quizPartA.add(answerOptionsList2);
    answerOptionsList2.setVisible(true);

    Integer[] answerOptions3 = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList3 = new JComboBox<Integer>(answerOptions3);
    answerOptionsList3.setBounds(220, 665, 60, 50);
    answerOptionsList3.setSelectedIndex(0);
    quizPartA.add(answerOptionsList3);
    answerOptionsList3.setVisible(true);

    Integer[] answerOptions4 = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList4 = new JComboBox<Integer>(answerOptions4);
    answerOptionsList4.setBounds(490, 665, 60, 50);
    answerOptionsList4.setSelectedIndex(0);
    quizPartA.add(answerOptionsList4);
    answerOptionsList4.setVisible(true);


    // Label and Button to check answers once questions are answered
    JLabel doneLabel = new JLabel("Check your answers.");
    doneLabel.setVisible(true);
    doneLabel.setBounds(710, 680, 300, 50);
    doneLabel.setForeground(Color.blue);
    quizPartA.add(doneLabel);

    JLabel checkAnswer1 = new JLabel("");
    checkAnswer1.setVisible(false);
    checkAnswer1.setBounds(780, 345, 150, 50);
    checkAnswer1.setForeground(Color.green);
    quizPartA.add(checkAnswer1);

    JLabel checkAnswer2 = new JLabel("");
    checkAnswer2.setVisible(false);
    checkAnswer2.setBounds(637, 547, 150, 50);
    checkAnswer2.setForeground(Color.green);
    quizPartA.add(checkAnswer2);

    JButton done1 = new JButton ("Done!");
    done1.setBounds(773, 650, 70, 50);
    done1.setForeground(Color.blue);
    quizPartA.add(done1);
    done1.setVisible(true);
    done1.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               int res = 0;
               if(falseButton1.isSelected()) res++;
               if(trueButton2.isSelected()) res++;
               if(falseButton3.isSelected()) res++;
               if(trueButton4.isSelected()) res++;
               if(trueButton5.isSelected()) res++;
               if(falseButton6.isSelected()) res++;
               if(res==6) { // first part is correct
                 checkAnswer1.setText("All correct!");
                 checkAnswer1.setForeground(Color.green);
               }
               else {
                 checkAnswer1.setText(String.format("%d incorrect", 6-res));
                 checkAnswer1.setForeground(Color.red);
               }
               checkAnswer1.setVisible(true);


               res = 0; // start over counting for second part
               if((Integer)answerOptionsList1.getSelectedItem()==4) res++;
               if((Integer)answerOptionsList2.getSelectedItem()==2) res++;
               if((Integer)answerOptionsList3.getSelectedItem()==3) res++;
               if((Integer)answerOptionsList4.getSelectedItem()==1) res++;
               if(res==4) { // first part is correct
                 checkAnswer2.setText("All correct!");
                 checkAnswer2.setForeground(Color.green);
               }
               else {
                 checkAnswer2.setText(String.format("%d incorrect", 4-res));
                 checkAnswer2.setForeground(Color.red);
               }
               checkAnswer2.setVisible(true);
        }
    });


    // button to go to the next part of the quiz
    JButton QuizBbutton = new JButton("Quiz B ☞");
    QuizBbutton.setBounds(880, 7, 86, 43);
    frameQuizA.add(QuizBbutton);
    QuizBbutton.setVisible(true);
    QuizBbutton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               launchQuizB();
               frameQuizA.dispose();
        }
    });

    // button to go back to main manu from quiz part A
    JButton QuizAbuttonMenu = new JButton("⌂ Menu");
    QuizAbuttonMenu.setBounds(880, 55, 86, 43);
    frameQuizA.add(QuizAbuttonMenu);
    QuizAbuttonMenu.setVisible(true);
    QuizAbuttonMenu.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               launchMainMenu();
               frameQuizA.dispose();
        }
    });

    frameQuizA.setVisible(true);
  }


  public static void launchQuizB () {
    JFrame frameQuizB = new JFrame("Test Your Acquired Knowledge!");

    // setting up the new frame window
    frameQuizB.setLayout(null);
    frameQuizB.setSize(1000, 700);
    frameQuizB.setResizable(false);
    frameQuizB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameQuizB.setLocationRelativeTo(null);

    // Quiz B window 2
    ImageIcon quizBpic = new ImageIcon("Slide10.png");
    JLabel quizPartB = new JLabel(quizBpic);
    quizPartB.setLayout(null);
    quizPartB.setVisible(true);
    quizPartB.setBounds(-20, -70, 900, 755);
    frameQuizB.add(quizPartB);

    // Radio Buttons for true or false questions - 4 times for 4 questions
    JRadioButton buttonQuestion1Option1 = new JRadioButton("European");
    buttonQuestion1Option1.setBounds(690, 132, 120, 30);
    buttonQuestion1Option1.setVisible(true);
    JRadioButton buttonQuestion1Option2 = new JRadioButton("American");
    buttonQuestion1Option2.setBounds(790, 132, 120, 30);
    buttonQuestion1Option2.setVisible(true);
    ButtonGroup group1a = new ButtonGroup();
    group1a.add(buttonQuestion1Option1);
    group1a.add(buttonQuestion1Option2);
    quizPartB.add(buttonQuestion1Option1);
    quizPartB.add(buttonQuestion1Option2);

    JRadioButton buttonQuestion2Option1 = new JRadioButton("BM");
    buttonQuestion2Option1.setBounds(690, 172, 120, 30);
    buttonQuestion2Option1.setVisible(true);
    JRadioButton buttonQuestion2Option2 = new JRadioButton("GBM");
    buttonQuestion2Option2.setBounds(790, 172, 120, 30);
    buttonQuestion2Option2.setVisible(true);
    ButtonGroup group1b = new ButtonGroup();
    group1b.add(buttonQuestion2Option1);
    group1b.add(buttonQuestion2Option2);
    quizPartB.add(buttonQuestion2Option1);
    quizPartB.add(buttonQuestion2Option2);

    JRadioButton buttonQuestion3Option1 = new JRadioButton("negative");
    buttonQuestion3Option1.setBounds(690, 212, 120, 30);
    buttonQuestion3Option1.setVisible(true);
    JRadioButton buttonQuestion3Option2 = new JRadioButton("differential");
    buttonQuestion3Option2.setBounds(790, 212, 120, 30);
    buttonQuestion3Option2.setVisible(true);
    ButtonGroup group1c = new ButtonGroup();
    group1c.add(buttonQuestion3Option1);
    group1c.add(buttonQuestion3Option2);
    quizPartB.add(buttonQuestion3Option1);
    quizPartB.add(buttonQuestion3Option2);

    JRadioButton buttonQuestion4Option1 = new JRadioButton("sigma");
    buttonQuestion4Option1.setBounds(690, 252, 120, 30);
    buttonQuestion4Option1.setVisible(true);
    JRadioButton buttonQuestion4Option2 = new JRadioButton("sigma square");
    buttonQuestion4Option2.setBounds(790, 252, 120, 30);
    buttonQuestion4Option2.setVisible(true);
    ButtonGroup group1d = new ButtonGroup();
    group1d.add(buttonQuestion4Option1);
    group1d.add(buttonQuestion4Option2);
    quizPartB.add(buttonQuestion4Option1);
    quizPartB.add(buttonQuestion4Option2);


    // ComboBox for possible answers for matching pictures
    Integer[] answerOptions1b = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList1b = new JComboBox<Integer>(answerOptions1b);
    answerOptionsList1b.setBounds(100, 560, 60, 50);
    answerOptionsList1b.setSelectedIndex(0);
    quizPartB.add(answerOptionsList1b);
    answerOptionsList1b.setVisible(true);

    Integer[] answerOptions2b = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList2b = new JComboBox<Integer>(answerOptions2b);
    answerOptionsList2b.setBounds(330, 560, 60, 50);
    answerOptionsList2b.setSelectedIndex(0);
    quizPartB.add(answerOptionsList2b);
    answerOptionsList2b.setVisible(true);

    Integer[] answerOptions3b = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList3b = new JComboBox<Integer>(answerOptions3b);
    answerOptionsList3b.setBounds(535, 560, 60, 50);
    answerOptionsList3b.setSelectedIndex(0);
    quizPartB.add(answerOptionsList3b);
    answerOptionsList3b.setVisible(true);

    Integer[] answerOptions4b = new Integer[] { 0,1,2,3,4 };
    JComboBox<Integer> answerOptionsList4b = new JComboBox<Integer>(answerOptions4b);
    answerOptionsList4b.setBounds(730, 560, 60, 50);
    answerOptionsList4b.setSelectedIndex(0);
    quizPartB.add(answerOptionsList4b);
    answerOptionsList4b.setVisible(true);

    JLabel checkAnswers1 = new JLabel("");
    checkAnswers1.setVisible(false);
    checkAnswers1.setBounds(745, 270, 150, 50);
    checkAnswers1.setForeground(Color.green);
    quizPartB.add(checkAnswers1);

    JLabel checkAnswers2 = new JLabel("");
    checkAnswers2.setVisible(false);
    checkAnswers2.setBounds(430, 585, 150, 50);
    checkAnswers2.setForeground(Color.green);
    quizPartB.add(checkAnswers2);

    // Label and Button to check answers once questions are answered
    JLabel doneLabel1 = new JLabel("Check your answers.");
    doneLabel1.setVisible(true);
    doneLabel1.setBounds(710, 680, 300, 50);
    doneLabel1.setForeground(Color.blue);
    quizPartB.add(doneLabel1);

    JButton done2 = new JButton ("Done!");
    done2.setBounds(770, 650, 70, 50);
    done2.setForeground(Color.blue);
    quizPartB.add(done2);
    done2.setVisible(true);
    done2.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               int res = 0;
               if(buttonQuestion1Option1.isSelected()) res++;
               if(buttonQuestion2Option2.isSelected()) res++;
               if(buttonQuestion3Option2.isSelected()) res++;
               if(buttonQuestion4Option1.isSelected()) res++;
               if(res==4) { // first part is correct
                 checkAnswers1.setText("All correct!");
                 checkAnswers1.setForeground(Color.green);
               }
               else {
                 checkAnswers1.setText(String.format("%d incorrect", 4-res));
                 checkAnswers1.setForeground(Color.red);
               }
               checkAnswers1.setVisible(true);


               res = 0; // start over counting for second part
               if( (Integer) answerOptionsList1b.getSelectedItem()==2) res++;
               if( (Integer) answerOptionsList2b.getSelectedItem()==1) res++;
               if( (Integer) answerOptionsList3b.getSelectedItem()==4) res++;
               if( (Integer) answerOptionsList4b.getSelectedItem()==3) res++;
               if(res==4) { // first part is correct
                 checkAnswers2.setText("All correct!");
                 checkAnswers2.setForeground(Color.green);
               }
               else {
                 checkAnswers2.setText(String.format("%d incorrect", 4-res));
                 checkAnswers2.setForeground(Color.red);
               }
               checkAnswers2.setVisible(true);
        }
    });


    // button to go back to part A of the quiz
    JButton QuizAbutton = new JButton("☜ Quiz A");
    QuizAbutton.setBounds(880, 7, 86, 43);
    frameQuizB.add(QuizAbutton);
    QuizAbutton.setVisible(true);
    QuizAbutton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               launchQuiz();
               frameQuizB.dispose();
        }
    });

    // button to go back to main manu from quiz part B
    JButton QuizBbuttonMenu = new JButton("⌂ Menu");
    QuizBbuttonMenu.setBounds(880, 55, 86, 43);
    frameQuizB.add(QuizBbuttonMenu);
    QuizBbuttonMenu.setVisible(true);
    QuizBbuttonMenu.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
               launchMainMenu();
               frameQuizB.dispose();
        }
    });

    frameQuizB.setVisible(true);
  }


}
