package boardgame.common.util;

import javafx.scene.Node;

public interface IPieceBuilder {

	Node drawDefault(double radious);

	Node drawRed(double radious);

	Node drawYellow(double radious);

}