package boardgame.controllers;

import java.util.Map;

import boardgame.framework.AbstractController;
import boardgame.framework.Config;
import javafx.fxml.FXML;

public class RootController extends AbstractController {
	
	protected Double width;
	
	protected Double height;
	
	public RootController (Map<Enum<?>, String> config) {
		this.config = config;
	}
	
	@FXML
	private void handleClose() {
		System.exit(-1);
    }
	
	@FXML
	private void restart() {
		((BoardController) this.getController(Config.BORDCONTROLLER)).restart();
	}
	
	public void resize(double width, double height) {
		this.width = width;
		this.height = height;
		this.draw();
	}

	@Override
	public void draw() {
		// do nothing
		double height = this.height;
		double width = this.width;
		System.out.println(height);
		this.main.setPrefHeight(height);
		this.main.setPrefWidth(width);
	}


	@Override
	public void prepare() {
		// do nothing
		this.width = Double.parseDouble(this.config.get(Config.WINDOW_WIDTH));
		this.height = Double.parseDouble(this.config.get(Config.WINDOW_HEIGHT));		
	}
}
