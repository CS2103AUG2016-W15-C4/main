//@@author A0139772U
package seedu.whatnow.ui;

import java.awt.Color;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.whatnow.commons.util.FxViewUtil;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class StatusPanel extends UiPart {
    public static final String STATUS_DISPLAY_ID = "statusPanel";
    private static final String STATUS_BAR_STYLE_SHEET = "status-panel";
    private TextArea statusDisplayArea;
    private final StringProperty displayed = new SimpleStringProperty("");

    private static final String FXML = "StatusPanel.fxml";

    private AnchorPane placeHolder;

    private AnchorPane mainPane;

    public static StatusPanel load(Stage primaryStage, AnchorPane placeHolder) {
        StatusPanel statusBar = UiPartLoader.loadUiPart(primaryStage, placeHolder, new StatusPanel());
        statusBar.configure();
        return statusBar;
    }

    public void configure() {
        GridPane grid = new GridPane();

        Image image = new Image("/images/WhatNowWhiteOnBlack.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(350);
        iv1.setFitHeight(200);
        statusDisplayArea = new TextArea();
        statusDisplayArea.setEditable(false);
        statusDisplayArea.setId(STATUS_DISPLAY_ID);
        statusDisplayArea.textProperty().bind(displayed);
        statusDisplayArea.setMaxWidth(350);
        statusDisplayArea.setMinHeight(60);
        statusDisplayArea.setMaxHeight(60);
        statusDisplayArea.setPadding(Insets.EMPTY);
        statusDisplayArea.setFont(Font.font(15));
        statusDisplayArea.setBorder(Border.EMPTY);
        grid.addRow(0, iv1);
        grid.addRow(1, statusDisplayArea);  
        placeHolder.getChildren().add(grid);
    }

    @Override
    public void setNode(Node node) {
        mainPane = (AnchorPane) node;
    }

    @Override
    public void setPlaceholder(AnchorPane placeholder) {
        this.placeHolder = placeholder;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    public void postMessage(String message) {
        displayed.setValue(message);
    }

}
