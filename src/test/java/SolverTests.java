import CubeSolver.Cube;
import CubeSolver.Chromosome;
import CubeSolver.Solver;
import org.junit.jupiter.api.Test;

class SolverTests {

    int[] successCounters = new int[]{0,0,0,0};

    @Test
    void doTests() {
        for (int i = 0; i < 100; i ++) {
            test1();
        }

        for (int i = 0; i < 100; i ++) {
            test2();
        }

        for (int i = 0; i < 100; i ++) {
            test3();
        }

        for (int i = 0; i < 100; i ++) {
            test4();
        }

        System.out.println("-".repeat(50));
        for (int i = 0; i < 4; i ++) {
            System.out.println("Success rate with shuffle complexity " + (6 + 2*i) + " is " + successCounters[i] + "%.");
        }
    }


    @Test
    void test1() {
        Cube cubeToSolve = getShuffledCube(6);
        Solver solver = initializeSolver(cubeToSolve);
        Cube solution = solver.solve();
        if (solution == null) {
            System.out.println("\rFailure! Can't find solution.");
        } else {
            for (int action : solution.getChromosome().getGenes()) {
                cubeToSolve.roll(action);
            }
            assert(cubeToSolve.equals(getSolvedCube()));
            successCounters[0] ++;
        }
    }

    @Test
    void test2() {
        Cube cubeToSolve = getShuffledCube(8);
        Solver solver = initializeSolver(cubeToSolve);
        Cube solution = solver.solve();
        if (solution == null) {
            System.out.println("\rFailure! Can't find solution.");
        } else {
            for (int action : solution.getChromosome().getGenes()) {
                cubeToSolve.roll(action);
            }
            assert(cubeToSolve.equals(getSolvedCube()));
            successCounters[1] ++;
        }
    }

    @Test
    void test3() {
        Cube cubeToSolve = getShuffledCube(10);
        Solver solver = initializeSolver(cubeToSolve);
        Cube solution = solver.solve();
        if (solution == null) {
            System.out.println("\rFailure! Can't find solution.");
        } else {
            for (int action : solution.getChromosome().getGenes()) {
                cubeToSolve.roll(action);
            }
            assert(cubeToSolve.equals(getSolvedCube()));
            successCounters[2] ++;
        }
    }

    @Test
    void test4() {
        Cube cubeToSolve = getShuffledCube(12);
        Solver solver = initializeSolver(cubeToSolve);
        Cube solution = solver.solve();
        if (solution == null) {
            System.out.println("\rFailure! Can't find solution.");
        } else {
            for (int action : solution.getChromosome().getGenes()) {
                cubeToSolve.roll(action);
            }
            assert(cubeToSolve.equals(getSolvedCube()));
            successCounters[3] ++;
        }
    }

    Solver initializeSolver(Cube cubeToSolve) {
        return new Solver(cubeToSolve, 8000, 18, 5600, 100);
    }

    static Cube getShuffledCube(int complexity) {
        int[] solvedState = new int[54];
        for (int i = 0; i < solvedState.length; i ++) {
            solvedState[i] = i / 9;
        }
        Chromosome chromosome = new Chromosome(complexity);
        System.out.println("Start cube: " + chromosome);
        return new Cube(chromosome, solvedState);
    }

    static Cube getSolvedCube() {
        int[] solvedState = new int[54];
        for (int i = 0; i < solvedState.length; i ++) {
            solvedState[i] = i / 9;
        }
        return new Cube(new Chromosome(0), solvedState);
    }


}
