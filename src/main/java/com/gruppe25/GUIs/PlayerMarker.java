package com.gruppe25.GUIs;

import com.gruppe25.ModelClasses.Player;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerMarker extends Group {
  public PlayerMarker(Player player, Color color) {
    Circle circle = new Circle(7);
    circle.setFill(color);
    circle.setStroke(Color.BLACK);
    this.getChildren().add(circle);
  }
}
