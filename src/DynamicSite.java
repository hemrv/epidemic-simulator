public class DynamicSite extends Site {
    public DynamicSite(int size, int locusOfInfection) {
        this.size = size;
        this.locusOfInfection = locusOfInfection;
    }

    public void simulateSiteInfections(int day) {
        int counter = countContagious(day) * locusOfInfection;
        // Could possibly just multiply by the radius of the location to find the subset
        // But this means that if it is overlapping it is no longer accurate
        while (counter > 0) {
            people.get(rg.nextInt(people.size())).infect(day, rg.nextDouble());
            counter--;
        }
        people.clear();
    }

}

