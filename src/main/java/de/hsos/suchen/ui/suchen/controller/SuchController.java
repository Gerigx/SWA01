package de.hsos.suchen.ui.suchen.controller;


import java.util.ArrayList;

import de.hsos.suchen.al.SucheWare;
import de.hsos.suchen.shared.Ware;
import de.hsos.suchen.ui.suchen.view.SuchView;

public class SuchController {
    private SucheWare sucheWare= null;
    private SuchenStartController suchenStartController=null; 
    private SuchView suchView = new SuchView(this);
    private Ware gefundeneWare = null;
    private ArrayList<Ware> gefundenWaren=null;
    public SuchController(SuchenStartController suchenStartController, SucheWare sucheWare){
        this.suchenStartController =suchenStartController;
        this.sucheWare=sucheWare;
    }
    public void visitSuchView(){
        suchView.startView();
    }

    public void returnToMenu(){
        this.suchenStartController.visitSuchenStartView();
    }

    public String warenSuche(long warennumer){
        gefundeneWare=sucheWare.sucheWare(warennumer);
        return gefundeneWare.getName();
    }
    public ArrayList<String> wareSuchen(String ware){
        gefundenWaren=sucheWare.sucheWaren(ware);
        ArrayList<String> waren=new ArrayList<String>();
        for(Ware w : gefundenWaren){
            waren.add(w.getName()+": "+w.getWarennummer());
        }
        return waren;
    }
    public void setWare(){
        suchenStartController.setAusgewaehlteWare(gefundeneWare);
    }
    public void setWare(int ware){
        suchenStartController.setAusgewaehlteWare(gefundenWaren.get(ware));
    }

}
