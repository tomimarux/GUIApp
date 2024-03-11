import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class StageController implements Initializable{
    @FXML
    private Pane identity;
    @FXML
    private ChildController appController;

    private StageModel model;
    
    private ChildController currentController;
    
    @Override
	public void initialize(URL location, ResourceBundle resources){
        this.model = new StageModel();

        this.appController.setParent(this);

        this.currentController = this.appController;
        this.currentController.init();
    }

    public StageModel getModel(){
        return this.model;
    }
    
    public void to(ChildController controller) {
    	this.currentController.getIdentity().setVisible(false);
    	this.currentController = controller;
    	this.currentController.init();
    	this.currentController.getIdentity().setVisible(true);
    }
    
    public void toApp() {
    	this.to(this.appController);
    }
}