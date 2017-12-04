package boardgame;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import boardgame.common.util.PieceBuilder;
import boardgame.controllers.BoardController;
import boardgame.controllers.RootController;
import boardgame.framework.AbstractController;
import boardgame.framework.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoardApp extends Application {

    private Stage primaryStage;
    private Pane rootLayout;
    private Pane boardLayout;
    private Map<Enum<?>, String> config;
    private AbstractController rootController; 
    private AbstractController boardController;

    public BoardApp(){
    	config = new HashMap<Enum<?>, String>();
    	
    	// should load width and height from a configuration file
    	config.put(Config.WINDOW_HEIGHT, "900");
    	config.put(Config.WINDOW_WIDTH, "900");
    	config.put(Config.TITLE, "Board Game App");
    	
    	rootController = new RootController(config);
    	boardController = new BoardController(config, new PieceBuilder());
    	
    	boardController.addController(Config.ROOTCONTROLLER, rootController);
    	rootController.addController(Config.BORDCONTROLLER, boardController);
    	
    	rootLayout = this.loadPane("views/RootLayout.fxml", this.rootController);
    	boardLayout = this.loadPane("views/BoardView.fxml", this.boardController);
    	
    }
    
	@Override
	public void start(Stage primaryStage) {		
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(this.config.get(Config.TITLE));
        initRootLayout();
        showBoardView();
	}
	
	/**
	 * 
	 * @param fxml
	 * @param controller
	 * @return
	 * @throws IOException
	 */
	protected Pane loadPane(String fxml, AbstractController controller) {
		// Load root layout from FXML file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BoardApp.class.getResource(fxml));
        loader.setController(controller);
		try {
			return (Pane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

    public void initRootLayout() {
		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public void showBoardView() {
		((BorderPane) rootLayout).setLeft(this.boardLayout);
    }

	public static void main(String[] args) {
		launch(args);
	}

}
