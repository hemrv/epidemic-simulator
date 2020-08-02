import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Simulator {
    Country country;
    Disease disease;
    Set<Person> population;
    Set<StaticSite> households = new HashSet<>();
    Set<StaticSite> workplaces = new HashSet<>();
    Set<DynamicSite> dailyDynamicSites = new HashSet<>();
    Hospital hospital;
    int sizeOfPopulation;
    int day;


    // Outputs
    int dailyCases;
    int dailyDeathCount;


    public Simulator(Country country, Disease disease) {
        this.country = country;
        this.disease = disease;
        this.day  = 0;
        population = new HashSet<>();
        hospital = new Hospital(population,disease);
        populateSimulator(country);
    }

    void simulateDay() {
        simulateDynamicSites();
        simulateStaticSites();
        simulateHospitals();
        day++;
    }


    void simulateDynamicSites() {
        populateDynamicSites();
        for (DynamicSite site: dailyDynamicSites) {
            site.simulateSiteInfections(day);
        }
    }

    void simulateStaticSites() {
        for (StaticSite household: households) {
            household.simulateSiteInfections(day);
        }
        for (StaticSite workplace: workplaces) {
            workplace.simulateSiteInfections(day);
        }
    }

    void simulateHospitals() {
        dailyCases = hospital.testPopulation(day);
        Set<Person> dailyDeaths = hospital.simulatePatients(day);
        dailyDeathCount =  dailyDeaths.size();
        for(Person deceased : dailyDeaths){
            population.remove(deceased);
        }
    }

    void populateSimulator(Country country) {
        this.sizeOfPopulation = country.sizeOfPopulation;
        for (int i = 0; i < country.numInfectedAtStart; i++) {
            Person person = new Person(true,disease);
            population.add(person);
        }
        for (int i = 0; i < sizeOfPopulation - country.numInfectedAtStart; i++) {
            Person person = new Person(false,disease);
            population.add(person);
        }
        populateStaticSites();
        populateDynamicSites();
    }

    void populateDynamicSites(){
        Iterator<Person> iterator = population.iterator();
        for (int i = 0; i < country.numExtremelyLargeDynamicSites ; i++) {
            DynamicSite site = new DynamicSite(10000,10);
            while(!site.isFull() && iterator.hasNext()){
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);
        }

        for (int i = 0; i < country.numVeryLargeDynamicSites ; i++) {
            DynamicSite site = new DynamicSite(1000,10);
            while(!site.isFull() && iterator.hasNext()){
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);

        }
        for (int i = 0; i < country.numSmallDynamicsSites; i++) {
            DynamicSite site = new DynamicSite(10,1);
            while(!site.isFull() && iterator.hasNext()){
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);

        }
        for (int i = 0; i < country.numLargeDynamicSites; i++) {
            DynamicSite site = new DynamicSite(1000,10);
            while(!site.isFull() && iterator.hasNext()){
                site.add(iterator.next());
            }
            dailyDynamicSites.add(site);
        }
    }

    void populateStaticSites(){
        Iterator<Person> iterator = population.iterator();
        while(iterator.hasNext()){
            StaticSite site = new StaticSite(4,4);
            while (!site.isFull() && iterator.hasNext()) {
                site.add(iterator.next());
            }
            households.add(site);
        }
        Iterator<Person> iterator2 = population.iterator();
        while(iterator2.hasNext()){
            StaticSite site = new StaticSite(20,10);
            while (!site.isFull() && iterator2.hasNext()) {
                site.add(iterator2.next());
            }
            workplaces.add(site);
        }
    }

}


