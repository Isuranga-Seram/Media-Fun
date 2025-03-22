package lk.ijse.dep13.fx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep13.fx.util.AppRouter;
import java.io.File;
import java.io.IOException;

public class AudioSceneController {
    @FXML public ImageView imgClose, imgOpen, imgPlay, imgPause, imgReset, imgVolume, imgMute, imgVideoWindow;
    @FXML public Label lblSong, lblDuration, lblVolume;
    @FXML public MediaView mdPreview;
    @FXML public AnchorPane root;
    @FXML public Slider sldrSeek, sldrVolume;
    public ImageView imgFolder;
    public ImageView imgAudioWindow;
    private MediaPlayer mediaPlayer;
    private boolean isSeeking = false;
    private double vlm = 50;

    public void initialize() {
        setupDragAndDrop();

        lblVolume.setText(String.valueOf("%.0f".formatted(sldrVolume.getValue() * 100)) + "%");

        sldrVolume.valueProperty().addListener((observableRef, previous, current) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(current.doubleValue());
            }
            lblVolume.setText("%.0f".formatted(current.doubleValue() * 100) + "%");

            if (current.doubleValue() == 0) {
                imgVolume.setVisible(false);
                imgMute.setVisible(true);
            } else {
                imgMute.setVisible(false);
                imgVolume.setVisible(true);
            }
        });
    }

    private String formatTime(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    void loadMedia(String mediaUrl) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        Media media = new Media(mediaUrl);
        mediaPlayer = new MediaPlayer(media);
        mdPreview.setMediaPlayer(mediaPlayer);
        lblSong.setText(new File(mediaUrl).getName());

        mediaPlayer.setOnReady(() -> {
            sldrSeek.setMax(mediaPlayer.getTotalDuration().toSeconds());
            lblDuration.setText(formatTime(mediaPlayer.getTotalDuration()));
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!isSeeking) {
                sldrSeek.setValue(newTime.toSeconds());
                lblDuration.setText(formatTime(newTime));
            }
        });

        mediaPlayer.setVolume(sldrVolume.getValue());
        mediaPlayer.play();
    }

    public void imgOpenOnMouseClicked(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.mkv", "*.avi", "*.wmv", "*.webm", "*.mp3", "*.wav", "*.flv", "*.aac"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            if (!file.getName().endsWith(".mp4") &&
                    !file.getName().endsWith(".mkv") &&
                    !file.getName().endsWith(".avi") &&
                    !file.getName().endsWith(".wmv") &&
                    !file.getName().endsWith(".webm")) {
                loadMedia(file.toURI().toString());
            } else {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }
                Stage stage = (Stage) imgOpen.getScene().getWindow();
                Scene scene = new Scene(AppRouter.getContainer(AppRouter.Routes.VIDEO));
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);

                VideoSceneController videoController = (VideoSceneController) AppRouter.getController(AppRouter.Routes.VIDEO);
                if (videoController != null) {
                    Platform.runLater(() -> videoController.loadMedia(file.toURI().toString()));
                }
            }
        }
    }

    private void setupDragAndDrop() {
        root.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
            }
            event.consume();
        });

        root.setOnDragDropped(event -> {
            handleDragDropped(event);
            event.consume();
        });
    }

    private void handleDragDropped(DragEvent event) {
        File file = event.getDragboard().getFiles().get(0);
        loadMedia(file.toURI().toString());
    }

    public void imgVolumeOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            imgVolume.setVisible(false);
            imgMute.setVisible(true);
            vlm = sldrVolume.getValue();
            sldrVolume.setValue(0);
        }
    }

    public void imgMuteOnMouseClicked(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            imgMute.setVisible(false);
            imgVolume.setVisible(true);
            sldrVolume.setValue(vlm);
        }
    }

    public void imgPlayOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void imgPauseOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void imgResetOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.pause();
            sldrSeek.setValue(0);
        }
    }

    public void imgCloseOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        System.exit(0);
    }

    public void sldrSeekOnMouseReleased(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.seconds(sldrSeek.getValue()));
        }
    }



    public void imgPlayOnMouseEntered(MouseEvent mouseEvent) {
        imgPlay.setOpacity(0.85);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUA);
        glow.setRadius(10);
        glow.setSpread(0.5);
        imgPlay.setEffect(glow);
    }

    public void imgPlayOnMouseExited(MouseEvent mouseEvent) {
        imgPlay.setOpacity(1.0);
        imgPlay.setEffect(null);
    }

    public void imgPauseOnMouseEntered(MouseEvent mouseEvent) {
        imgPause.setOpacity(0.85);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUA);
        glow.setRadius(10);
        glow.setSpread(0.5);
        imgPause.setEffect(glow);
    }

    public void imgPauseOnMouseExited(MouseEvent mouseEvent) {
        imgPause.setOpacity(1.0);
        imgPause.setEffect(null);
    }

    public void imgResetOnMouseEntered(MouseEvent mouseEvent) {
        imgReset.setOpacity(0.85);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUA);
        glow.setRadius(10);
        glow.setSpread(0.5);
        imgReset.setEffect(glow);
    }

    public void imgResetOnMouseExited(MouseEvent mouseEvent) {
        imgReset.setOpacity(1.0);
        imgReset.setEffect(null);
    }

    public void imgOpenOnMouseEntered(MouseEvent mouseEvent) {
        imgOpen.setOpacity(0.85);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.AQUA);
        glow.setRadius(10);
        glow.setSpread(0.5);
        imgOpen.setEffect(glow);
    }

    public void imgOpenOnMouseExited(MouseEvent mouseEvent) {
        imgOpen.setOpacity(1.0);
        imgOpen.setEffect(null);
    }

    public void imgCloseOnMouseEntered(MouseEvent mouseEvent) {
        imgClose.setOpacity(0.85);
        DropShadow glow = new DropShadow();
        glow.setColor(Color.RED);
        glow.setRadius(8);
        glow.setSpread(0.2);
        imgClose.setEffect(glow);
    }

    public void imgCloseOnMouseExited(MouseEvent mouseEvent) {
        imgClose.setOpacity(1.0);
        imgClose.setEffect(null);
    }


    // Move the window
    double mouseX, mouseY;
    private void onMouseDragged(MouseEvent mouseEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - mouseX);
        stage.setY(mouseEvent.getScreenY() - mouseY);
    }
    private void onMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX();
        mouseY = mouseEvent.getSceneY();
    }

    public void imgAudioWindowOnMouseDragged(MouseEvent mouseEvent) {
        onMouseDragged(mouseEvent);
    }

    public void imgAudioWindowOnMousePressed(MouseEvent mouseEvent) {
        onMousePressed(mouseEvent);
    }

    public void mdPreviewOnMouseDragged(MouseEvent mouseEvent) {
        onMouseDragged(mouseEvent);
    }

    public void mdPreviewOnMousePressed(MouseEvent mouseEvent) {
        onMousePressed(mouseEvent);
    }

    public void imgFolderOnMouseDragged(MouseEvent mouseEvent) {
        onMouseDragged(mouseEvent);
    }

    public void imgFolderOnMousePressed(MouseEvent mouseEvent) {
        onMousePressed(mouseEvent);
    }
}

