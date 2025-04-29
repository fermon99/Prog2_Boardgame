package com.gruppe25.GUIs;

import java.util.concurrent.atomic.AtomicInteger;

import com.gruppe25.Controllers.SnakeLadderController;
import com.gruppe25.ModelClasses.Player;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WinnerGUI {

  private SnakeLadderController controller;

  public static int showAndWait(Player winner) {
    Stage winnerStage = new Stage();
    winnerStage.initModality(Modality.APPLICATION_MODAL);
    winnerStage.setTitle("Winner window");

    VBox layout = new VBox(10);
    layout.setAlignment(Pos.CENTER);

    Label winnerLabel = new Label("The winner is " + winner + "!");
    Button newGameButton = new Button("New game");
    Button mainMenuButton = new Button("Main menu");
    layout.getChildren().addAll(winnerLabel, newGameButton, mainMenuButton);

    Scene scene = new Scene(layout, 300, 200);
    winnerStage.setScene(scene);

    AtomicInteger result = new AtomicInteger(0);

    newGameButton.setOnAction(e -> {
        result.set(1);
        winnerStage.close();
    });
    mainMenuButton.setOnAction(e -> {
        result.set(2);
        winnerStage.close();
    });

    winnerStage.showAndWait();
    return result.get();
  }
}
