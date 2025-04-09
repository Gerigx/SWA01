package de.hsos.suchen.ui.suchen.controller;


import de.hsos.suchen.al.WaehleWare;
import de.hsos.suchen.al.WarenBeobachter;
import de.hsos.suchen.bl.Ware;
import de.hsos.suchen.ui.suchen.view.AuswahlView;

public class AuswahlController implements WarenBeobachter {
    
  private AuswahlView auswahlView;
  private Ware ware;
  private WaehleWare waehleWare;
  private SuchenStartController suchenStartController;

  public AuswahlController(SuchenStartController suchenStartController, WaehleWare waehleWare) {
    this.suchenStartController = suchenStartController;
    this.waehleWare = waehleWare;
    this.auswahlView = new AuswahlView(this);
    
    // Observer pattern
    if (waehleWare instanceof de.hsos.suchen.al.WarenSubject) {
        ((de.hsos.suchen.al.WarenSubject) waehleWare).registriereBeobachter(this);
    }
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
        if (ware == null) {
            return false;
        }
        
        boolean erfolg = waehleWare.wareZuWarenKorbHinzufügen(ware);
        
        if (erfolg) {
            suchenStartController.setAusgewaehlteWare(null);
        }
        
        return erfolg;
  }

  @Override
  public void wareWurdeAusgewaehlt(Ware ware) {
      System.out.println("Benachrichtigung: Ware '" + ware.getName() + "' wurde zum Warenkorb hinzugefügt.");
  }

}
