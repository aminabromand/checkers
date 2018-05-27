package com.abromand.learning.root;

import java.awt.Color;
import com.abromand.learning.gui.Feld;

/**
 * @author amin
 */
public abstract class Stein {
	private Feld feld;
	private boolean istSchwarz;

	public Stein (Feld feld, boolean schwarz) {
		this.feld = feld;
		this.istSchwarz = schwarz;
	}

	public boolean ist_schwarz() {
		return istSchwarz;
	}

	public void setFeld( Feld feld ) {
		this.feld = feld;
		feld.getBrett().merkeEnde();
	}
}