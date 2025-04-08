package de.hsos.suchen.ui.warenkorb.view;

import java.util.Scanner;

import de.hsos.suchen.ui.view.controller.MenueController;

public class WarenkorbStartView {
     Scanner scanner = new Scanner(System.in);
     private MenueController menueController = new MenueController();

    public void startView(){
        boolean validInput=false;
        System.out.println("Warenkorb");
        while(!validInput){
            System.out.println("1) Weiter zum Menü");
            int input=scanner.nextInt();
            if(input==1){
                validInput=true;
                menueController.visitMenueView();
            }

    }
}
}
