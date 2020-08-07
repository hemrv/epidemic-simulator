
public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Country fakeLand = new Country(100000,1,2,
                5,10,1);
        Disease plague  = new Disease(0.1,7,0.3,0.1,14);
        Simulator simulator = new Simulator(fakeLand,plague,0);
        int cumulativeDeaths = 0;
        int cumulativeCases  = 0;
        for (int day = 0; day < 500; day++) {
            simulator.simulateDay();
            cumulativeCases += simulator.getDailyCases();
            cumulativeDeaths += simulator.getDailyDeathCount();
            System.out.println(cumulativeDeaths);
//            System.out.println("Day " + day + " deaths = " + simulator.dailyDeathCount);
//            System.out.println("Day " + day + " cases =  " + simulator.dailyCases);
//            System.out.println("Day " + day + " total deaths = " + cumulativeDeaths);
//            System.out.println("Day " + day + " total cases =  " + cumulativeCases);
//            System.out.println("---------------------------------------------------");
        }
        simulator.waitForTermination();
        long end = System.currentTimeMillis();
        System.out.println("Milliseconds runtime  - " + (end - start));

    }
}
