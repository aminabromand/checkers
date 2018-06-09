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
	private int zeile;
	private int spalte;

	public Feld(Brett brett, boolean schwarz, int zeile, int spalte) {
		this.brett = brett;
		this.istSchwarz = schwarz;
		this.zeile = zeile;
		this.spalte = spalte;
	}

	public void setStein (Stein stein) {
		this.stein = stein;
		stein.setFeld(this);
		this.setForeground( stein.ist_schwarz() ? Color.black : Color.white );
		this.setText("O");
	}

	public void wegStein() {
		stein = null;
		brett.merkeBeginn();
		setText("");
	}

	public Brett getBrett() {
		return brett;
	}

	public Stein getStein() {
		return stein;
	}

	public int getZeile() {
		return zeile;
	}

	public int getSpalte() {
		return spalte;
	}
}