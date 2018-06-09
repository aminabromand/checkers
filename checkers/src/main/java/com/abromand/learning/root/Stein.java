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

	public Feld getFeld() {
		return feld;
	}

	public boolean istOK ( Feld feld ) {
		int dX = feld.getSpalte() - getFeld().getSpalte();
		int dY = feld.getZeile() - getFeld().getZeile();

		if ( Math.abs(dX) != Math.abs(dY) ) {
			return false;
		} else if ( Math.abs(dX) == 0 || Math.abs(dX) > 2) {
			return false;
		} else if ( (ist_schwarz() && dY > 0) || (!ist_schwarz() && dY < 0) ) {
			return false;
		}

		return true;
	}
}