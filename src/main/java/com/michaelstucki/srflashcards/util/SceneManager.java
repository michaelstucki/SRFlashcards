package com.michaelstucki.srflashcards.util;

import com.michaelstucki.srflashcards.controller.*;
import com.michaelstucki.srflashcards.dao.Dao;
import com.michaelstucki.srflashcards.dto.Deck;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.michaelstucki.srflashcards.constants.Constants.height;
import static com.michaelstucki.srflashcards.constants.Constants.width;

/**
 * SceneManager Singleton to create scenes and present them
 * It runs FXMLLoader once for each UI and caches their associated scenes and
 * FXMLLoader references for select UIs to give access to their controller references.
 * @author Michael Stucki
 * @version 1.0
 * @since 2025-09-21
 */
// Marked final to prevent extension
public enum SceneManager {
    /**
     * Single declared constant; so the sole instance of this enum; so a singleton
     */
    SCENE_MANAGER;

    // interface reference to Data Access Object (Dao)
    Dao dao;

    private Stage stage;

    // Cache of UI scenes
    private final Map<String, Scene> sceneCache = new HashMap<>();

    // Cache of UI FXMLLoaders
    private final Map<String, FXMLLoader> loaderCache = new HashMap<>();

    // The deck of interest selected in Decks UI and shared with Cards UI (and elsewhere as needed)
    private Deck sharedDeck = new Deck("");

    // Constructor is implicitly private; called once when JVM loads enum to create its instances (here only one)
    SceneManager() {}

    /**
     * Set Dao reference
     * @param dao Dao reference
     */
    public void setDao(Dao dao) { this.dao = dao; }

    /**
     * Set the JavaFX stage
     * @param stage Stage instance
     */
    public void setStage(Stage stage) { this.stage = stage; }

    /**
     * Set shared deck reference
     * @param sharedDeck Deck instance shared by select UI controllers
     */
    public void setSharedDeck(Deck sharedDeck) { this.sharedDeck = sharedDeck; }

    /**
     * Create, cache, and present UI scenes and FXMLLoaders (to make app responsive to scene changes)
     * @param fxmlPath full path to .fxml files defined in @{Constants} class and passed in here by main
     */
    public void showView(String fxmlPath) {
        try {
            if (sceneCache.containsKey(fxmlPath)) {
                FXMLLoader loader = loaderCache.get(fxmlPath);
                // invoke controller init() only if FXMLLoader has been run
                if (fxmlPath.contains("cards")) {
                    ControllerCards controller = loader.getController();
                    controller.init(sharedDeck);
                } else if (fxmlPath.contains("drills")) {
                    ControllerDrills controller = loader.getController();
                    controller.init(sharedDeck);
                } else if (fxmlPath.contains("decks")) {
                    ControllerDecks controller = loader.getController();
                    controller.init();
                } else if (fxmlPath.contains("home")) {
                    ControllerHome controller = loader.getController();
                    controller.init();
                }
                Scene scene = sceneCache.get(fxmlPath);
                stage.setScene(scene);
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                loader.setControllerFactory(controllerClass -> {
                    // inject dependencies into custom controller constructors
                    if (fxmlPath.contains("welcome")) return new ControllerWelcome(SCENE_MANAGER);
                    else if (fxmlPath.contains("cards")) return new ControllerCards(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("change_password")) return new ControllerChangePassword(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("create_account")) return new ControllerCreateAccount(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("decks")) return new ControllerDecks(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("delete_account")) return new ControllerDeleteAccount(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("drills")) return new ControllerDrills(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("forgot_password")) return new ControllerForgotPassword(SCENE_MANAGER, dao);
                    else if (fxmlPath.contains("home")) return new ControllerHome(SCENE_MANAGER, dao);
                    return null;
                });

                Scene scene = new Scene(loader.load(), width, height);
                sceneCache.put(fxmlPath, scene);
                loaderCache.put(fxmlPath, loader);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exit app
     */
    public void exit() { stage.close(); }
}
