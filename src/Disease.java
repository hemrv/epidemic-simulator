public class Disease {
    final double infectionRate;
    final int incubationPeriod;
    final double hospitalizationRate;
    final double deathRate;
    final int recoveryPeriod;


    final double dailyInfectionRate;
    final double dailyHospitalizationRate;
    final double dailyDeathRate;


    public Disease(double infectionRate, int incubationPeriod,
                   double hospitalizationRate, double deathRate, int recoveryPeriod) {
        this.infectionRate = infectionRate;
        this.incubationPeriod = incubationPeriod;
        this.hospitalizationRate = hospitalizationRate;
        this.deathRate = deathRate;
        this.recoveryPeriod = recoveryPeriod;
        dailyInfectionRate = 1 - Math.pow(1-infectionRate,1.0 /incubationPeriod);
        dailyDeathRate = 1 - Math.pow(1-hospitalizationRate,1.0 /recoveryPeriod);
        dailyHospitalizationRate = 1 - Math.pow(1-dailyDeathRate,1.0 /recoveryPeriod);
    }
}
