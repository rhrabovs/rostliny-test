import java.time.LocalDate;


public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    // 1) konstruktor, ktery nastavi vsechny atributy
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {

        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zalevani musi byt kladne cislo.");
        }

        if (watering.isBefore(planted)) {
            throw new PlantException("Datum posledni zalivky nesmi byt drive nez datum vysadby");
        }

        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // 2) konstruktor: notes = "", planted = today, watering today
    public Plant(String name, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zalevani musi byt kladne cislo");
        }

        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // 3) konstruktor: jako (2) + frequency = 7
    public Plant(String name) {
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }

    // gettery a settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) throws PlantException {
        if (planted.isAfter(LocalDate.now())) throw new PlantException("Datum zasazeni nesmi byt v budoucnosti.");
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws  PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum posledni zalivky nesmi byt drive nez datum vysadby");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0){
            throw new PlantException("Frekvence zalevani musi byt kladne cislo");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // 1) vrati textovou informaci o zalevani
    public String getWateringIngo(){
        LocalDate nextWatering = watering.minusDays(frequencyOfWatering);

        return "Rostlina: " + name + ", posledni zalivka: " + watering + ", doporucena dalsi zalivka: " + nextWatering;
    }

    // 2) nastaveni posledni zalivky na dnesek
    public void doWateringNow() {
        this.watering = LocalDate.now();
    }

    // comparableTo()

    @Override
    public int compareTo(Plant o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
