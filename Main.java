import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage stage) throws Exception {		
		stage.setResizable(false);

		Pane mainRoot = new FXMLLoader(getClass().getResource("stage.fxml")).load();
		Scene mainScene = new Scene(mainRoot, 850, 600);
		stage.setScene(mainScene);

		stage.setTitle("Web Book");
		/*Image icon = new Image(getClass().getResource("icon.png").toString());
		stage.getIcons().add( icon );*/

		stage.show();
	}
    public static void main(String[] args) {
		launch(args);
	}
}