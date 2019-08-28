# Black-Scholes Option Pricing Model
### Simulation of a Geometric Brownian Motion with Java
---

**Ester Hlav, May 2017**

This program is an *Option Pricing Model* based on the *Black-Scholes formula*. It includes both an **Option Pricing Calculator** as well as a  **Geometric Brownian Motion Simulator** based on a random generator. 
Additionaly, based on parameters given by the user, the program can calculate **implied volatility** and **Greeks** (*i.e.* the derivatives of an option value regarding the different factors such as stock price, strike price, volatility etc.) as well as output diagrams of **option payoff** and **volatility smile**. 
All calculated and simulated outputs are well-known concepts in the field of quantitative finance and derivative contracts and are using parameters from the Black-Scholes formula.
Finally, a GUI with a brief introduction to options and option pricing was developed for users new to the field of quantiative finance.


Figure1
[Figure1](Figure1.png)


---

## Modules

### PART A - Introduction to Calls and Puts
 - Slides
 - Quizz
[Figure2](Figure2.png)

### Demo
![gif animation](https://github.com/EsterHlav/Black-Scholes-Option-Pricing-Model/raw/master/QuizzHD.gif "overview")

### PART B - Option Pricing Calcualtor and Brownian Motion Simulator
[Figure4](Figure4.png)
#### 1- Computation
 - Computing the Option Price
 - Computing the Implied Volatility
 - Computing the Greeks

#### 2 - Simulation
 - Geometric Brownian Motion
 - Payoff/Value Diagram
 - Volatility Smile

### Demo
![gif animation](https://github.com/EsterHlav/Black-Scholes-Option-Pricing-Model/raw/master/OptionPricing.gif "overview")

## Run the code
In directory ```/Code/```.

To compile:
```bash
$ javac OptionPricing.java
```

To run:
```bash
$ java OptionPricing
```
