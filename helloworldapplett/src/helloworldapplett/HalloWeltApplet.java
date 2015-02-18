package helloworldapplett;

import java.applet.Applet;
import java.awt.Graphics;

public class HalloWeltApplet extends Applet {

	/**
	 *
	 */
	private static final long serialVersionUID = 8749092549266899691L;

	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		arg0.drawString("Hallo Welt", 20, 20);
	}

}
