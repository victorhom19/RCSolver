package CubeSolver;

import java.util.*;

public class Solver {

    private int[] startState;
    private List<Cube> population;
    private int populationSize;
    private int killRate;
    private int chromosomeSize;
    private int randomSaveNumber;

    public Solver(Cube cubeToSolve, int populationSize, int chromosomeSize, int killRate, int randomSaveNumber) {
        this.startState = cubeToSolve.state;
        this.populationSize = populationSize;
        this.killRate = killRate;
        this.randomSaveNumber = randomSaveNumber;
        this.chromosomeSize = chromosomeSize;
        this.population = new ArrayList<Cube>();
        for (int i = 0; i < populationSize; i ++) {
            population.add(new Cube(new Chromosome(chromosomeSize), startState));
        }
    }

    public Cube solve() {
        for (int generation = 0; generation < 100; generation ++) {
            System.out.print("\rCurrent best: " + currentBestFitness + " | Iteration: " + generation);
            if (checkForSuccess()) {
                System.out.println("\rSuccess!");
                System.out.println("Solution found on generation " + generation);
                population.sort(Comparator.comparingInt((o) -> o.fitness));
                Collections.reverse(population);
                System.out.println(population.get(0).chromosome);
                return population.get(0);
            }
            crossover();
            kill();
            for (int i = 0; i < population.size() / 2; i++) {
                mutate(population.get(i));
            }
            population.sort(Comparator.comparingInt((o) -> o.fitness));
        }
        return null;
    }

    private void mutate(Cube cube) {
        Random random = new Random();
        cube.chromosome.genes[random.nextInt(chromosomeSize)] = random.nextInt(13) - 1;
        cube.calculateFitness();
    }

    private void crossover() {
        Random random = new Random();
        for (int i = 0; i < killRate / 2; i ++) {
            List<Cube> crossoverCubes = new ArrayList<Cube>();
            Cube next;
            do {
                next = population.get(random.nextInt(populationSize));
                if (!(crossoverCubes.contains(next))) {
                    crossoverCubes.add(next);
                }
            } while (crossoverCubes.size() < 4);

            crossoverCubes.sort(Comparator.comparingInt((o) -> o.fitness));

            List<Chromosome> newChromosomes = crossoverCubes.get(2).chromosome.crossbreed(crossoverCubes.get(3).chromosome);
            population.add(new Cube(newChromosomes.get(0), startState));
            population.add(new Cube(newChromosomes.get(1), startState));
        }
    }

    private void kill() {
        Random random = new Random();
        population.sort(Comparator.comparingInt((o) -> o.fitness));

        Set<Cube> randomSaveIndices = new HashSet<>();
        while (randomSaveIndices.size() != randomSaveNumber) {
            int index = random.nextInt(killRate);
            randomSaveIndices.add(population.get(index));
        }

        Set<Cube> randomKillIndices = new HashSet<>();
        while (randomKillIndices.size() != randomSaveNumber) {
            int index = random.nextInt(population.size() - killRate) + killRate;
            randomKillIndices.add(population.get(index));
        }
        population = population.subList(killRate, population.size());
        population.addAll(randomSaveIndices);
        population.removeAll(randomKillIndices);
    }

    private int currentBestFitness = 0;

    private boolean checkForSuccess() {
        population.sort(Comparator.comparingInt((o) -> o.fitness));
        int bestOfGeneration = population.get(population.size() - 1).fitness;
        if (currentBestFitness < bestOfGeneration) {
            currentBestFitness = bestOfGeneration;
        }
        return (population.get(population.size() - 1).fitness == 54);
    }
}