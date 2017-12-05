package boardgame.common.util;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * We can use as many as kind of piece on board, can be rendered dynamically as a vector / a static image. 
 * 
 * use builder pattern so it can be replaced by other logic easily
 * 
 * @author HaihaoY
 *
 */
public class PieceBuilder implements IPieceBuilder {
	
	protected Node draw(Paint color, double radious) {
		Circle result = new Circle(radious);
		result.setFill(color);
		result.setStroke(Color.BLACK);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see boardgame.common.util.IPieceBuilder#drawDefault(double)
	 */
	@Override
	public Node drawDefault(double radious) {
		return this.draw(Color.WHITE, radious);
	}
	
	/* (non-Javadoc)
	 * @see boardgame.common.util.IPieceBuilder#drawRed(double)
	 */
	@Override
	public Node drawRed(double radious) {
		return this.draw(Color.RED, radious);
	}
	
	/* (non-Javadoc)
	 * @see boardgame.common.util.IPieceBuilder#drawYellow(double)
	 */
	@Override
	public Node drawYellow(double radious) {
		return this.draw(Color.YELLOW, radious);
	}
	
	
}
