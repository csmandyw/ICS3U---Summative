package summative;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private MenuItem invertColor;

    @FXML
    private MenuItem brightness;

    @FXML
    private MenuItem bulge;

    @FXML
    private MenuItem colorOverlay;

    @FXML
    private MenuItem pixelation;

    @FXML
    private MenuItem vignette;

    @FXML
    private MenuItem edgeDetection;

    @FXML
    private MenuItem emboss;

    @FXML
    private MenuItem noise;

    @FXML
    private Slider slider;

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

    @FXML
    void onRestore(ActionEvent event) {
        imageView.setImage(originalImage);
    }

    @FXML
    void onExit(ActionEvent event) {
        javafx.application.Platform.exit();
    }

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

                double newRed = Math.min(0.393 * red + 0.769 * green + 0.189 * blue, 1.0);
                double newGreen = Math.min(0.348 * red + 0.686 * green + 0.168 * blue, 1.0);
                double newBlue = Math.min(0.272 * red + 0.534 * green + 0.131 * blue, 1.0);

                Color newColor = new Color(newRed, newGreen, newBlue, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onInvertColor(ActionEvent event) {
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

                double newRed = 1.0 - red;
                double newGrean = 1.0 - green;
                double newBlue = 1.0 - blue;

                Color newColor = new Color(newRed, newGrean, newBlue, color.getOpacity());
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
                double red = color.getRed();
                double green = color.getGreen();
                double blue = color.getBlue();

                double newRed = Math.min(red + red * brightness, 1.0);
                double newGreen = Math.min(green + green * brightness, 1.0);
                double newBlue = Math.min(blue + blue * brightness, 1.0);

                Color newColor = new Color(newRed, newGreen, newBlue, color.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onSlider(ActionEvent event) {
        Slider slider = new Slider(0, 10, 5);
        Label value = new Label("");

        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                value.setText(newValue.toString());
            }
        });
    }

    @FXML
    void onColorOverlay(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        Color overlay = new Color(0.5, 0, 0.5, 0.5);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);

                Color newColor = color.interpolate(overlay, 0.5);
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onBulge(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        double cx = width / 2.0;
        double cy = height / 2.0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                double dx = x - cx;
                double dy = y - cy;

                double radius = Math.sqrt(dx * dx + dy * dy);
                double theta = Math.atan2(dy, dx);
                double newRadius = Math.pow(radius, 1.6) / 30.0;

                int newX = (int) (cx + newRadius * Math.cos(theta));
                int newY = (int) (cy + newRadius * Math.sin(theta));

                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    writer.setColor(x, y, reader.getColor(newX, newY));
                }
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onPixelation(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        int blockSize = 6;

        for (int x = 0; x < width; x += blockSize) {
            for (int y = 0; y < height; y += blockSize) {
                Color color = reader.getColor(x, y);

                for (int i = 0; i < blockSize && x + i < width; i++) {
                    for (int j = 0; j < blockSize && y + j < height; j++) {
                        writer.setColor(x + i, y + j, color);
                    }
                }
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onVignette(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        double cx = width / 2.0;
        double cy = height / 2.0;
        double max = Math.sqrt(cx * cx + cy * cy);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double dist = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));
                double factor = Math.max(1 - dist / max, 0.3);

                Color color = reader.getColor(x, y);
                Color newColor = color.deriveColor(0, 1, 1, factor);
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onEdgeDetection(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        double[][] kernel = { { 1, 1, 1 }, { 1, -7, 1 }, { 1, 1, 1 } };
        int kernelSize = 3;
        int offset = 0;

        for (int x = offset; x < width; x++) {
            for (int y = offset; y < height; y++) {
                double red = 0;
                double green = 0;
                double blue = 0;

                for (int kx = 0; kx < kernelSize && x + kx < width; kx++) {
                    for (int ky = 0; ky < kernelSize && y + ky < height; ky++) {
                        Color color = reader.getColor(x + kx - offset, y + ky - offset);
                        red += color.getRed() * kernel[kx][ky];
                        green += color.getGreen() * kernel[kx][ky];
                        blue += color.getBlue() * kernel[kx][ky];
                    }
                }
                red = Math.max(0.0, Math.min(red, 1.0));
                green = Math.max(0.0, Math.min(green, 1.0));
                blue = Math.max(0.0, Math.min(blue, 1.0));

                Color originalColor = reader.getColor(x, y);
                Color newColor = new Color(red, green, blue, originalColor.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onEmboss(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        double[][] kernel = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
        int kernelSize = 3;
        int offset = 0;

        for (int x = offset; x < width; x++) {
            for (int y = offset; y < height; y++) {
                double red = 0;
                double green = 0;
                double blue = 0;

                for (int kx = 0; kx < kernelSize && x + kx < width; kx++) {
                    for (int ky = 0; ky < kernelSize && y + ky < height; ky++) {
                        Color color = reader.getColor(x + kx - offset, y + ky - offset);
                        red += color.getRed() * kernel[kx][ky];
                        green += color.getGreen() * kernel[kx][ky];
                        blue += color.getBlue() * kernel[kx][ky];
                    }
                }
                red = Math.max(0.0, Math.min(red, 1.0));
                green = Math.max(0.0, Math.min(green, 1.0));
                blue = Math.max(0.0, Math.min(blue, 1.0));

                Color originalColor = reader.getColor(x, y);
                Color newColor = new Color(red, green, blue, originalColor.getOpacity());
                writer.setColor(x, y, newColor);
            }
        }
        imageView.setImage(writableImage);
    }

    @FXML
    void onNoise(ActionEvent event) {
        int width = (int) imageView.getImage().getWidth();
        int height = (int) imageView.getImage().getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelReader reader = imageView.getImage().getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        int spacing = 3;

        for (int x = 0; x < width; x += spacing) {
            for (int y = 0; y < height; y += spacing) {
                Color color = reader.getColor(x, y);
                writer.setColor(x, y, color);
            }
        }
        imageView.setImage(writableImage);
    }

    // DO NOT REMOVE THIS METHOD!
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
