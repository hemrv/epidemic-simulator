public class Person {
    Disease disease;
    int age;

    int hospitalizedDate;
    int infectedDate;
    int recoveryDate;

    boolean infected;
    boolean contagious;
    boolean testedPositive;
    boolean quarantined;
    boolean immune;
    boolean dead;


    public Person(boolean infected,Disease disease) {
        this.disease = disease;
        //this.age = age;
        this.hospitalizedDate = -1;
        infectedDate = infected ? 0 : -1;
        this.recoveryDate = -1;
        this.infected = infected;
        this.contagious = false;
        this.testedPositive = false;
        this.immune = false;
        this.dead = false;
    }


    public void infect(int day, double rand) {
        if(contagious || infected || immune || quarantined) {
            return;
        }
        if (rand < disease.dailyInfectionRate) {
            infectedDate = day;
            infected = true;
            //System.out.println("infected someone");
            if (rand < disease.hospitalizationRate) {
                hospitalizedDate = day + disease.incubationPeriod;
            }
        }
    }

    public boolean isContagious(int day) {

//        if(immune || quarantined){
//            return false;
//        }

        if(immune || quarantined){
            return false;
        }
        if(contagious){
            return true;
        }
        if (infected && infectedDate + disease.incubationPeriod == day) {
            contagious = true;
        }
        return contagious;
    }

    public void recovered(){
        quarantined = false;
        contagious = false;
        immune = true;
    }


}
// If over the 14 days they are contagious, to make it x % percent likley
// If you were exposed to that person for 14 days straight y% or (100 - y) * 14 = 100 - x
