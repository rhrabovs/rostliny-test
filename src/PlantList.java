import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantList {
    private List<Plant> plants;

    public PlantList(){
        this.plants = new ArrayList<>();
    }

    // -- komparator pro razeni podle posledni zalivky --
    public static final Comparator<Plant> BY_WATERING_DATE = Comparator.comparing(Plant::getWatering);

    // -- metody razeni --

    //  vychozi razeni podle nazvu
    public void sortByName(){
        plants.sort(null); // pouzije Plant.comparableTo()
    }

    // razeni podle data posledni zalivky
    public void sortByWateringDate(){
        plants.sort(BY_WATERING_DATE);
    }

    // (1) pridani nove rostliny do seznamu
    public void addPlant(Plant plant){
        plants.add(plant);
    }

    // (2) ziskani rostliny na zadanem indexu
    public Plant getPlant(int idx){
        return plants.get(idx);
    }

    // (3) odebrani rostliny ze seznamu
    public void removePlant(int idx){
        plants.remove(idx);
    }

    // (4) vytvoreni kopie seznamu rostlin

    public List<Plant> getPlantCopy() {
        return new ArrayList<>(plants);
    }

    // (5) seznam rostlin, ktere je potreba zalit
    public List<Plant> getPlantsToWater(){
        List<Plant> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Plant p : plants){
            LocalDate nextWatering = p.getWatering().plusDays(p.getFrequencyOfWatering());

            if (nextWatering.isBefore(today)){
                result.add(p);
            }
        }

        return result;
    }

    // ulozeni seznamu rostlin do souboru
    public void saveToFile(String filename, String delimiter) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (Plant p : plants) {
                //writer.write(
                //        p.getName()+ delimiter +
                //            p.getNotes()+ delimiter +
                //            p.getPlanted()+ delimiter +
                //            p.getWatering()+ delimiter +
                //            p.getFrequencyOfWatering()
                //);
                // viz poznamka v konstruktoru
                writer.write(
                        p.getName()+ delimiter +
                                p.getNotes()+ delimiter +
                                p.getFrequencyOfWatering() + delimiter +
                                p.getWatering()+ delimiter +
                                p.getPlanted()+ delimiter

                );
                writer.newLine();
            }
        }
    }

    // nacteni seznamu rostlin ze souboru
    public void loadFromFile(String filename, String delimiter) throws IOException, PlantException {
        plants.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(delimiter);

                if (parts.length != 5) {
                    throw new IOException("Chybny format radku: "+ line);
                }

                String name = parts[0];
                String notes = parts[1];
                int frequencyOfWatering = Integer.parseInt(parts[2]); // presunute z konce na 3 pozici, viz poznamka v konstruktoru
                LocalDate watering = LocalDate.parse(parts[3]);       // proti zadani prehozene watering a planted
                LocalDate planted = LocalDate.parse(parts[4]);


                // muze vyhodit vyjimku PlantException > predame ji dal
                //Plant plant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                Plant plant = new Plant(name, notes, frequencyOfWatering,watering, planted); // viz poznamka v konstruktoru
                plants.add(plant);
            }
        } catch (Exception e) {
            System.out.println("Chyba pri nacitani souboru:\n" + e.getMessage());
            plants.clear(); // ponechame prazdny seznam
        }
    }

}
