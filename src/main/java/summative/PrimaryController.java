package summative;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

public class PrimaryController {

    private Stage stage;
    private Image originalImage; // Use this to keep track of the original image

    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem openImage;

    @FXML
    private MenuItem saveImage;
    
    @FXML
    private MenuItem restore;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem horizontalFlip;
    
    @FXML
    private MenuItem verticalFlip;

    @FXML
    private MenuItem rotation;

    @FXML
    private MenuItem grayScale;
    
    @FXML
    private MenuItem sepia;

    @FXML
    private MenuItem invertColour;
    
    @FXML
    private MenuItem brightness;
    
    @FXML
    private MenuItem bulge;

    @FXML
    private MenuItem colourOverlay;

    @FXML
    private MenuItem pixelation;

    @FXML
    private MenuItem vignette;

    @FXML
    private MenuItem edgeDetection;

    @FXML
    private MenuItem emboss;
    
    @FXML
    void onOpenImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif"));

        try {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                originalImage = image;
                imageView.setImage(image);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Image Load Failed");
            alert.setContentText("There was a problem opening your image");
            alert.showAndWait();
        }
    }

    @FXML
    public void onSaveImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
        File file = fileChooser.showSaveDialog(imageView.getScene().getWindow());

        if (file != null) {
            WritableImage writableImage = imageView.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Image Save Failed");
                alert.setContentText("There was a problem saving your image");
                alert.showAndWait();
            }
        }
    }

    // @FXML
    // Image onRestore(ActionEvent event) {
    //     // int width = (int) imageView.getImage().getWidth();
    //     // int height = (int) imageView.getImage().getHeight();

    // // }

    // @FXML
    // void onExit(ActionEvent event) {
        
    // }

    @FXML
    void onHorizontalFlip(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                writer.setColor(width - i - 1, j, reader.getColor(i, j));
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onVerticalFlip(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                writer.setColor(i, height - j - 1, reader.getColor(i, j));
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onRotation(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                writer.setColor(j, height - i - 1, reader.getColor(i, j));
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onGrayScale(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();

                double gray = red * 0.21 + green * 0.71 + blue * 0.07;
                Color newColor = new Color(gray, gray, gray, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onSepia(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();

                double newRed = red * (0.393 * red + 0.769 * green + 0.189 * blue);
                double newGreen = green * (0.348 * red + 0.686 * green + 0.168 * blue);
                double newBlue = blue * (0.272 * red + 0.534 * green + 0.131 * blue);
                
                double sepia = Math.min(newRed + newGreen + newBlue, 1.0);
                Color newColor = new Color(sepia, sepia, sepia, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onInvertColour(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();

                Color newColor = new Color(1.0 - red, 1.0 - green, 1.0 - blue, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onBrightness(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        double brightness = 0.2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                double red = Math.min(color.getRed() + color.getRed() * brightness, 1.0);
                double green = Math.min(color.getGreen() + color.getGreen() * brightness, 1.0);
                double blue = Math.min(color.getBlue() + color.getBlue() * brightness, 1.0);

                Color newColor = new Color(red, green, blue, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    // @FXML
    // void onBulge(ActionEvent event) {

    // }

    // @FXML
    // void onColourOverlay(ActionEvent event) {

    // }

    // @FXML
    // void onPixelation(ActionEvent event) {

    // }

    // @FXML
    // void onVignette(ActionEvent event) {

    // }

    // @FXML
    // void onEdgeDetection(ActionEvent event) {

    // }

    // @FXML
    // void onEmboss(ActionEvent event) {

    // }

    /*
     * Accessing a pixels colors
     * 
     * Color color = reader.getColor(x, y);
     * double red = color.getRed();
     * double green = color.getGreen();
     * double blue = color.getBlue();
     */

    /*
     * Modifying a pixels colors
     * 
     * Color newColor = new Color(1.0 - red, 1.0 - green, 1.0 - blue,
     * color.getOpacity());
     */

    // DO NOT REMOVE THIS METHOD!
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
