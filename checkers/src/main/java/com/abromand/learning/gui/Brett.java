package com.abromand.learning.gui;

import java.awt.Color;
import java.awt.Font;
import com.abromand.learning.root.Einfach;
import com.abromand.learning.root.Dame;
import com.abromand.learning.root.Stein;
 
/**
 * @author amin
 */
public class Brett extends javax.swing.JFrame {

    private boolean istZugbeginn = true;
 
    /* weiss:   1       00000001
     * schwarz: 2       00000010
     * "beide": 3       00000011
     */
    private final int WEISS = 1;
    private final int SCHWARZ = 2;
    private final int BEIDE = 3;
    private int amZug = WEISS;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jButton1;

    private Feld[][] feld = new Feld[10][10];

 
    /**
     * Der Konstruktor.
     */
    public Brett() {
        initComponents();
    }

    private void initComponents () {

        // Was soll bei Klick auf das System-X rechts oben passieren:
        // Das Programm soll gänzlich beendet werden.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        this.setLayout( new java.awt.BorderLayout() );
        
        
        jPanel1 = new javax.swing.JPanel();
        //jButton1 = new javax.swing.JButton( "jButton1" );

        jPanel1.setLayout( new java.awt.GridLayout(10, 10) );
        //jPanel1.add(jButton1);

        Font feldFont = new Font( "Dialog", 1, 36 );
        boolean schwarz = true;
        for ( int z=0; z<feld.length; z++ ) {
            for ( int sp=0; sp<feld[z].length; sp++ ) {
                Feld newFeld = new Feld( this, schwarz, z, sp );

                newFeld.setFont( feldFont );
                newFeld.setBackground( schwarz ? Color.darkGray : Color.lightGray );
                newFeld.addActionListener(fl);

                if (schwarz) {
                    if ( z <= 3 ) {
                        newFeld.setStein( new Einfach (newFeld, false), true );
                    } else if ( z >= 6 ) {
                        newFeld.setStein( new Einfach (newFeld, true), true );
                    }
                }

                feld[z][sp] = newFeld;
                jPanel1.add(newFeld);

                // damit die Felder abwechselnd Schwarz/Weiss sind.
                schwarz = !schwarz;
            }
            //damit jede Zeile mit einer anderen Farbe startet.
            schwarz = !schwarz;
        }


        this.add(jPanel1, java.awt.BorderLayout.CENTER);
        
        

        // Das Panel zum aktiven, sichtbaren Inhalt des JFrame machen:
        //this.getContentPane().add ( jPanel1 ) ;
 
        // Alle Elemente auf kleinstmögliche Größe bringen
        pack();

        this.setSize(500,500);
    }



    public boolean getZugbeginn() {
        return istZugbeginn;
    }

    public void merkeBeginn() {
        istZugbeginn = false;
    }

    public void merkeEnde( Stein stein ) {
        //System.out.println("cname: " + stein.getClass().getCanonicalName());
        //if ( stein.getClass().getCanonicalName().equals( "root.Einfach" ) ) {
        if ( stein instanceof Einfach ) {
            amZug = stein.ist_schwarz() ? WEISS : SCHWARZ;
            System.out.println("am Zug: " + amZug);
        } else if ( stein instanceof Dame ) {
            //ToDo: hier müsste der Schlagzwang implementiert werden
            amZug = stein.ist_schwarz() ? WEISS : SCHWARZ;
            System.out.println("am Zug: " + amZug);
        }
        istZugbeginn = true;
    }

    public boolean istOK( Stein stein, Feld ziel ) {
        // ziel besetzt
        if ( ziel.getStein() != null ) {
            return false;
        }

        // zug länger als 1 feld
        int x1 = stein.getFeld().getSpalte();
        int y1 = stein.getFeld().getZeile();
        int x2 = ziel.getSpalte();
        int y2 = ziel.getZeile();

        int dX = x2 - x1;
        int dY = y2 - y1;

        if ( Math.abs(dX) > 1 ) {
            int dX1 = dX > 0 ? 1 : -1;
            int dY1 = dY > 0 ? 1 : -1;
            // letztes feld
            Feld fletzt = feld[ y2 - dY1 ][ x2 - dX1 ];
            Stein stletzt = fletzt.getStein();
                // stein eigener farbe
            if (stletzt == null) {
                // einfacher stein: leer
                if ( stein instanceof Einfach ) {
                    return false;
                }
            } else if (stletzt.ist_schwarz() == stein.ist_schwarz()) {
                return false;
            }
            // zug länger als 2 feld
            if ( Math.abs(dX) > 2 ) {
                int z = y1;
                int sp = x1;
                // erstes bis vorletztes feld besetzt
                for ( int i = 0; i < Math.abs(dX) - 2; i++ ) {
                    z += dY1;
                    sp += dX1;

                    if ( feld[z][sp].getStein() != null ) {
                        return false;
                    }
                }
            }
            fletzt.wegStein();
        }

        return true;
    }

 
    /**
     * Zeigt ein JFrame im BorderLayout mit je einem Button pro Bereich an.
     *
     * Jedes Java-Programm beginnt bei einer Methode main() zu laufen, so auch 
     * dieses. Beachten Sie, dass die Methode main() in jeder beliebigen 
     * Klasse stehen könnte, die Zugriff auf BorderLayoutDemo hat, also auch in 
     * ihr selbst – also hier:
     */
    public static void main(String args[]) {
        // Ein Objekt der Klasse erzeugen und sichtbar machen.
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brett().setVisible ( true );
            }
        });
    }


    private class FeldListener implements java.awt.event.ActionListener {
        private Stein st = null;

        public void actionPerformed ( java.awt.event.ActionEvent evt ) {
            Feld f = null;

            for ( int z=0; z<feld.length; z++ ) {
                for ( int sp=0; sp<feld[z].length; sp++ ) {
                    if (feld[z][sp]==evt.getSource()) {
                        System.out.println("Feld " + z + ", " + sp + " geklickt!");
                        f = feld[z][sp];
                        break;
                    }
                }
            }
            
            if ( getZugbeginn() ) {
                if ( (st = f.getStein() ) == null) {
                    return;
                }
                if ( (st.ist_schwarz() && (amZug & SCHWARZ) != 0) || (!st.ist_schwarz() && (amZug & WEISS) != 0)) {
                    f.wegStein();
                    merkeBeginn();
                }
            } else if ( st.istOK(f) && istOK( st, f ) ) {
                f.setStein(st);
                merkeEnde(st);
            }
            else {
                st.getFeld().setStein(st, true);
                st = null;
                istZugbeginn = true;
            }
        }
    }
    FeldListener fl = new FeldListener();
 
}