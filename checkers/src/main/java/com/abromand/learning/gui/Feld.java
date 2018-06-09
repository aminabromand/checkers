package com.abromand.learning.gui;

import java.awt.Color;
import com.abromand.learning.root.*;

/**
 * @author amin
 */
public class Feld extends javax.swing.JButton {
	private Brett brett;
	private boolean istSchwarz;
	private Stein stein = null;
	private int zeile;
	private int spalte;

	public Feld( Brett brett, boolean schwarz, int zeile, int spalte ) {
		this.brett = brett;
		this.istSchwarz = schwarz;
		this.zeile = zeile;
		this.spalte = spalte;

		//this.setFont( this.getFont().deriveFont(Font.BOLD) );
	}

	public void setStein ( Stein stein ) {
		setStein( stein, false );
	}

	public void setStein ( Stein stein, boolean init ) {
		if ( !init && ( zeile == 0 || zeile == 9 ) && stein instanceof Einfach ) {
			stein = new Dame( this, stein.ist_schwarz() );
		}

		this.stein = stein;
		stein.setFeld( this );
		this.setForeground( stein.ist_schwarz() ? Color.black : Color.white );
		this.setText( stein.getSymbol() );
	}

	public void wegStein() {
		stein = null;
		//brett.merkeBeginn();
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