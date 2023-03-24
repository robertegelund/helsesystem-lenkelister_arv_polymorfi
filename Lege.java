public class Lege implements Comparable<Lege> {
    private String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {return navn;}
    public IndeksertListe<Resept> hentUtskrevneResepter() {return utskrevneResepter;}

    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept); pasient.leggTilResept(nyResept);
        return nyResept;
    }
    
    public Resept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if(legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        MilResept nyResept = new MilResept(legemiddel, this, pasient);
        utskrevneResepter.leggTil(nyResept); pasient.leggTilResept(nyResept);
        return nyResept;
    }

    public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        PResept nyResept = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept); pasient.leggTilResept(nyResept);
        return nyResept;
    }
    
    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof Narkotisk) throw new UlovligUtskrift(this, legemiddel);
        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept); pasient.leggTilResept(nyResept);
        return nyResept;
    }


    @Override
    public String toString() {
        return navn;
    }

    @Override
    public int compareTo(Lege annen) {
        return navn.compareTo(annen.navn);
    }
}
