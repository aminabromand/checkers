package com.abromand.learning.root;

import com.abromand.learning.gui.Feld;

/**
 * @author amin
 */
public class Einfach extends Stein {
	public Einfach (Feld feld, boolean schwarz) {
		super(feld, schwarz);
	}

	public boolean istOK( Feld feld ) {
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

	public String getSymbol() {
		return "\u25ce";
	}
}