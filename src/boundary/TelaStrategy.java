package boundary;

import javafx.scene.layout.Pane;

public interface TelaStrategy {
	
	public Pane getTela();
	
	public void carregar() throws Exception;

}