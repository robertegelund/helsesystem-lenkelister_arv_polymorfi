import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Legesystem {
    private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
    private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
    private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    private static IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public static void main(String[] args) {
        if (args.length != 0) {
            lesFraFil(args[0]);
        } else {
            lesFraFil("legedata.txt");
        }

        mottaBrukerinput();
    }

    private static void mottaBrukerinput() {
        Scanner sc = new Scanner(System.in);
        print("\n--------------------------------------------------------------------");
        print("Velkommen til ditt personlige legesystem. Vennligst velg fra menyen.");
        print("--------------------------------------------------------------------");

        String brukerInput = "";
        while(handtereBrukerinput(brukerInput, sc)) {
            visBrukermeny();
            brukerInput = "";
            System.out.print("\n# Tast ditt menyvalg (0-5): " + brukerInput);
            brukerInput = sc.nextLine();
        }
        print("\nProgrammet avsluttes. Velkommen tilbake!\n");
        sc.close();
    }

    public static boolean handtereBrukerinput(String brukerInput, Scanner sc) {
        switch(brukerInput) {
            case "0": {visAllInformasjon(); return true;}
            case "1": {return true;}
            case "2": {brukPasientResepter(sc); return true;}
            case "3": {visAntVaneNark(); return true;}
            case "4": {visMuligMisbruk(); return true;}
            case "5": {return false;}
            default: {return true;}
        }
    }

    public static void visAllInformasjon() {
        print("\n--------------------------------------------------------------------");
        print("INFORMASJON OM ALLE PASIENTER");
        print("--------------------------------------------------------------------");
        for(Pasient pasient : pasienter) {print(pasient+"");}
        print("\n--------------------------------------------------------------------");
        print("INFORMASJON OM ALLE LEGEMIDLER");
        print("--------------------------------------------------------------------");
        for(Legemiddel legemiddel : legemidler) {print(legemiddel+"\n");}
        print("\n--------------------------------------------------------------------");
        print("INFORMASJON OM ALLE LEGER");
        print("--------------------------------------------------------------------");
        for(Lege lege : leger) {print(lege+"");}
        print("\n--------------------------------------------------------------------");
        print("INFORMASJON OM ALLE RESEPTER");
        print("--------------------------------------------------------------------");
        for(Resept resept : resepter) {print(resept+"\n");}
        print("--------------------------------------------------------------------");
    }

    public static void brukPasientResepter(Scanner sc) {
        visPasienterMedResepter();
        String brukerInput = "";
        System.out.print("\n# Velg pasient ved aa skrive inn ID: " + brukerInput);
        brukerInput = sc.nextLine();
        visPasientResepter(Integer.parseInt(brukerInput));
        brukerInput = "";
        System.out.print("\n# Velg resept ved aa skrive inn ID: " + brukerInput);
        brukerInput = sc.nextLine();
        brukEnResept(Integer.parseInt(brukerInput));
    }

    public static void brukEnResept(int reseptID) {
        Resept resept = resepter.hent(reseptID);
        resept.bruk();
        if(resept.hentReit() == 0) {
            print("Resept paa " + resept.hentLegemiddel().hentNavn() + 
            " brukt. Ingen gjaenvaerende reiterasjoner.");
        } else {
            print("Resept paa " + resept.hentLegemiddel().hentNavn() + 
            " brukt. Gjenvaerende iterasjoner: " + resept.hentReit() + ".");
        }
    }

    public static void visPasienterMedResepter() {
        print("\n--------------------------------------------------------------------");
        print("PASIENTER SOM HAR RESEPTER");
        print("--------------------------------------------------------------------");
        for(Pasient pasient : pasienter) {
            if(pasient.hentResepter().stoerrelse() != 0) {
                print("ID " + pasient.hentPasientID() + ": " + pasient);
            }
        }
    }

    public static void visPasientResepter(int pasientID) {
        IndeksertListe<Resept> resepter = pasienter.hent(pasientID).hentResepter();
        print("\n--------------------------------------------------------------------");
        print("VISER RESEPT(ER) FOR " + pasienter.hent(pasientID));
        print("--------------------------------------------------------------------");
        for(Resept resept : resepter) {
            print("ID " + resept.hentID() + ": " + 
                    resept.hentLegemiddel().hentNavn() + 
                    " (" + resept.hentReit() + " reit)");
        }
    }

    public static void visAntVaneNark() {
        int antVanedannende = 0;
        int antNarkotiske = 0;

        for(Resept resept : resepter) {
            if(resept.hentLegemiddel() instanceof Vanedannende) {
                antVanedannende++;
            } else if(resept.hentLegemiddel() instanceof Narkotisk) {
                antNarkotiske++;
            }
        }

        print("\n--------------------------------------------------------------------");
        print("Totalt antall utskrevne vanedannende resepter: " + antVanedannende);
        print("Totalt antall utskrevne narkotiske resepter: " + antNarkotiske);
        print("--------------------------------------------------------------------");
    }

    public static void visMuligMisbruk() {
        print("\n--------------------------------------------------------------------");
        print("INFORMASJON OM MULIG NARKOTISK MISBRUK");
        print("--------------------------------------------------------------------");
        
        for(Lege lege : leger) {
            int antNarkRes = 0;
            for(Resept resept : lege.hentUtskrevneResepter()) {
                if(resept.hentLegemiddel() instanceof Narkotisk) {
                    antNarkRes++;
                }
            }
            if(antNarkRes > 0) {
                print(lege + "\n***Antall utskrevne narkotiske resepter: " + antNarkRes+"\n");
            }
        } 

        for(Pasient pasient : pasienter) {
            int antNarkRes = 0;
            for(Resept resept : pasient.hentResepter()) {
                if(resept.hentLegemiddel() instanceof Narkotisk) {
                    antNarkRes++;
                }
            }
            if(antNarkRes > 0) {
                print(pasient + "\n***Antall mottatte narkotiske resepter: " + antNarkRes);
                print("--------------------------------------------------------------------\n");
            }
        } 
    }

    private static void visBrukermeny() {
        print("\n0: Skriv ut oversikt over pasienter, leger, legemidler og resepter");
        print("1: Opprett og legg til nye elementer i systemet");
        print("2: Bruk en resept fra listen til en pasient");
        print("3: Se totalt antall uskrevne resepter paa vanedannende og narkotiske legemidler");
        print("4: Se statistikk om mulig misbruk av narkotika");
        print("5: Avslutt programmet");
    }

    private static void lesFraFil(String filnavn) {
        Scanner sc = null;
        int antLinjer = 0;
        
        try {
            sc = new Scanner(new File(filnavn));
            while(sc.hasNextLine()) {antLinjer++; sc.nextLine();}
            sc.close();
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            print("Filen ble ikke funnet.");
            System.exit(1);
        }

        int linjerPerKlasse = (antLinjer - 4) / 4;
        while(sc.hasNextLine()) {
            String linje = sc.nextLine();
            if (linje.contains("Pasienter")) {
                leggTilPasienter(sc, linjerPerKlasse); 
            } else if(linje.contains("Legemidler")) {
                leggTilLegemidler(sc, linjerPerKlasse);
            } else if(linje.contains("Leger")) {
                leggTilLeger(sc, linjerPerKlasse);
            } else if(linje.contains("Resepter")) {
                leggTilResepter(sc, linjerPerKlasse);
            }
        }
        sc.close();
    }

    private static void leggTilPasienter(Scanner sc, int antPasienter) {
        for(int i = 0; i < antPasienter; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            pasienter.leggTil(new Pasient(deler[0], deler[1]));
        }
    }

    private static void leggTilLegemidler(Scanner sc, int antLegemidler) {
        for(int i = 0; i < antLegemidler; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            String navn = deler[0], type = deler[1]; 
            int pris = Integer.parseInt(deler[2]); 
            double virkestoff = Double.parseDouble(deler[3]); 
            int styrke = (deler.length == 4) ? Integer.parseInt(deler[3]) : 0;
    
            if(type.equals("vanlig")) {
                legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
            } else if(type.equals("vanedannende")) {
                legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
            } else if(type.equals("narkotisk")) {
                legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
            }
        }
    }

    private static void leggTilLeger(Scanner sc, int antLeger) {
        for(int i = 0; i < antLeger; i ++) {
            String[] deler = sc.nextLine().trim().split(",");
            if(deler[1].equals("0")) {
                leger.leggTil(new Lege(deler[0]));
            } else {
                leger.leggTil(new Spesialist(deler[0], deler[1]));
            }
        }
    }

    private static void leggTilResepter(Scanner sc, int antResepter) {
        for(int i = 0; i < antResepter; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            Legemiddel legemiddel = legemidler.hent(Integer.parseInt(deler[0]));
            Lege lege = null;
            for(Lege l : leger) { if(l.hentNavn().equals(deler[1])) lege = l; }
            Pasient pasient = pasienter.hent(Integer.parseInt(deler[2]));
            String type = deler[3];
            int reit = (deler.length == 5) ? Integer.parseInt(deler[4]) : 3;

            try {
                if(type.equals("hvit")) {
                    resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                } else if(type.equals("militaer")) {
                    resepter.leggTil(lege.skrivMilResept(legemiddel, pasient));
                } else if(type.equals("p")) {
                    resepter.leggTil(lege.skrivPResept(legemiddel, pasient, reit));
                } else if(type.equals("blaa")) {
                    resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                }
            } catch(UlovligUtskrift e) {
                print(e.getMessage() + ". Resept ikke opprettet.");
            }
        }
    }

    private static void print(String string) {
        System.out.println(string);
    }
}
