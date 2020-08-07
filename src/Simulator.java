import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulator {
    final Country country;
    final Disease disease;
    final Set<Person> population;
    final Set<StaticSite> staticSites = new HashSet<>();

    Set<DynamicSite> dailyDynamicSites = new HashSet<>();
    //MainSite mainSite;
    Hospital hospital;
    int day;
    final double lockDownFactor;
    double locusReduction;

    ExecutorService threadPool;

    private int dailyCases;
    private int dailyDeathCount;


    public Simulator(Country country, Disease disease, double lockDownFactor) {
        this.country = country;
        this.disease = disease;
        this.day = 0;
        this.lockDownFactor = lockDownFactor;
        locusReduction = 1 - lockDownFactor;
        population = new HashSet<>();

        populateSimulator(country);
        threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        hospital = new Hospital(population, disease, lockDownFactor);
    }

    public void simulateDay() {
        //simulateMainSite();
        simulateStaticSites();
        simulateDynamicSites();
        simulateHospitals();
        day++;
    }

    public void waitForTermination() {
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("error - couldn't shutdown threadPool");

        }

    }

    private void simulateStaticSites() {
        for (StaticSite site : staticSites) {
            threadPool.execute(site);
        }
    }

    private void simulateDynamicSites() {
        populateDynamicSites();
        for (DynamicSite site : dailyDynamicSites) {
            site.simulateSiteInfections(day);
        }
    }

    private void simulateHospitals() {
        dailyCases = hospital.testPopulation(day);
        Set<Person> dailyDeaths = hospital.simulatePatients(day);
        dailyDeathCount = dailyDeaths.size();
        for (Person deceased : dailyDeaths) {
            population.remove(deceased);
        }
    }

    private void populateSimulator(Country country) {
        for (int i = 0; i < country.numInfectedAtStart; i++) {
            Person person = new Person(true, disease);
            population.add(person);
        }
        for (int i = 0; i < country.sizeOfPopulation - country.numInfectedAtStart; i++) {
            Person person = new Person(false, disease);
            population.add(person);
        }
        populateStaticSites();
        //populateMainSite();
    }


    private void populateDynamicSites() {
        Iterator<Person> iterator = population.iterator();
        for (int i = 0; i < country.numExtremelyLargeDynamicSites; i++) {
            DynamicSite site = new DynamicSite(10000, (int) (50 * locusReduction));
            while (!site.isFull() && iterator.hasNext()) {
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);
        }

        for (int i = 0; i < country.numVeryLargeDynamicSites; i++) {
            DynamicSite site = new DynamicSite(1000, (int) (25 * locusReduction));
            while (!site.isFull() && iterator.hasNext()) {
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);

        }
        for (int i = 0; i < country.numSmallDynamicsSites; i++) {
            DynamicSite site = new DynamicSite(10, (int) (3 * locusReduction));
            while (!site.isFull() && iterator.hasNext()) {
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);

        }
        for (int i = 0; i < country.numLargeDynamicSites; i++) {
            DynamicSite site = new DynamicSite(100, (int) (10 * locusReduction));
            while (!site.isFull() && iterator.hasNext()) {
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);
        }
    }

    private void populateStaticSites() {
        Iterator<Person> iterator = population.iterator();
        while (iterator.hasNext()) {
            StaticSite household = new StaticSite(4, 4);
            while (!household.isFull() && iterator.hasNext()) {
                household.add(iterator.next());
            }
            staticSites.add(household);
        }
        Iterator<Person> iterator2 = population.iterator();
        while (iterator2.hasNext()) {
            StaticSite workplace = new StaticSite(50, (int) (10 * locusReduction));
            while (!workplace.isFull() && iterator2.hasNext()) {
                workplace.add(iterator2.next());
            }
            staticSites.add(workplace);
        }
        Iterator<Person> iterator3 = population.iterator();
        while (iterator3.hasNext()) {
            StaticSite socialCircle = new StaticSite(5, 5);
            while (!socialCircle.isFull() && iterator3.hasNext()) {
                socialCircle.add(iterator3.next());
            }
            staticSites.add(socialCircle);
        }
    }

    public int getDailyCases() {
        return dailyCases;
    }

    public int getDailyDeathCount() {
        return dailyDeathCount;
    }


    /*
    Code that can used to simulate on large site containing the entire population

     private void simulateMainSite() {
        mainSite.simulateSiteInfections(day);
        }

     private void populateMainSite(){
        mainSite = new MainSite(population, (int) (50 * locusReduction));
        }

     */


}


