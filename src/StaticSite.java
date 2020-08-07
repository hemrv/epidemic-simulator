import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StaticSite extends Site implements Runnable {
    private int day;
    public StaticSite(int size , int locusOfInfection){
        this.size = size;
        people = new ArrayList<>();
        this.locusOfInfection = locusOfInfection;
        this.day = 0;
    }

    @Override
    public void run() {
        int counter = countContagious(day) * locusOfInfection;
        while (counter > 0){
            people.get(rg.nextInt(people.size())).infect(day,rg.nextDouble());
            counter--;
        }
        day++;
    }

    @Override
    void simulateSiteInfections(int day) {
    }
}
