import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class Hospital {
    final private Set<Person> population;
    final private Set<Person> patients;
    final private Random rg = new Random();
    final private Disease disease;
    private int day;
    final private double lockDownFactor;


    public Hospital(Set<Person> population, Disease disease, double lockDownFactor) {
        this.population = population;
        patients = new HashSet<>();
        this.disease = disease;
        this.lockDownFactor = lockDownFactor;
    }


     int testPopulation(int day) {
        int dailyCases = 0;
        this.day = day;
        for (Person person : population) {
            if (person.isInfected() && person.isContagious() && !person.hasTestedPositive()) {
                dailyCases++;
                person.setTestedPositive();
                simulateQuarantine(person);
            }
            if (person.getHospitalizedDate() != -1 && person.getHospitalizedDate() == day) {
                patients.add(person);
            }

        }
        return dailyCases;
    }

    private void simulateQuarantine(Person person){
        if(day < disease.incubationPeriod * 2){
            return;
        }
        if(rg.nextDouble() < lockDownFactor){
            person.setQuarantined(true);
        }
    }

    public Set<Person> simulatePatients(int day) {
        Set<Person> dailyDeaths = new HashSet<>();
        Iterator<Person> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Person patient = iterator.next();
            if (rg.nextDouble() < disease.dailyDeathRate) {
                iterator.remove();
                dailyDeaths.add(patient);
            } else if(patient.getRecoveryDate() == day) {
                patient.recovered();
                iterator.remove();

            }
        }
        return dailyDeaths;
    }
}
