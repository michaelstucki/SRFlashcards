package com.michaelstucki.srflashcards.constants;

/**
 * Defines constants used throughout application
 * @author Michael Stucki
 * @version 1.0
 * @since 2025-09-21
 */

public final class Constants {
    private static final double ASPECT_RATIO = 1.2;

    /**
     * Screen height
     */
    public static final double height = 620;

    /**
     * Screen width
     */
    public static final double width = height * ASPECT_RATIO;

    /**
     * Names of fonts used
     */
    public static final String[] fonts = {"HerculanumLTProRoman.TTF", "EBGaramond-Regular.ttf",
            "EBGaramond-Italic.ttf", "EBGaramond-Bold.ttf"};

    /**
     * Names of FXML files
     */
    public static final String[] fxmls = {"delete_account", "welcome", "drills", "cards", "decks", "change_password",
            "forgot_password", "create_account", "home"};

    /**
     * Token to parse cards in listView
     */
    public static final String cardToken = "::";

    /**
     * Name of Database
     */
    public static final String databaseName = "/.flashcards.db";

    /**
     * Name of Table for Users
     */
    public static final String usersTable = "users";

    /**
     * Name of Table for Decks
     */
    public static final String decksTable = "decks";

    /**
     * Name of Table for Cards
     */
    public static final String cardsTable = "cards";
    private Constants() {}
}
