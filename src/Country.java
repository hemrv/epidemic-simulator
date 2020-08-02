public class Country {
    int sizeOfPopulation;
    int numExtremelyLargeDynamicSites;
    int numVeryLargeDynamicSites;
    int numLargeDynamicSites;
    int numSmallDynamicsSites;
    int numInfectedAtStart;

    public Country(int sizeOfPopulation, int numExtremelyLargeDynamicSites, int numVeryLargeDynamicSites,
                   int numLargeDynamicSites, int numSmallDynamicsSites, int numInfectedAtStart) {
        this.sizeOfPopulation = sizeOfPopulation;
        this.numExtremelyLargeDynamicSites = numExtremelyLargeDynamicSites;
        this.numVeryLargeDynamicSites = numVeryLargeDynamicSites;
        this.numLargeDynamicSites = numLargeDynamicSites;
        this.numSmallDynamicsSites = numSmallDynamicsSites;
        this.numInfectedAtStart = numInfectedAtStart;
    }
}

