package pt.pa.refactoring;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pt.pa.refactoring.model.Review;
import pt.pa.refactoring.model.Reviews;

import java.util.List;
import java.util.ArrayList;

/**
 * @author amfs
 */
public class MainGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private GridPane createForm(String title, List<LabelValue> labelValues) {
        GridPane form = new GridPane();
        Label labelTitle = new Label(title);
        labelTitle.setStyle("-fx-font-weight: bold");
        form.add(labelTitle, 0, 0);
        for (int i = 0; i < labelValues.size(); i++) {
            Label label = new Label(labelValues.get(i).getLabel());
            form.add(label, 0, i + 1);
            TextField textField = new TextField();
            form.add(textField, 1, i + 1);
        }
        return form;
    }

    private class LabelValue {
        private String label;
        private String value;

        public LabelValue(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Review Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Reviews reviews = new Reviews();
        ListView<Review> listViewReviews = new ListView<>();

        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(borderPane,700,600);

        // Create the product image
        Image image = new Image("product.jpg");
        ImageView imageView = new ImageView(image);

        // Create the product description grid pane
        GridPane productDescriptionGridPane = new GridPane();
        productDescriptionGridPane.add(new Label("Product name:"), 0, 0);
        productDescriptionGridPane.add(new Label("Release date:"), 0, 1);
        productDescriptionGridPane.add(new Label("CPU:"), 0, 2);
        productDescriptionGridPane.add(new Label("RAM:"), 0, 3);
        productDescriptionGridPane.add(new Label("Storage:"), 0, 4);
        productDescriptionGridPane.add(new Label("Weight:"), 0, 5);
        productDescriptionGridPane.add(new Label("Battery:"), 0, 6);
        productDescriptionGridPane.add(new Label("Portátil XPTO"), 1, 0);
        productDescriptionGridPane.add(new Label("January de 2022"), 1, 1);
        productDescriptionGridPane.add(new Label("Intel Core i7 @ 3.00GHz"), 1, 2);
        productDescriptionGridPane.add(new Label("16GB"), 1, 3);
        productDescriptionGridPane.add(new Label("1TB SSD NVMe M.2"), 1, 4);
        productDescriptionGridPane.add(new Label("1.3kg"), 1, 5);
        productDescriptionGridPane.add(new Label("65Wh"), 1, 6);

        GridPane reviewFormGridPane = new GridPane();
        reviewFormGridPane.add(new Label("Your name:"), 0, 0);
        reviewFormGridPane.add(new Label("Text:"), 0, 1);
        reviewFormGridPane.add(new Label("Rating (0 to 5):"), 0, 2);
        TextField textFieldName = new TextField();
        reviewFormGridPane.add(textFieldName, 1, 0);
        TextField textFieldText = new TextField();
        reviewFormGridPane.add(new TextField(),1,1);
        TextField textFieldRating = new TextField();
        reviewFormGridPane.add(new TextField(),1,2);

        Button buttonAddReview = new Button("Add");
        reviewFormGridPane.add(buttonAddReview, 1, 4);

        HBox hBoxProduct = new HBox(5);
        hBoxProduct.getChildren().addAll(imageView, productDescriptionGridPane, reviewFormGridPane);
        
        borderPane.setTop(hBoxProduct);

        // Reviews list
        GridPane gridPaneReviews = new GridPane();
        Label labelReviewsList = new Label(String.format("Reviews list (%d)", reviews.getTotal()));
        labelReviewsList.setStyle("-fx-font-weight: bold");

        gridPaneReviews.add(labelReviewsList, 0, 0);
        gridPaneReviews.add(listViewReviews, 0, 1);
        listViewReviews.setMinWidth(scene.getWidth());

        borderPane.setCenter(gridPaneReviews);

        buttonAddReview.setOnAction((ActionEvent e) -> {
            if (textFieldName.getText().isEmpty() || textFieldRating.getText().isEmpty() || textFieldText.getText().isEmpty()) {
                showErrorAlert("Empty fields");
            } else {
                try {
                    String name = textFieldName.getText();
                    String text = textFieldText.getText();
                    double rating = Double.parseDouble(textFieldRating.getText());

                    if (rating < 0 || rating > 5) {
                        showErrorAlert("Invalid rating");
                        return;
                    }

                    reviews.add(new Review(name, text, rating));
                    listViewReviews.getItems().clear();
                    listViewReviews.getItems().addAll(reviews);

                    textFieldName.clear();
                    textFieldText.clear();
                    textFieldRating.clear();
                    labelReviewsList.setText(String.format("Reviews list (%d) . Average rating (%.1f)", reviews.getTotal(), reviews.getAvgRating()));
                } catch (NumberFormatException nfe) {
                    showErrorAlert("invalid format");
                }
            }
        });

        stage.setTitle("Product review");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

