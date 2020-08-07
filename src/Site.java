import java.util.*;

public abstract class Site {
    protected List<Person> people = new ArrayList<>();
    protected int locusOfInfection;
    protected int size;
    final protected Random rg = new Random();

    abstract void simulateSiteInfections(int day);


    protected int countContagious(int day){
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
