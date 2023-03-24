abstract class Resept {
    private static int antResepter = 0;
    private int ID;
    private Legemiddel legemiddel;
    private Lege utskrivendeLege;
    private int pasientId, reit;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        ID = antResepter;
        antResepter++;
    }

    public boolean bruk() {
        if(reit <= 0) return false;
        reit--;
        return true;
    }

    abstract public String farge();
    abstract public int prisAaBetale();

    public int hentId() {return ID;}
    public Legemiddel hentLegemiddel() {return legemiddel;}
    public Lege hentLege() {return utskrivendeLege;}
    public int hentPasientId() {return pasientId;}
    public int hentReit() {return reit;}

    @Override
    public String toString() {
        String info = "";
        info += "Resept-ID: " + ID + "\n";
        info += "Reseptfarge: " + farge() + "\n";
        info += "Legemiddel: " + legemiddel.navn + "\n";
        info += "Utskrivende lege: " + utskrivendeLege.hentNavn() + "\n";
        info += "Pasient-ID: " + pasientId + "\n";
        info += "Pris: " + prisAaBetale() + "\n";
        info += "Reiterasjoner: " + reit;
        return info;
    }

}
