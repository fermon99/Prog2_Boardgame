package com.gruppe25;

public class Die {
  private int lastRolledValue;

  public Die() {
    
  }

  public int roll() {
    lastRolledValue = (int)((Math.random() * 6 + 1));
    return lastRolledValue;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
