package com.gruppe25.GUIs;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.gruppe25.Controllers.QuestionController;
import com.gruppe25.ModelClasses.Player;
import com.gruppe25.ModelClasses.Question;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* View class for pop-up question screen */

public class QuestionGUI {

  private static QuestionController controller;

  public QuestionGUI(QuestionController controller) {
    this.controller = controller;
  }

  public static String question(Player player, String category, Question question) {
    Stage questionStage = new Stage();
    questionStage.initModality(Modality.APPLICATION_MODAL);
    questionStage.setTitle("Question");

    VBox layout = new VBox(10);
    layout.setAlignment(Pos.CENTER);

    /* Questioncard text */
    Label questionTitle = new Label(player + ", you landed on the " + category + " category!");
    Label questionText = new Label(question.getQuestionText());

    /* Displaying buttons with answer options */
    HBox optionLayout = new HBox(20);
    optionLayout.setAlignment(Pos.CENTER);
    List<String> options = question.getOptions();
    Button option1 = new Button(options.get(0));
    Button option2 = new Button(options.get(1));
    Button option3 = new Button(options.get(2));
    Button option4 = new Button(options.get(3));
    optionLayout.getChildren().addAll(option1, option2, option3, option4);
    

    layout.getChildren().addAll(questionTitle, questionText, optionLayout);

    Scene scene = new Scene(layout, 600, 300);
    questionStage.setScene(scene);

    /* Updates returnable result based on option pressed */
    AtomicReference<String> selectedAnswer = new AtomicReference<>();

    option1.setOnAction(e ->  {
      selectedAnswer.set(options.get(0));
      questionStage.close();
    });
    option2.setOnAction(e ->  {
      selectedAnswer.set(options.get(1));
      questionStage.close();
    });
    option3.setOnAction(e ->  {
      selectedAnswer.set(options.get(2));
      questionStage.close();
    });
    option4.setOnAction(e ->  {
      selectedAnswer.set(options.get(3));
      questionStage.close();
    });

    questionStage.showAndWait();
    String result = selectedAnswer.get();
    
    return result;
  }
}
