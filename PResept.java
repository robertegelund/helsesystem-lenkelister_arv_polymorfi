class PResept extends HvitResept {
    
    PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        int pris = hentLegemiddel().hentPris() - 108; 
        if(pris < 0) return 0;
        return pris;
    }

}
