import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StaticSite extends Site {
    public StaticSite(Set<Person> population , int locusOfInfection){
        this.size = population.size();
        people = new ArrayList<>(population);
        this.locusOfInfection = locusOfInfection;
    }

    void simulateSiteInfections(int day){
        int counter = countContagious(day) * locusOfInfection;
        while (counter > 0){
            people.get(rg.nextInt(people.size())).infect(day,rg.nextDouble());
            counter--;
        };
    }

}
