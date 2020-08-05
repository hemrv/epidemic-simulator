import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DynamicSite extends Site {
    public DynamicSite(int size, int locusOfInfection) {
        this.size = size;
        this.locusOfInfection = locusOfInfection;
    }

    public DynamicSite(Set<Person> population,int locusOfInfection) {
        super();
        this.people = new ArrayList<>(population);
        this.size = population.size();
        this.locusOfInfection = locusOfInfection;

    }

    public void simulateSiteInfections(int day) {
        int counter = countContagious(day) * locusOfInfection;
        while (counter > 0) {
            people.get(rg.nextInt(people.size())).infect(day, rg.nextDouble());
            counter--;
        }
        people.clear();
    }

}

