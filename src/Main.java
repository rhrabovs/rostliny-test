import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    /*    System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }*/

        PlantList list = new PlantList();

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

    }

}