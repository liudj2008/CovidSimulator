# CovidSimulator
A Java-based simulator to simulate the spread of Covid-19 in an indoor environment

# Scenario
People are shopping in an indoor market. The market has only one entrance/exit. The number of people in the market (number of people in the market) is constant: Once people reach the enter/exit, they will exit and will not be back. Meanwhile, the same amount of new people will enter the market. Suppose there is one random person infected by Covid-19, and he/she coughs/sneezes only once during the shopping time. We would like to know how many new people (R0) are infected by this initial Covid-19 carrier and how fast it is to have the first new infection, in order to evaluate the mask effectiveness.

# How to use the model
1. Go to Run folder
2. Run Java program in terminal 

   Java Simulator [numOfPeople] [initialSick] [path] [maskPercent] [maskSickPercent] [unMaskSickPercent]
   
   It is noted that Simulator has five required parameters:
   
   numOfPeople: Number of people in the matrix (Market Capacity)
   initialSick: Number of initial infected people in the market
   path: save path for output files
   maskPercent: Percentage of people wearing masks (scale at 0 - 100)
   maskSickPercent: Probability of being infected if wearing masks (scale at 0 - 100)
   unMaskPercent: Probability of being infected if not wearing masks (scale at 0 -100)
   
3. The model will generate output files in the selcted path folder with 1000 output files, each file holds snapshots of health conditions for 100 steps

# Acknowledgement
Specially thanks to Victoria Yi in collaboration in the project.
