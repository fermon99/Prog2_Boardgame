package com.gruppe25.ModelClasses;

import java.util.ArrayList;
import java.util.List;

/* Class for making a set of dice based on amount for boardgame */

public class Dice {
  private final List<Die> dice;

  public Dice(int numberOfDice) {
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  /* Method for rolling all dice */
  public int roll() {
    int result = 0;
    for (Die die : dice) {
      result += die.roll();
    }
    return result;
  }

  /* Getters */
  public int getDie(int dieNumber) {
    return dice.get(dieNumber-1).getValue();
  }

}
