package com.hrm.assets.lib;

import javafx.stage.PopupWindow;
import javafx.geometry.Bounds;
import javafx.scene.control.Tooltip;

public class tooltip_get {

	private static final String SQUARE_BUBBLE = "M24 1h-24v16.981h4v5.019l7-5.019h13z";

	public tooltip_get() {
		// TODO Auto-generated constructor stub
	}

	public static Tooltip makeBubble(Tooltip tooltip) {
		tooltip.setStyle("-fx-font-size: 16px; -fx-shape: \"" + SQUARE_BUBBLE + "\"; -fx-border-radius:5px");
		tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);

		return tooltip;
	}

}
