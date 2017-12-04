package boardgame.controllers;

import java.util.Map;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Player;
import boardgame.common.util.IPieceBuilder;
import boardgame.framework.AbstractController;
import boardgame.framework.Config;
import boardgame.impl.BoardgameFactoryImpl;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class BoardController extends AbstractController {

	public IPieceBuilder pieceBuilder;

	protected double width;

	protected double height;

	protected double radious;

	private Board board;

	@FXML
	public GridPane gridPane;

	public BoardController(Map<Enum<?>, String> config, IPieceBuilder builder) {
		this.config = config;
		this.pieceBuilder = builder;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

		gridPane.setGridLinesVisible(true);

		for (int row = 0; row < this.board.getHeight(); row++) {
			for (int col = 0; col < this.board.getWidth(); col++) {
				Circle element = (Circle) this.pieceBuilder.drawDefault(radious);
				int column = col;
				gridPane.add(element, col, row);
				element.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> columnClicked(element, column));
			}
		}
		double width = this.board.getWidth() * this.radious * 2;
		double height = this.board.getHeight() * this.radious * 2;
		this.gridPane.setPrefWidth(width);
		this.gridPane.setPrefHeight(height);
		((RootController) this.getController(Config.ROOTCONTROLLER)).resize(width + 10, height + 40);

	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
		this.width = Double.parseDouble(this.config.get(Config.WINDOW_WIDTH));
		this.height = Double.parseDouble(this.config.get(Config.WINDOW_HEIGHT));
		this.board = (new BoardgameFactoryImpl()).createBoard();
		this.radious = this.width / (this.board.getWidth() * 2);
	}

	public void restart() {
		this.initialize();
	}

	/**
	 * get last empty grid
	 * 
	 * @param col
	 * @return
	 */
	protected int getLastEmptyGrid(int col) {
		for (int i = 0; i < this.board.getHeight(); i++) {
			Piece p = board.getPiece(i, col);
			if (p != null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * handler for column clicked event
	 * 
	 * @param node
	 * @param col
	 */
	protected void columnClicked(Node node, int col) {
		Player player = board.nextTurn();
		Piece p = player.getToken();
		// logic stuff
		if (board.getWinner() == null) {
			board.play(player, p, col + 1);
			// paint the right token in the view
			int row = getLastEmptyGrid(col);
			Node newNode = "Yellow".equals(p.getName()) ? this.pieceBuilder.drawYellow(radious)
					: this.pieceBuilder.drawRed(radious);
			gridPane.add(newNode, col, row);
			return;
		}
		System.out.println("we have a winner " + board.getWinner().getName());
	}

}
