class Narkotisk extends Legemiddel {
    public final int styrke;

    Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += "\nStyrke: " + styrke;
        return info;
    }
} 
