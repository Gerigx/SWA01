package de.hsos.suchen.ui.suchen.controller;


import de.hsos.suchen.al.WaehleWare;
import de.hsos.suchen.shared.Ware;
import de.hsos.suchen.ui.suchen.view.AuswahlView;

public class AuswahlController {
    
  private AuswahlView auswahlView = new AuswahlView(this);
  private Ware ware=null;
  private WaehleWare waehleWare= null;
  private SuchenStartController suchenStartController=null;
  AuswahlController(SuchenStartController suchenStartController, WaehleWare waehleWare){
    this.suchenStartController=suchenStartController;
    this.waehleWare = waehleWare;
  }
  public void visitAuswahlView(Ware ware){
    if(ware==null){
    auswahlView.errorView();
    }
    else{
      this.ware=ware;
      auswahlView.startView(ware.getName());

    }
  }
  public void returnToMenu(){
    this.suchenStartController.visitSuchenStartView();
  }
  public boolean fuegeWareHinzu(){
    boolean valid;
      valid= waehleWare.wareZuWarenKorbHinzufügen(ware);
      if(valid){
        suchenStartController.setAusgewaehlteWare(null);
      }

      return valid;
  }

}
