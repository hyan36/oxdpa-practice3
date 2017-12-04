package boardgame.framework;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public abstract class AbstractController {
	
	protected Map<Enum<?>, String> config;
	
	protected Map<Enum<?>, AbstractController> controllers;
	
	protected String controllerName;
	
	@FXML
	protected Pane main;
	
	public AbstractController () {
		this.controllers = new HashMap<Enum<?>, AbstractController>();
	}
	
	public void setConfig(Map<Enum<?>, String> config) {
		this.config = config;
	}
	
	@FXML
	protected void initialize() {	
		this.controllerName = this.getClass().getName();
		this.prepare();
		this.draw();
	}
	
	public void addController(Enum<?> key, AbstractController value) {
		this.controllers.put(key, value);
	}
	
	public AbstractController getController(Enum<?> key) {
		return this.controllers.get(key);
	}
	
	public void removeController(Enum<?> key) {
		this.controllers.remove(key);
	}
	
	public abstract void draw();
	
	public abstract void prepare();
	
}
