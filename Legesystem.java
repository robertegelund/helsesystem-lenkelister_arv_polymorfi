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
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Velkommen til ditt personlige legesystem. Vennligst velg fra menyen.");
        System.out.println("--------------------------------------------------------------------");

        String brukerInput = "";
        while(!brukerInput.equals("5")) {
            brukerInput = "";
            visBrukermeny();
            System.out.print("\n# Tast ditt menyvalg (0-6): " + brukerInput);
            brukerInput = sc.nextLine();
            handtereBrukerinput(brukerInput);
        }
        System.out.println("\nProgrammet avsluttes. Velkommen tilbake!\n");
        sc.close();
    }

    public static void handtereBrukerinput(String brukerInput) {
        switch(brukerInput) {
            case "0": {skrivUtAllInformasjon(); break;}
            case "1": {skrivUtAllInformasjon(); break;}
            case "2": {skrivUtAllInformasjon(); break;}
            case "3": {skrivUtAntVaneNark(); break;}
            case "4": {skrivUtAllInformasjon(); break;}
            case "5": {skrivUtAllInformasjon(); break;}
        }
    }

    public static void skrivUtAllInformasjon() {
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("INFORMASJON OM ALLE PASIENTER");
        System.out.println("--------------------------------------------------------------------");
        for(Pasient pasient : pasienter) {System.out.println(pasient);}
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("INFORMASJON OM ALLE LEGEMIDLER");
        System.out.println("--------------------------------------------------------------------");
        for(Legemiddel legemiddel : legemidler) {System.out.println(legemiddel+"\n");}
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("INFORMASJON OM ALLE LEGER");
        System.out.println("--------------------------------------------------------------------");
        for(Lege lege : leger) {System.out.println(lege);}
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("INFORMASJON OM ALLE RESEPTER");
        System.out.println("--------------------------------------------------------------------");
        for(Resept resept : resepter) {System.out.println(resept+"\n");}
    }

    public static void skrivUtAntVaneNark() {
        int antVanedannende = 0;
        int antNarkotiske = 0;

        for(Resept resept : resepter) {
            if(resept.hentLegemiddel() instanceof Vanedannende) {
                antVanedannende++;
            } else if(resept.hentLegemiddel() instanceof Narkotisk) {
                antNarkotiske++;
            }
        }

        System.out.println("\n--------------------------------------------------------------------");
        System.out.println("Totalt antall utskrevne vanedannende resepter: " + antVanedannende);
        System.out.println("Totalt antall utskrevne narkotiske resepter: " + antNarkotiske);
        System.out.println("--------------------------------------------------------------------");
    }

    private static void visBrukermeny() {
        System.out.println("\n0: Skriv ut oversikt over pasienter, leger, legemidler og resepter");
        System.out.println("1: Opprett og legg til nye elementer i systemet");
        System.out.println("2: Bruk en resept fra listen til en pasient");
        System.out.println("3: Se totalt antall uskrevne resepter paa vanedannende og narkotiske legemidler");
        System.out.println("4: Se statistikk om mulig misbruk av narkotika");
        System.out.println("5: Avslutt programmet");
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
            System.out.println("Filen ble ikke funnet.");
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
                System.out.println(e.getMessage() + ". Resept ikke opprettet.");
            }
        }
    }
}
