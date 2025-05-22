package com.gruppe25.ModelClasses;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/* Class for reading players from a .csv file */

public class PlayerReader {
  public static List<Player> readPlayersFromCSV(String filename, BoardGame boardGame) {
    List<Player> players = new ArrayList<>();

    try (CSVReader reader = new CSVReader(new FileReader(filename))) {
      String[] nextLine;

      /* Adds all players listed in file to a list (is further used when selecting players) */
      while ((nextLine = reader.readNext()) != null) {
        String name = nextLine[0];
        players.add(new Player(name, boardGame));
      }
    } catch (IOException | NumberFormatException | CsvValidationException e) {
    } 
    return players;
  }
}
