public class Person implements Runnable {
    final private Disease disease;

    private int hospitalizedDate;
    private int infectedDate;
    private int recoveryDate;

    private boolean infected;
    private boolean contagious;
    private boolean testedPositive;
    private boolean quarantined;
    private boolean immune;



    public Person(boolean infected,Disease disease) {
        this.disease = disease;
        this.hospitalizedDate = -1;
        infectedDate = infected ? 0 : -1;
        this.recoveryDate = -1;
        this.infected = infected;
        this.contagious = false;
        this.testedPositive = false;
        this.immune = false;
    }


    public void infect(int day, double rand) {
        if(contagious || infected || immune || quarantined) {
            return;
        }
        if (rand < disease.dailyInfectionRate) {
            infectedDate = day;
            infected = true;
            recoveryDate = infectedDate + disease.incubationPeriod + disease.recoveryPeriod;
            if (rand < disease.hospitalizationRate) {
                hospitalizedDate = day + disease.incubationPeriod;
            }
        }
    }

    public boolean isContagious(int day) {
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


    public int getHospitalizedDate() {
        return hospitalizedDate;
    }

    public int getRecoveryDate() {
        return recoveryDate;
    }

    public boolean hasTestedPositive() {
        return testedPositive;
    }
    public void setTestedPositive() {
        testedPositive = true;
    }

    public void setQuarantined(boolean quarantined) {
        this.quarantined = quarantined;
    }

    public boolean isQuarantined() {
        return quarantined;
    }

    public boolean isInfected() {
        return infected;
    }

    public boolean isContagious() {
        return contagious;
    }

    @Override
    public void run() {

    }
}

