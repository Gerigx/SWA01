package de.hsos.suchen.ui.suchen.view;

import java.util.ArrayList;
import java.util.Scanner;

import de.hsos.suchen.ui.suchen.controller.PruefController;

public class PruefView {
    private Scanner scanner = new Scanner(System.in);
    private PruefController pruefController=null;
    public PruefView(PruefController pruefController){
      this.pruefController=pruefController;
    }
    public void startView(String ware){
      System.out.println("Informationen zu "+ware+":");
      ArrayList<String> informationen=pruefController.getProduktInformationen();
      for(String info: informationen){
        System.out.println(info);
      }
       boolean validInput=false;
       while(!validInput){
        System.out.println("Kehre mit 1) zum Menü zurück");
        int input=scanner.nextInt();
        if(input==1){
            validInput=true;
            pruefController.returnToMenu();
         }
       }
    }
    public void errorView(){
    
        boolean validInput= false;
        while (!validInput) {
            System.out.println("Keine Ware gefunden. Drücken sie 1) um zum Menü zurück zukehren");
            int input=scanner.nextInt();
          if(input==1){
              validInput=true;
              pruefController.returnToMenu();
           }
        
         }
    }

}
