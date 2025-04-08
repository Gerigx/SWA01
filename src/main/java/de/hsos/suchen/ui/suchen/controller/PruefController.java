package de.hsos.suchen.ui.suchen.controller;

import java.util.ArrayList;

import de.hsos.suchen.al.PruefeWare;
import de.hsos.suchen.shared.Produktinformation;
import de.hsos.suchen.shared.Ware;
import de.hsos.suchen.ui.suchen.view.PruefView;

public class PruefController {
    private PruefView pruefView =new PruefView(this);
    private PruefeWare pruefeWare=null;
    private Ware ware=null;
    private SuchenStartController suchenStartController=null;
    public PruefController(SuchenStartController suchenStartController, PruefeWare pruefeWare){
        this.suchenStartController=suchenStartController;
        this.pruefeWare=pruefeWare;
    }
    public void visitPruefView(Ware ware){
        if(ware!=null){
            this.ware=ware;
            pruefView.startView(ware.getName());}
        else{
            pruefView.errorView();
        }
    }
    
    public void returnToMenu(){
        suchenStartController.visitSuchenStartView();
    }

    public ArrayList<String> getProduktInformationen(){
        ArrayList<Produktinformation> produktinformationen=pruefeWare.holeDetailInformationen(ware);
        ArrayList<String> informationen=new ArrayList<String>();
        for(Produktinformation p :produktinformationen){
            informationen.add(p.toString());
        }
        return informationen;
    }
    
}
