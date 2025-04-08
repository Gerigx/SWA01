package de.hsos.suchen.ui.view.controller;

import de.hsos.suchen.ui.bezahlen.view.BezahlenStartView;
import de.hsos.suchen.ui.suchen.controller.SuchenStartController;
import de.hsos.suchen.ui.view.view.BegruessungView;
import de.hsos.suchen.ui.view.view.MenueView;
import de.hsos.suchen.ui.warenkorb.view.WarenkorbStartView;

public class MenueController {
    private BegruessungView begruessungView;
    private MenueView menueView;
    private BezahlenStartView bezahlenStartView;
    private WarenkorbStartView warenkorbStartView;
    private SuchenStartController suchenStartController;
   public  MenueController(){
        begruessungView= new BegruessungView();
        menueView= new MenueView();
        bezahlenStartView =new BezahlenStartView();
        warenkorbStartView =new WarenkorbStartView();
        suchenStartController = new SuchenStartController();

    }
    public  void   visitBegruessungView() {
       begruessungView.startView();
    }
    public void visitMenueView(){
        menueView.startView();
    }
    public void visitBezahlenStartView(){
        bezahlenStartView.startView();
    }
    public void visitWarenkorbStartView(){
        warenkorbStartView.startView();
    }
    public void visitSuchenStartView(){
        suchenStartController.visitSuchenStartView();
    }

}
