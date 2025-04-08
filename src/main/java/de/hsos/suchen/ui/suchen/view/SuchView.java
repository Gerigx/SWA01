package de.hsos.suchen.ui.suchen.view;

import java.util.ArrayList;
import java.util.Scanner;

import de.hsos.suchen.ui.suchen.controller.SuchController;

public class SuchView {
    private Scanner scanner = new Scanner(System.in);
    private SuchController suchController=null;
    public SuchView(SuchController suchController){
        this.suchController=suchController;
    }
    public void startView(){
        boolean validInput=false;
        while(!validInput){
            System.out.println("Bitte Wählen sie eine Suchoption:");
            System.out.println("1) Suche über Warennummer");
            System.out.println("2) Suche nach produktname");
            int input=scanner.nextInt();
            if(input>0&&input<3){
                switch (input) {
                    case 1:
                        warennummer();
                        break;
                    case 2:
                        warenname();
                        break;
                    default:
                        break;
                }
                validInput=true;
            }
        }
         validInput=false;
         while(!validInput){
             System.out.println("Bitte Wählen sie eine Option:");
             System.out.println("1) nochmal Suchen");
             System.out.println("2) zurück zum Menu");
            int input= scanner.nextInt();
             if(input>0&&input<3){
                validInput=true;
                 switch (input) {
                    case 1:
                        startView();
                        break;
                
                     case 2:
                        suchController.returnToMenu();
                        break;
                    default:
                   
                        break;
            }
        }
    }
    }
    public void warennummer(){
        boolean validInput=false;
        while(!validInput){
        System.out.println("Bitte geben sie eine Warennummer ein");
        long input=scanner.nextLong();
       String result= suchController.warenSuche(input);
        if(result!=null){
            
            boolean validInputConfirmation=false;
            while (!validInputConfirmation) {
                
            System.out.println("Wollen sie die Ware speichern?");
            System.out.println("1) ja");
            System.out.println("2) nein");
            int confirm=scanner.nextInt();
            if(confirm>0&&confirm<3)
                if(confirm==1){
                    suchController.setWare();
                    System.out.println("Ware wurde gespeichert");
                }
                else{
                    System.out.println("Ware wurde nicht gespeichert");                    
                }
            validInputConfirmation=true;
            }
        }
        else{
            System.out.println("Ware konnte nicht gefunden werden");
        }
    }
    }
    public void warenname(){
        boolean validInput=false;
        while(!validInput){
        System.out.println("Bitte geben sie einen Warennamen ein");
        String input=scanner.nextLine();
       ArrayList<String> results= suchController.wareSuchen(input);
        if(!results.isEmpty()){
            
            boolean validInputConfirmation=false;
            while (!validInputConfirmation) {
                
            System.out.println("Wollen sie eine der Waren speichern? bitte geben sie eine Nummer ein");
                int i=0;
            while(i<results.size()){
                System.out.println(i+") "+results.get(i));
                i++;
            }
            System.out.println(i+1+")"+" Nein");
            int confirm=scanner.nextInt();
            if(confirm>-1&&confirm<results.size()+1)
                if(confirm<results.size()){
                    suchController.setWare(confirm);
                    System.out.println("Ware wurde gespeichert");
                }
                else{
                    System.out.println("Ware wurde nicht gespeichert");                    
                }

            }
        }
        else{
            System.out.println("Ware konnte nicht gefunden werden");
        }
    }
    
    }
    
}
