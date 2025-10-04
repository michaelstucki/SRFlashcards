package com.michaelstucki.srflashcards;

import com.michaelstucki.srflashcards.constants.Constants;
import com.michaelstucki.srflashcards.dao.Dao;
import com.michaelstucki.srflashcards.dao.DaoSQLite;
import com.michaelstucki.srflashcards.util.SceneManager;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class
 * @author Michael Stucki
 * @version 1.0
 * @since 2025-09-21
 */
public class Main extends Application {
    Dao dao = DaoSQLite.DAO; // interface reference to it class-specific implementation

    @Override
    public void start(Stage stage) throws IOException {
        // Enable use of fonts
        for (String font : Constants.fonts) Font.loadFont(getClass().getResourceAsStream("/fonts/" + font), 10);

        // Get reference to SceneManager singleton to manager changing scenes
        SceneManager sceneManager = SceneManager.SCENE_MANAGER;
        sceneManager.setDao(dao); // inject Dao dependency into the SceneManager enum singleton
        sceneManager.setStage(stage);

        // Create and cache UI scenes and FXMLLoaders
        for (String fxml: Constants.fxmls) sceneManager.showView("/fxml/" + fxml + ".fxml");
        sceneManager.showView("/fxml/home.fxml");

        // Make app fixed in size
        stage.setResizable(false);
        stage.show();

        // Create on-exit handler
        stage.setOnCloseRequest(event -> {
            sceneManager.exit();
        });
    }
}
