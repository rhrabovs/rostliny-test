import java.time.LocalDate;


public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    /** ty vole, tak oni tam daji uplne blby soubor, ktery ma uplne jine rozlozeni
     * dat, nez uvadeji v zadani ukolu, to snad ne. A clovek pak muze debuggovat jak
     * debil protoze mu nenacita ani soubor, ktery je spravny!!!!!
     *
     * Takze musim opravit (prizpusobit) konstruktor tak, aby vyhovoval jejich blbemu souboru
     */

    /**
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
    */

    // konstuktory uoravene dle zadanych souboru v projektu,
    // ne podle zadani v projektu!!!

    // (1) konstruktor, ktery nastavi vsechny atributy
    public Plant(String name, String notes, int frequencyOfWatering,LocalDate watering, LocalDate planted) throws PlantException {

        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zalevani musi byt kladne cislo.");
        }

        if (watering.isBefore(planted)) {
            throw new PlantException("Datum posledni zalivky nesmi byt drive nez datum vysadby");
        }

        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        this.watering = watering;
        this.planted = planted;
    }

    // (2) konstruktor: notes = "", planted = today, watering today
    public Plant(String name, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zalevani musi byt kladne cislo");
        }

        this.name = name;
        this.notes = "";
        this.frequencyOfWatering = frequencyOfWatering;
        this.watering = LocalDate.now();
        this.planted = LocalDate.now();

    }

    // (3) konstruktor: jako (2) + frequency = 7
    public Plant(String name) {
        this.name = name;
        this.notes = "";
        this.frequencyOfWatering = 7;
        this.watering = LocalDate.now();
        this.planted = LocalDate.now();

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
    public String getWateringInfo(){
        LocalDate lastWatering = getWatering();
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);

        while (!nextWatering.isAfter(getWatering())){
            nextWatering = nextWatering.plusDays(frequencyOfWatering);
        }
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

    @Override
    public String toString() {
        return name;
    }
}
