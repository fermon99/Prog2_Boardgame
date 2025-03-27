package com.gruppe25;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class PlayerReader {
  public static List<Player> readPlayersFromCSV(String filename) {
    List<Player> players = new ArrayList<>();

    try (CSVReader reader = new CSVReader(new FileReader(filename))) {
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {
          String name = nextLine[0];
          players.add(new Player(name));
        }
    } catch (IOException | NumberFormatException | CsvValidationException e) {
    } 
    return players;
  }
}
