package CubeSolver;

import java.util.*;

public class Chromosome {

    int[] genes;

    public Chromosome(int size) {
        Random random = new Random();
        this.genes = new int[size];

        for (int i = 0; i < size; i ++) {
            this.genes[i] = random.nextInt(13) - 1;
        }

    }

    private Chromosome (Chromosome other) {
        this.genes = Arrays.copyOf(other.genes, other.genes.length);
    }

    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        String[] rotations = {"N", "U", "!U", "D", "!D", "L", "!L", "R", "!R", "F", "!F", "B", "!B"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int action : this.genes) {
            if (action != -1) stringBuilder.append(rotations[action + 1]).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    List<Chromosome> crossbreed(Chromosome other) {
        Random random = new Random();
        List<Chromosome> result = new ArrayList<Chromosome>();

        int pointer = random.nextInt(this.genes.length);

        Chromosome newChromosome1 = new Chromosome(this);
        Chromosome newChromosome2 = new Chromosome(other);

        for (int i = 0; i < pointer; i ++) {
            newChromosome1.genes[i] = other.genes[i];
            newChromosome2.genes[i] = this.genes[i];
        }

        result.add(newChromosome1);
        result.add(newChromosome2);

        return result;
    }

}