package purplesquad;

import java.awt.*;

public class RobotColors implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public Color bodyColor;
	public Color gunColor;
	public Color radarColor;
	public Color scanColor;
	public Color bulletColor;

	public RobotColors () {
		this.bodyColor   = new Color(180,  13, 232);
		this.gunColor    = new Color(  8,   8,   8);
		this.radarColor  = new Color(213,  13,  13);
		this.scanColor   = new Color(213,  13,  13);
		this.bulletColor = new Color(  0, 244, 252);
	}
}
