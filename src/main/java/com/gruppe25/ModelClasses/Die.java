package com.gruppe25.ModelClasses;

/* Class for the object 'die', used to roll amount of steps in boardgame*/

public class Die {
  private int lastRolledValue;

  public Die() {
    
  }

  /* Method for rolling a die individually */
  public int roll() {
    lastRolledValue = (int)((Math.random() * 6 + 1));
    return lastRolledValue;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
