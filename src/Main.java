
public class Main {
    public static void main(String[] args) {
        Country fakeLand = new Country(100000,1,1,
                1,1,1);
        Disease plague  = new Disease(0.1,7,0.3,0.1,14);
        Simulator simulator = new Simulator(fakeLand,plague);
        int cumulativeDeaths = 0;
        int cumulativeCases  = 0;
        for (int day = 0; day < 1000; day++) {
            simulator.simulateDay();
            cumulativeCases += simulator.dailyCases;
            cumulativeDeaths += simulator.dailyDeathCount;
            System.out.println(simulator.dailyCases);
//            System.out.println("Day " + day + " deaths = " + simulator.dailyDeathCount);
//            System.out.println("Day " + day + " cases =  " + simulator.dailyCases);
//            System.out.println("Day " + day + " total deaths = " + cumulativeDeaths);
//            System.out.println("Day " + day + " total cases =  " + cumulativeCases);
//            System.out.println("---------------------------------------------------");
        }

    }
}
