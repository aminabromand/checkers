package com.abromand.learning.gui;

import java.awt.Color;
import com.abromand.learning.root.Stein;

/**
 * @author amin
 */
public class Feld extends javax.swing.JButton {
	private Brett brett;
	private boolean istSchwarz;
	private Stein stein = null;

	public Feld(Brett brett, boolean schwarz) {
		this.brett = brett;
		this.istSchwarz = schwarz;
	}

	public void setStein (Stein stein) {
		this.stein = stein;
		this.setForeground( stein.ist_schwarz() ? Color.black : Color.white );
		this.setText("O");
	}
}