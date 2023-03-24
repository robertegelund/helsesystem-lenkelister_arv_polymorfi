abstract class Resept {
    private static int antResepter = 0;
    private int reseptID, reit;
    private Legemiddel legemiddel;
    private Lege utskrivendeLege;
    private Pasient pasient;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        reseptID = antResepter;
        antResepter++;
    }

    public boolean bruk() {
        if(reit <= 0) return false;
        reit--;
        return true;
    }

    abstract public String farge();
    abstract public int prisAaBetale();

    public int hentId() {return reseptID;}
    public Legemiddel hentLegemiddel() {return legemiddel;}
    public Lege hentLege() {return utskrivendeLege;}
    public int hentReit() {return reit;}

    @Override
    public String toString() {
        String info = "";
        info += "Resept-ID: " + reseptID + "\n";
        info += "Reseptfarge: " + farge() + "\n";
        info += "Legemiddel: " + legemiddel.hentNavn() + "\n";
        info += "Utskrivende lege: " + utskrivendeLege.hentNavn() + "\n";
        info += "Pasient-ID: " + pasient.hentPasientID() + "\n";
        info += "Pris: " + prisAaBetale() + "\n";
        info += "Reiterasjoner: " + reit;
        return info;
    }

}
