package com.gruppe25;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.gruppe25.ModelClasses.Dice;

public class DiceTest {
  Dice dice = new Dice(2);

  @Test
  void testDice() {
    int result = dice.roll();
    int roll1 = dice.getDie(1);
    int roll2 = dice.getDie(2);
    assertEquals(result, roll1 + roll2);
  }
}
