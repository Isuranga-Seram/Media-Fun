package lk.ijse.dep13.fx.controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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


/*public class AudioSceneController {
    public ImageView imgClose;
    public ImageView imgOpen;
    public ImageView imgPlay;
    public ImageView imgPuase;
    public ImageView imgReset;
    public ImageView imgVideoWindow;
    public Label lblDuration;
    public Label lblSong;
    public AnchorPane root;
    public Slider sldrSeek;
    public Slider sldrVolume;
    public ImageView imgAudioWindow;
    public MediaView mdPreview;
    public MediaPlayer mediaPlayer;
    public Label lblVolume;

    public void initialize() throws IOException {
        setupDragAndDrop();

        sldrVolume.valueProperty().addListener((observableRef, previous, current) -> {
            lblVolume.setText("%.0f".formatted(current.doubleValue()*100)+"%");
            mediaPlayer.setVolume(current.doubleValue());
        });
    }

    public void imgCloseOnMouseClicked(MouseEvent event) {
        ((Stage)(root.getScene().getWindow())).close();
    }

    public void imgOpenOnMouseClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mdPreview.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    public void imgPauseOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void imgPlayOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void imgResetOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    double mouseX, mouseY;
    public void rootOnMouseDragged(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX() - mouseX);
        stage.setY(event.getScreenY() - mouseY);
    }

    public void rootOnMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    private void setupDragAndDrop() {
        mdPreview.setOnDragOver(this::mdPreviewOnDragOver);
        mdPreview.setOnDragDropped(this::handleDragDropped);
    }

    public void mdPreviewOnDragOver(DragEvent event) {
        if (event.getGestureSource() != mdPreview && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            var file = db.getFiles().get(0);
            loadMedia(file.toURI().toString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void loadMedia(String mediaUrl) {
        Media media = new Media(mediaUrl);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mdPreview.setMediaPlayer(mediaPlayer);
        mdPreview.setVisible(false);
        mediaPlayer.play();
        lblSong.setText(media.getSource());
    }
}*/

public class AudioSceneController {
    public ImageView imgClose;
    public ImageView imgOpen;
    public ImageView imgPlay;
    public ImageView imgPause;
    public ImageView imgReset;
    public ImageView imgVideoWindow;
    public Label lblDuration;
    public Label lblSong;
    public AnchorPane root;
    public Slider sldrSeek;
    public Slider sldrVolume;
    public ImageView imgAudioWindow;
    public MediaView mdPreview;
    public MediaPlayer mediaPlayer;
    public Label lblVolume;

    public boolean isSeeking = false;
    public ImageView imgVolume;
    public ImageView imgMute;
    public ImageView imgPuase;

    public void initialize() throws IOException {

        /*Media media = new Media("file:/home/isuranga/Documents/dep-13/first-phase/java-fx/05-controls/fx-controls-media-player/src/main/resources/video/Football.mp4");
        mediaPlayer = new MediaPlayer(media);
        mdPreview.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnReady(() -> {

            // lbl Duration at the beginning
            Duration duration = mediaPlayer.getMedia().getDuration();
            sldrSeek.setMax(duration.toMillis());
            lblDuration.setText(String.format("%.0f sec", duration.toSeconds()));

            // Update playback time every second & sldr seek
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (!isSeeking) {
                    lblDuration.setText(String.format("%.0f / %.0f sec",
                            newValue.toSeconds(), duration.toSeconds()));
                    sldrSeek.setValue(newValue.toMillis());
                }
            });

            // Volume
            lblVolume.setText(String.valueOf("%.0f".formatted(sldrVolume.valueProperty().getValue()*100))+"%");
            sldrVolume.valueProperty().addListener((observableRef, previous, current) -> {
                lblVolume.setText("%.0f".formatted(current.doubleValue()*100)+"%");
                mediaPlayer.setVolume(current.doubleValue());
                if (sldrVolume.valueProperty().getValue() == 0){
                    imgVolume.setVisible(false);
                    imgMute.setVisible(true);
                } else{
                    imgMute.setVisible(false);
                    imgVolume.setVisible(true);
                }
            });
        });
*/

        setupDragAndDrop();

        sldrVolume.valueProperty().addListener((observableRef, previous, current) -> {
            lblVolume.setText("%.0f".formatted(current.doubleValue() * 100) + "%");
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(current.doubleValue());
            }
        });
    }

    public void imgCloseOnMouseClicked(MouseEvent event) {
        ((Stage)(root.getScene().getWindow())).close();
    }

    public void imgOpenOnMouseClicked(MouseEvent event) throws IOException {
        /*FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            loadMedia(file.toURI().toString());
        }*/

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        if ((fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4"))) ||
                (fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.avi"))) ||
                (fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mkv"))) ||
                (fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.wav")))){
            Stage stage = (Stage) imgOpen.getScene().getWindow();
            Scene scene = new Scene(AppRouter.getContainer(AppRouter.Routes.VIDEO));
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
        }

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.flv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.aac"));

        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            Media media = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mdPreview.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    public void imgPauseOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void imgPlayOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void imgResetOnMouseClicked(MouseEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seek(Duration.ZERO); // Optional: Reset to start
        }
    }

    double mouseX, mouseY;
    public void rootOnMouseDragged(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(event.getScreenX() - mouseX);
        stage.setY(event.getScreenY() - mouseY);
    }

    public void rootOnMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    private void setupDragAndDrop() {
        mdPreview.setOnDragOver(this::mdPreviewOnDragOver);
        mdPreview.setOnDragDropped(this::handleDragDropped);
    }

    public void mdPreviewOnDragOver(DragEvent event) {
        if (event.getGestureSource() != mdPreview && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            var file = db.getFiles().get(0);
            loadMedia(file.toURI().toString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void loadMedia(String mediaUrl) {
        // If there is an existing MediaPlayer, stop it first
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(mediaUrl);
        mediaPlayer = new MediaPlayer(media);  // Set the class-level mediaPlayer
        mdPreview.setMediaPlayer(mediaPlayer);
        mdPreview.setVisible(true); // Make the media preview visible
        mediaPlayer.play();
        lblSong.setText(media.getSource());
    }

    public void sldrSeekOnMouseReleased(MouseEvent mouseEvent) {
        isSeeking = true; // Set flag to true while seeking
        sldrSeek.valueProperty().addListener((observableRef, previous, current) -> {
            double seekValue = current.doubleValue();
            isSeeking = false; // Reset flag after seeking
            mediaPlayer.seek(Duration.millis(seekValue)); // Seek to the specified position
        });
    }

    double vlm;
    public void imgVolumeOnMouseClicked(MouseEvent mouseEvent) {
        imgVolume.setVisible(false);
        imgMute.setVisible(true);
        vlm = sldrVolume.valueProperty().getValue();
        sldrVolume.valueProperty().setValue(0);
    }

    public void imgMuteOnMouseClicked(MouseEvent mouseEvent) {
        imgMute.setVisible(false);
        imgVolume.setVisible(true);

        sldrVolume.valueProperty().setValue(vlm);
    }
}

