package de.hsos.suchen.bl;

public interface Katalog {
    public void legeBuchungsAlgorithmusFest(SuchAlgorithmus suchAlgorithmus);
    public Ware suchen(String warenname);
    public Ware suchen(Long warennumer);
    public void gebeProduktInformation(Ware ware);
}
