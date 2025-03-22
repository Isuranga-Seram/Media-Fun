package lk.ijse.dep13.fx.util;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppRouter {

    public enum Routes {
        VIDEO, AUDIO,
    }

    // Store controllers
    private static final Map<Routes, Object> controllersMap = new HashMap<>();

    public static AnchorPane getContainer(Routes route) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane container = null;

        if (route == Routes.VIDEO) {
            loader.setLocation(AppRouter.class.getResource("/scene/VideoScene.fxml"));
        } else if (route == Routes.AUDIO) {
            loader.setLocation(AppRouter.class.getResource("/scene/AudioScene.fxml"));
        }

        container = loader.load();
        container.setBackground(Background.fill(Color.TRANSPARENT));

        // Store the controller instance
        controllersMap.put(route, loader.getController());

        // Add fade transition
        FadeTransition ft = new FadeTransition(Duration.millis(700), container);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.playFromStart();

        return container;
    }

    // Retrieve the stored controller
    public static Object getController(Routes route) {
        return controllersMap.get(route);
    }
}

