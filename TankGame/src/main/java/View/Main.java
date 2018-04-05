package View;

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex ){
            System.out.println("Error during UnsupportedLookAndFeel initilalization");
        }
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
