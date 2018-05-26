package com.abromand.learning;


import com.abromand.learning.gui.Brett;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Brett().setVisible ( true );
            }
        });
    }
}
