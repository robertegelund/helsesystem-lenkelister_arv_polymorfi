abstract class Lenkeliste<E> implements Liste<E> {
    //I denne superklassen legges elementer inn bakerst og elementer fjernes forrerst (FIFO)

    protected Node forste = null; //null hvis ingen elementer i lista, en nodereferanse ellers
    protected Node siste = null; ////null hvis ingen elementer i lista, en nodereferanse ellers
    protected int stoerrelse = 0; //0 hvis forste = siste = null (ingen listeelementer), >0 ellers

    protected class Node {
        Node neste = null; //null hvis bare én node i lista, en nodereferanse ellers
        E data = null; //aldri null naar et nodeobjekt er opprettet med data

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        stoerrelse++;
        if(forste == null) {
            forste = siste = nyNode;
            return;
        }
        siste.neste = nyNode;
        siste = nyNode;
    }

    @Override
    public E hent() throws UgyldigListeindeks {
        if(stoerrelse == 0) throw new UgyldigListeindeks(0);
        return forste.data;
    }

    @Override
    public E fjern() throws UgyldigListeindeks {
        if(stoerrelse == 0) throw new UgyldigListeindeks(-1);
        
        E forsteVerdi = forste.data;
        if(forste != siste) {
            forste = forste.neste;
        } else {
            forste = siste = null;
        }
        
        stoerrelse--;
        return forsteVerdi;
    }

    @Override
    public int stoerrelse() { 
        return stoerrelse; 
    }

    @Override 
    public String toString() {
        String innhold = "";
        Node peker = forste; int teller = 0;
        while(peker != null) {
            innhold += "\n" + teller + ": " + peker.data;
            peker = peker.neste;
            teller++;
        }
        innhold += "\n";
        return innhold;
    }
}