package com.abromand.learning.gui;

import java.awt.Color;
import com.abromand.learning.root.Einfach;
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

        boolean schwarz = true;
        for ( int z=0; z<feld.length; z++ ) {
            for ( int sp=0; sp<feld[z].length; sp++ ) {
                Feld newFeld = new Feld( this, schwarz, z, sp );
                newFeld.setBackground( schwarz ? Color.darkGray : Color.lightGray );
                newFeld.addActionListener(fl);

                if (schwarz) {
                    if ( z <= 3 ) {
                        newFeld.setStein( new Einfach (newFeld, false) );
                    } else if ( z >= 6 ) {
                        newFeld.setStein( new Einfach (newFeld, true) );
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

    public void merkeEnde() {
        istZugbeginn = true;
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
                st = f.getStein();
                if ( (st.ist_schwarz() && (amZug & SCHWARZ) != 0) || (!st.ist_schwarz() && (amZug & WEISS) != 0)) {
                    f.wegStein();
                }
            } else if ( st.istOK(f) ) {
                f.setStein(st);
            }
            else {
                st.getFeld().setStein(st);
            }
        }
    }
    FeldListener fl = new FeldListener();
 
}