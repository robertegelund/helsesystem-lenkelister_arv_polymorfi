class BlaaResept extends Resept {
    
    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {return "blaa";}

    @Override
    public int prisAaBetale() {
        double pris = 0.25*hentLegemiddel().hentPris(); 
        return (int) Math.round(pris);
    }
   
}
