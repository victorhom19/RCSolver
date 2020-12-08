package Cube;

import java.util.*;
import java.util.stream.Collectors;

public class Solver {

    // Well, this version of solver can solve some cases of cube, but I can't say that it is fair solve.
    // While I was testing program, I noticed that program tried to find backward combination.
    // But I think that the way of program solving cube wasn't just find backward combination.
    // I guess that this happened just because backward combination is only combination which simple enough to calculate.
    // So that's why the time of solving process and chance of success mostly depends on how strong starting cube
    // is shuffled. In some cases, this variant of program can solve cube which is less than
    // ~11 steps away from solved state.

    public static void main(String[] args) {

        // The way I'm testing solver is generating random chromosome and applying moves from this
        // chromosome to from a starting cube. Then I try to solve this cube by creating an instance of Solver above
        // the starting cube and using solve() method.

        Chromosome chromosome = new Chromosome(15);
        int[] solvedState = new int[54];
        for (int i = 0; i < solvedState.length; i ++) {
            solvedState[i] = i / 9;
        }
        System.out.println("Start cube: " + chromosome);
        Cube cubeToSolve = new Cube(chromosome, solvedState);

        Solver solver = new Solver(cubeToSolve.state, 8000, 5600, 18, 500);
        solver.solve();
    }

    private int[] startState;
    private List<Cube> population;
    private int populationSize;
    private int killRate;
    private int chromosomeSize;
    private int randomSaveNumber;

    public Solver(int[] startState, int populationSize, int killRate, int chromosomeSize, int randomSaveNumber) {
        this.startState = startState;
        this.populationSize = populationSize;
        this.killRate = killRate;
        this.randomSaveNumber = randomSaveNumber;
        this.chromosomeSize = chromosomeSize;
        this.population = new ArrayList<Cube>();
        for (int i = 0; i < populationSize; i ++) {
            population.add(new Cube(new Chromosome(chromosomeSize), startState));
        }
    }



    public void solve() {

        for (int generation = 0; generation < 1000; generation ++) {

            System.out.print("\rCurrent best: " + currentBestFitness + " | Iteration: " + generation);
            if (checkForSuccess()) {
                System.out.println("\rSolution found on generation " + generation);
                population.sort(Comparator.comparingInt((o) -> o.fitness));
                Collections.reverse(population);
                System.out.println(population.get(0).chromosome);
                break;
            }

            crossover();
            kill();
            for (int i = 0; i < population.size() / 2; i++) {
                mutate(population.get(i));
            }
            population.sort(Comparator.comparingInt((o) -> o.fitness));
        }

    }


    // In general, my mutation works randomly - random gene changes to random value
    private void mutate(Cube cube) {
        Random random = new Random();

        // Here I tried to apply filters, but it also didn't work

        /*int mutatingIndex = random.nextInt(chromosomeSize);
        List<Integer> deprecatedMoves = cube.chromosome.deprecatedMoves(mutatingIndex);
        int next;
        do {
            next = random.nextInt(13) - 1;
        } while (deprecatedMoves.contains(next));
        cube.chromosome.genes[mutatingIndex] = next;*/

        cube.chromosome.genes[random.nextInt(chromosomeSize)] = random.nextInt(13) - 1;
        cube.calculateFitness();
    }

    private void crossover() {
        // Here I used single-point crossover. I tried to recreate the variant from repo,
        // where you choose two best individuals from four, and use them in single-point crossover to
        // produce 2 more children. In previous version of project I also tried the variant of crossover
        // which you mentioned in article, but it wasn't successful too.

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
        // Dividing sorted population to two parts: before killRate marker and after killRate marker.
        // If needed, in the first part I randomly choose some individuals to save
        // and in the second part - some individuals to kill.

        Random random = new Random();
        population.sort(Comparator.comparingInt((o) -> o.fitness));

        List<Integer> randomSaveIndices = new ArrayList<>();
        while (randomSaveIndices.size() != randomSaveNumber) {
            int index = random.nextInt(killRate);
            if (!(randomSaveIndices.contains(index))) {
                randomSaveIndices.add(index);
            }
        }

        List<Integer> randomKillIndices = new ArrayList<>();
        while (randomKillIndices.size() != randomSaveNumber) {
            int index = random.nextInt(population.size() - killRate) + killRate;
            if (!(randomKillIndices.contains(index))) {
                randomKillIndices.add(index);
            }
        }


        population = population.stream()
                .filter((o) -> population.indexOf(o) >= killRate
                        && !randomKillIndices.contains(population.indexOf(o))
                        || randomSaveIndices.contains(population.indexOf(o)))
                .collect(Collectors.toList());
    }

    private int currentBestFitness = 0;

    private boolean checkForSuccess() {
        // Sorting population in increasing order and check if last
        // individual reached fitness of 54
        population.sort(Comparator.comparingInt((o) -> o.fitness));
        int bestOfGeneration = population.get(populationSize - 1).fitness;
        if (currentBestFitness < bestOfGeneration) {
            currentBestFitness = bestOfGeneration;
        }
        return (population.get(populationSize - 1).fitness == 54);
    }
}
