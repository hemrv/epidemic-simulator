import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Hospital {
    Set<Person> population;
    Set<Person> patients;
    Random rg = new Random();
    Disease disease;

    public Hospital(Set<Person> population, Disease disease) {
        this.population = population;
        patients = new HashSet<>();
        this.disease = disease;
    }


    public int testPopulation(int day) {
        int dailyCases = 0;
        for (Person person : population) {
            if (person.infected && person.contagious && !person.testedPositive) {
                dailyCases++;
                person.testedPositive = true;
                simulateQuarantine(person);
            }
            if (person.hospitalizedDate != -1 && person.hospitalizedDate == day) {
                patients.add(person);
                //person.quarantined = true;
            }

        }
        return dailyCases;
    }

    public void simulateQuarantine(Person person){
        if(rg.nextDouble() < 0.5){
            person.quarantined = true;
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
            } else if(patient.recoveryDate == day) {
                patient.recovered();
                iterator.remove();

            }
        }
        return dailyDeaths;
    }
}
