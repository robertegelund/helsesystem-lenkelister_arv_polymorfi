public class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {
    //I en prioritetskoe ligger de minste elementene fremst og de stoerste elementene bakerst
    //Det minste elementet hentes med hent() fra Lenkeliste (ingen override noedvendig)
    //Det minste elementet fjernes med fjern() fra Lenkeliste (ingen override noedvendig)

    @Override
    public void leggTil(E x) {
        if(forste == null || x.compareTo(siste.data) >= 0) {
            super.leggTil(x); 
            return;
        }

        Node nyNode = new Node(x);
        stoerrelse++;

        if(x.compareTo(forste.data) <= 0) {
            nyNode.neste = forste;
            forste = nyNode;
            return;
        }

        Node peker = forste;
        while(peker.neste != null && !(x.compareTo(peker.neste.data) < 0)) {
            peker = peker.neste;
        }
        nyNode.neste = peker.neste;
        peker.neste = nyNode;
    }
    
}
