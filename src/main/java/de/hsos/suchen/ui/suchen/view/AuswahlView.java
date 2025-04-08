package de.hsos.suchen.ui.suchen.view;


import java.util.Scanner;
import de.hsos.suchen.ui.suchen.controller.AuswahlController;

public class AuswahlView {
     private Scanner scanner = new Scanner(System.in);
     private AuswahlController auswahlController= null;
     public AuswahlView(AuswahlController auswahlController){
        this.auswahlController= auswahlController;
     }
    public void startView( String warenName){
        boolean validInput= false;
        while (!validInput) {
            System.out.println("Möchten sie "+warenName+" zum Warenkorb hinzufügen?");
            System.out.println("1) ja");
            System.out.println("2) Zurück zum Menü");
            int input = scanner.nextInt();
            if(input>0&&input<3){
                validInput=true;
                switch (input) {
                    case 1:
                        boolean succses =auswahlController.fuegeWareHinzu();
                        succesView(succses);
                        break;
                    case 2:
                        auswahlController.returnToMenu();
                         break;
                    default:
                        break;
                }
            }
            
        }
    }
    public void errorView(){
        boolean validInput= false;
        while (!validInput) {
            System.out.println("Keine Ware gefunden. Drücken sie 1)");
            int input=scanner.nextInt();
            if(input==1){
                validInput=true;
                 auswahlController.returnToMenu();
                }
        
        }
    }
    private void succesView(boolean succses){
        if(!succses){
        errorView();
        }
        else{
            boolean validInput= false;
            while (!validInput) {
                System.out.println("Ware erfolgreich hinzugefügt. Drücken sie 1)");
                int input=scanner.nextInt();
                if(input==1){
                    validInput=true;
                    auswahlController.returnToMenu();
                }
            }
        }  
    }
}
