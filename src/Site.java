import java.util.*;

public abstract class Site {
    List<Person> people = new ArrayList<>();
    int locusOfInfection;
    int size;
    Random rg = new Random();

    abstract void simulateSiteInfections(int day);

    public int countContagious(int day){
        int numContagious = 0;
        for(Person person: people){
            if(person.isContagious(day)){
                numContagious++;
            }
        }
        return numContagious;
    }

    public boolean isFull(){
        return size <= people.size();
    }

    public void add(Person person){
        people.add(person);
    }



}
