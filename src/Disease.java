public class Disease {
    double infectionRate;
    int incubationPeriod;
    double hospitalizationRate;
    double deathRate;
    int recoveryPeriod;


    double dailyInfectionRate;
    double dailyHospitalizationRate;
    double dailyDeathRate;


    public Disease(double infectionRate, int incubationPeriod,
                   double hospitalizationRate, double deathRate, int recoveryPeriod) {
        this.infectionRate = infectionRate;
        this.incubationPeriod = incubationPeriod;
        this.hospitalizationRate = hospitalizationRate;
        this.deathRate = deathRate;
        this.recoveryPeriod = recoveryPeriod;
        calculateDailyRates();
    }

    private void calculateDailyRates(){
        dailyInfectionRate = 1 - Math.pow(1-infectionRate,1.0 /incubationPeriod);
        dailyDeathRate = 1 - Math.pow(1-hospitalizationRate,1.0 /recoveryPeriod);
        dailyHospitalizationRate = 1 - Math.pow(1-dailyDeathRate,1.0 /recoveryPeriod);

    }
}
