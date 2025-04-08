package de.hsos.suchen.ui.suchen.controller;

import java.util.ArrayList;

import de.hsos.suchen.al.EinkaueferIn;
import de.hsos.suchen.shared.Ware;
import de.hsos.suchen.ui.suchen.view.SuchenStartView;
import de.hsos.suchen.ui.view.controller.MenueController;

public class SuchenStartController {
    private EinkaueferIn einkaueferIn= new EinkaueferIn();
    private AuswahlController auswahlController= new AuswahlController(this, einkaueferIn);
    private SuchController suchController=new SuchController(this, einkaueferIn);
    private PruefController pruefController = new PruefController(this, einkaueferIn);
    private MenueController menueController= new MenueController();
    private SuchenStartView suchenStartView = new SuchenStartView(this);
    private ArrayList<Ware> gefundeneWaren= new ArrayList<Ware>();
    private Ware ausgewaehlteWare=null; 

    public void visitMenueView(){
        menueController.visitMenueView();
    }

    public void visitAuswahlView(){
        auswahlController.visitAuswahlView(ausgewaehlteWare);
    }

    public void visitSuchView(){
        suchController.visitSuchView();
    }

    public void visitPruefView(){
        pruefController.visitPruefView(ausgewaehlteWare);
    }

    public void visitSuchenStartView(){
        suchenStartView.startView();
    }
    
    public void setAusgewaehlteWare(Ware ausgewaehlteWare) {
        this.ausgewaehlteWare = ausgewaehlteWare;
    }

    public Ware getAusgewaehlteWare() {
        return ausgewaehlteWare;
    }

    public void setGefundeneWaren(ArrayList<Ware> gefundeneWaren) {
        this.gefundeneWaren = gefundeneWaren;
    }

    public ArrayList<Ware> getGefundeneWaren() {
        return gefundeneWaren;
    }
}
