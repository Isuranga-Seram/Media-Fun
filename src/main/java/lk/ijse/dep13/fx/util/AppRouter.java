package lk.ijse.dep13.fx.util;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class AppRouter {
    public enum Routes{
        VIDEO,AUDIO,
    }

    public static AnchorPane getContainer (Routes route) throws IOException {
        AnchorPane container = null;
        if (route == Routes.VIDEO){
            container = FXMLLoader.load(AppRouter.class.getResource("/scene/VideoScene.fxml"));
            container.setBackground(Background.fill(Color.TRANSPARENT));
        }else if (route == Routes.AUDIO){
            container = FXMLLoader.load(AppRouter.class.getResource("/scene/AudioScene.fxml"));
            container.setBackground(Background.fill(Color.TRANSPARENT));
        }

        FadeTransition ft = new FadeTransition(Duration.millis(700), container);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.playFromStart();

        return container;

    }
}
