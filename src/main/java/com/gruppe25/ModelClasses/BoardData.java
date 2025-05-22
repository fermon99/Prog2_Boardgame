package com.gruppe25.ModelClasses;

import java.util.List;
import java.util.Map;

/* Class for storing data about the board to be created from a .json file */

public class BoardData {
  String BoardgameName;
  String Description;
  int numberOfTiles;
  boolean loopableBoard;
  int numberOfDice;
  List<Map<String, Object>> actionTiles;
}
