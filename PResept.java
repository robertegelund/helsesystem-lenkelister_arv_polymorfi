class PResept extends HvitResept {
    
    PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public int prisAaBetale() {
        int pris = hentLegemiddel().hentPris() - 108; 
        if(pris < 0) return 0;
        return pris;
    }

}
