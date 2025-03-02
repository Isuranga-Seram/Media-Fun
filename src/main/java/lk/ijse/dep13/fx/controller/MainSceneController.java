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

public class MainSceneController {
    public ImageView imgClose;
    public ImageView imgOpen;
    public ImageView imgPlay;
    public ImageView imgPuase;
    public ImageView imgReset;
    public Label lblSong;
    public MediaView mdPreview;
    public MediaPlayer mediaPlayer;
    public AnchorPane root;
    public Slider sldrSeek;
    public Slider sldrVolume;
    public Label lblDuration;
    public ImageView imgVideoWindow;
    public Label lblVolume;
    public ImageView imgVolume;
    public ImageView imgMute;

    public boolean isSeeking = false; // Flag to control update conflicts

    public void initialize() {

        Media media = new Media("file:/home/isuranga/Documents/dep-13/first-phase/java-fx/05-controls/fx-controls-media-player/src/main/resources/video/Football.mp4");
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

//        sldrSeek.valueProperty().addListener((observableRef, previous, current) -> {
//            isSeeking = true; // Set flag to true while seeking
//            double seekValue = current.doubleValue();
//            mediaPlayer.seek(Duration.millis(seekValue)); // Seek to the specified position
//            isSeeking = false; // Reset flag after seeking
//        });

        setupDragAndDrop();

        File path = new File("file:/home/isuranga/Documents/dep-13/first-phase/java-fx/05-controls/fx-controls-media-player/src/main/resources/video/Football.mp4");
        lblSong.setText(path.getName());

    }


    private void setupDragAndDrop() {
        mdPreview.setOnDragOver(this::mdPreiviewOnDragOver);
        mdPreview.setOnDragDropped(this::handleDragDropped);
    }

    public void mdPreiviewOnDragOver(DragEvent event) {
        if (event.getGestureSource() != mdPreview && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            // Get the first file
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
        mediaPlayer.play();
        lblSong.setText(media.getSource());
    }

    public void imgOpenOnMouseClicked(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        if ((fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"))) ||
                (fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.flv"))) ||
                        (fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.aac")))){
            Stage stage = (Stage) imgOpen.getScene().getWindow();
            Scene scene = new Scene(AppRouter.getContainer(AppRouter.Routes.AUDIO));
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.avi"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mkv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.wav"));

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

    public void rootOnMouseDragged(MouseEvent mouseEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - mouseX);
        stage.setY(mouseEvent.getScreenY() - mouseY);
    }

    double mouseX, mouseY;
    public void rootOnMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX();
        mouseY = mouseEvent.getSceneY();
    }

    public void imgCloseOnMouseClicked(MouseEvent mouseEvent) {
        ((Stage)(root.getScene().getWindow())).close();
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

    public void sldrSeekOnMouseReleased(MouseEvent mouseEvent) {
        isSeeking = true; // Set flag to true while seeking
        sldrSeek.valueProperty().addListener((observableRef, previous, current) -> {
            double seekValue = current.doubleValue();
            isSeeking = false; // Reset flag after seeking
            mediaPlayer.seek(Duration.millis(seekValue)); // Seek to the specified position
        });
    }
}
