package com.gruppe25.GUIs;

import java.util.ArrayList;
import java.util.List;

import com.gruppe25.ModelClasses.Player;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* View class for 'new game'.
 * Opens pop-up with player selection.
 */
public class NewGameGUI {
  public static List<Player> showAndWait(List<Player> availablePlayers) {
    Stage newGameStage = new Stage();
    newGameStage.initModality(Modality.APPLICATION_MODAL);
    newGameStage.setTitle("Select players");

    VBox layout = new VBox(10);
    
    /* Checkbox for selecting players */
    List<CheckBox> selections = new ArrayList<>();
    for (Player player : availablePlayers) {
      CheckBox selection = new CheckBox(player.getName());
      layout.getChildren().add(selection);
      selections.add(selection);
    }

    Button startButton = new Button("Start Game");
    layout.getChildren().add(startButton);

    List<Player> selectedPlayers = new ArrayList<>();

    /* Logic for adding selected players */
    startButton.setOnAction(e -> {
      for (int i = 0; i < selections.size(); i++) {
        if (selections.get(i).isSelected()) {
          selectedPlayers.add(availablePlayers.get(i));
        }
      }
      newGameStage.close();
    });

    Scene scene = new Scene(layout, 300, 400);
    newGameStage.setScene(scene);
    newGameStage.showAndWait();

    /* Logic for max 4 players per game */
    if (selectedPlayers.size() <= 4) {
      return selectedPlayers;
    } else {
      System.out.println("Too many players selected");
      return null;
    }
  }
}
