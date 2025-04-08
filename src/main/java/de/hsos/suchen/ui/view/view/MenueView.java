package de.hsos.suchen.ui.view.view;

import java.util.Scanner;

import de.hsos.suchen.ui.view.controller.MenueController;

public class MenueView {
         Scanner scanner = new Scanner(System.in);
         MenueController menueController = new MenueController();
         public void startView(){
            boolean validInput=false;
            System.out.println("Optionen: ");
            while(!validInput){
                System.out.println("1) Suchen");
                System.out.println("2) Warenkorb");
                System.out.println("3) Bezahlen ");
              
                int input=scanner.nextInt();
                if(input>0&&input<4){
                    validInput=true;
                    switch (input) {
                        case 1:
                            menueController.visitSuchenStartView();
                            break;
                        case 2:
                        menueController.visitWarenkorbStartView();
                            break;
                        case 3:
                        menueController.visitBezahlenStartView();
                            break;
                        default:
                            break;
                    }
                }
            }
         }
}
