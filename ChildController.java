import javafx.scene.layout.Pane;

public interface ChildController {
	public Pane getIdentity();
	public void init();
	public void setParent(StageController parent);
}
