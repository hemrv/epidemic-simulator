import java.util.ArrayList;
import java.util.HashSet;

public class StaticSite extends Site {
    public StaticSite(int size, int locusOfInfection){
        this.size = size;
        people = new ArrayList<>();
        this.locusOfInfection = locusOfInfection;
    }

    void simulateSiteInfections(int day){
        int counter = countContagious(day) * locusOfInfection;
        while (counter > 0){
            people.get(rg.nextInt(people.size())).infect(day,rg.nextDouble());
            counter--;
        }
    }

}
