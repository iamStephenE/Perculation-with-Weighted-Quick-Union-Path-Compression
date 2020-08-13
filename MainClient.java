public class MainClient {

    public static void main(String[] args) {
        // Trying it on a 1000x1000
        Perculate simulation = new Perculate(1000);

        while (!simulation.perculates()) {
            simulation.openRandomSite();
        }

        simulation.showGrid();
        System.out.println(simulation.siteVacancyPercentage());
    }

}
