package Cube;

import java.util.*;

public class Chromosome {

    int[] genes;

    public Chromosome(int size) {
        Random random = new Random();
        this.genes = new int[size];

        for (int i = 0; i < size; i ++) {
            this.genes[i] = random.nextInt(13) - 1;
        }

        //This variant of loop I used to effectively generate chromosomes (aka filters)
        //But I haven't noticed any improvements in working
        /*for (int i = 0; i < size; i ++) {
            List<Integer> deprecatedMoves = this.deprecatedMoves(i);
            int next;
            do {
                next = random.nextInt(13) - 1;
            } while (deprecatedMoves.contains(next));
            this.genes[i] = next;
        }*/

    }

    @Override
    public String toString() {
        String[] rotations = {"N", "U", "!U", "D", "!D", "L", "!L", "R", "!R", "F", "!F", "B", "!B"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int action : this.genes) {
            stringBuilder.append(rotations[action + 1]).append(", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    Chromosome (Chromosome other) {
        this.genes = Arrays.copyOf(other.genes, other.genes.length);
    }

    public List<Chromosome> crossbreed(Chromosome other) {
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


    //Well, this method is used to generate a list of moves which are not passed filters.
    List<Integer> deprecatedMoves(int index) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> filter = new ArrayDeque<>();
        for (int shift = index - 3; shift != index; shift ++) {
            if (shift >= 0) {
                if (filter.size() == 0 || filter.peekLast() / 4 == this.genes[index] / 4) {
                    filter.addLast(this.genes[index]);
                } else {
                    filter = new ArrayDeque<>();
                }
            }
        }

        if (filter.size() > 0) {
            switch (filter.peekLast() / 4) {
                case 0:
                    if (Collections.frequency(filter, index) == 2) {
                        result.add(index);
                    }
                    result.add(index + 1);
                    break;
                case 1:
                    result.add(index - 1);
                    result.add(index);
                    break;
                case 2:
                    result.add(index - 2);
                    result.add(index - 1);
                    if (Collections.frequency(filter, index) == 2) {
                        result.add(index);
                    }
                    result.add(index + 1);
                    break;
                case 3:
                    result.add(index - 3);
                    result.add(index - 2);
                    result.add(index - 1);
                    result.add(index);
                    break;
            }
        }

        return result;
    }

}
