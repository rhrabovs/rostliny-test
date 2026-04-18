import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PlantList list = new PlantList();

        /**
        try {
            list.addPlant(new Plant(
                    "Aloe",
                    "Pozn.",
                    LocalDate.now().minusDays(10),
                    LocalDate.now().minusDays(10),
                    7
            ));

            list.addPlant(new Plant(
                    "Kaktus",
                    "mala zalivka",
                    LocalDate.of(2025,01,10),
                    LocalDate.of(2025,11,20),
                    100
            ));
        } catch (PlantException e) {
            System.out.println("Chyba pri vytvareni rostliny: "+e.getLocalizedMessage());
        }

        List<Plant> toWater = list.getPlantsToWater();

        for (Plant p : toWater){
            System.out.println(p.getName() + " potrebuje zalit.");
        }

        // seradit podle nazvu
        list.sortByName();

        // seradit podle posledni zalivky
        list.sortByWateringDate();
        */

        // (1) nacteni seznamu kvetin ze souboru
        try{
            String filename = "kvetiny.txt";

            System.out.println("Nacitani seznamu kvetin ze souboru " + filename);
            list.loadFromFile(filename,"\t");
            System.out.println("Nacteni dokonceno!");
        } catch (IOException | PlantException e){
            System.out.println("Chyba pri nacitani: \n" + e.getMessage());
        }

        System.out.println();

        // (2) vypis informaci o zalivce
        System.out.println("Informace o zalivce\n===================");
        for (Plant p : list.getPlantCopy()){
            System.out.println(p.getWateringInfo());
        }

        System.out.println();

        // (3) pridani kmvetiny
        try {
            String jmenoKytky = "Monstera";
            //Plant novaKytka = new Plant(jmenoKytky, "Velka listova rostlina", LocalDate.now().minusDays(30),
            //        LocalDate.now().minusDays(5),7);

            // viz poznamka v konstruktoru
            Plant novaKytka = new Plant(jmenoKytky, "Velka listova rostlina", 7,LocalDate.now().minusDays(5),
                    LocalDate.now().minusDays(30));

            list.addPlant(novaKytka);
            System.out.println("Pridana nova rostlina " + jmenoKytky + "\n");
        } catch (PlantException e) {
            System.out.println("Chyba pri vlozeni nove rostliny: \n" + e.getMessage());
        }

        System.out.println();

        // (4) pridani 10 tulipanu
        System.out.println("Pridavam 10 tulipanu ");
        for (int i = 1; i<=10; i++){
            try {
                //list.addPlant(new Plant(
                //            "Tulipan na prodej " + i,
                //                  "",
                //                  LocalDate.now(),
                //                  LocalDate.now(),
                //                  14
                //));

                // viz poznamka v konstruktoru
                list.addPlant(new Plant(
                        "Tulipan na prodej " + i,
                        "",
                        14,
                        LocalDate.now(),
                        LocalDate.now()
                ));
            } catch (PlantException e) {
                System.out.println("Chyba pri pridani tulipanu: \n" + e.getMessage());
            }
        }

        System.out.println("Tulipany doplneny do seznamu.\n");

        // (5) odebrani kvetiny na treti pozici v seznamu
        String delPlant;
        if (list.getPlantCopy().size() >= 3){

            delPlant = String.valueOf(list.getPlant(2));
            System.out.println("Ted odeberu rostlinu na treti pozici v seznamu");
            list.removePlant(2);
            System.out.println("Rostlina ("+ delPlant + ") byla odebrana.\n");
        }

        // (6) ulozeni seznamu do noveho souboru
        try {
            String filename = "kvetiny_export.txt";

            System.out.println("Ukladam seznam rostlin do noveho souboru ("+ filename + ")");
            list.saveToFile(filename,"\t");
            System.out.println("Ulozeno!\n");
        } catch (IOException e) {
            System.out.println("Chyba pri ukladani do souboru:\n" + e.getMessage());
        }

        // (7) opetovne nacteni vytvoreneho souboru
        PlantList load = new PlantList();
        try {
            String filename = "kvetiny_export.txt";

            System.out.println("Nacteni exportovaneho souboru ("+filename+")");
            load.loadFromFile(filename,"\t");
            System.out.println("Soubor byl nacten!\n");
        } catch (IOException | PlantException e) {
            System.out.println("Chyba pri opetovnem nacteni souboru:\n" + e.getMessage());
        }

        // (8) setrideni rostlin podle ruznych kriterii
        System.out.println("Trideni podle nazvu:\n====================");
        load.sortByName();
        for (Plant p : load.getPlantCopy()){
            System.out.println(p.getName());
        }
        System.out.println();

        System.out.println("Trideni podle data posledni zalivky:\n====================================");
        load.sortByWateringDate();
        for (Plant p : load.getPlantCopy()){
            System.out.printf("%-25s %10s\n", p.getName(),p.getWatering());
        }
    }

}