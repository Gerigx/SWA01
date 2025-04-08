package de.hsos.suchen.ui.suchen.view;

import java.util.Scanner;
import de.hsos.suchen.ui.suchen.controller.SuchenStartController;


public class SuchenStartView {
     private Scanner scanner = new Scanner(System.in);
     private SuchenStartController suchenStartController= null;
     public SuchenStartView(SuchenStartController suchenStartController){
          this.suchenStartController=suchenStartController;
     }

     public void startView(){
          boolean validInput=false;
          System.out.println("Optionen: ");
            while(!validInput){
                System.out.println("1) Hauptmenü");
                System.out.println("2) Suchen");
                System.out.println("3) Pruefen ");
                System.out.println("4) Auswahl ");
              
                int input=scanner.nextInt();
                if(input>0&&input<5){
                    validInput=true;
                    switch (input) {
                        case 1:
                             suchenStartController.visitMenueView();
                             break;
                        case 2:
                             suchenStartController.visitSuchView();
                             break;
                        case 3:
                             suchenStartController.visitPruefView();
                             break;
                        case 4:
                             suchenStartController.visitAuswahlView();
                             break;
                        default:
                             break;
                    }
                }
          }
     }
}
