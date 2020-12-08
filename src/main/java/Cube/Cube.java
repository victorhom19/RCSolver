package Cube;

import java.util.Arrays;

public class Cube {
    
    Chromosome chromosome;
    int[] state;
    int fitness;

    Cube(Chromosome chromosome, int[] startPosition) {
        this.chromosome = chromosome;
        this.state = Arrays.copyOf(startPosition, startPosition.length);
        for (int action : chromosome.genes) {
            this.roll(action);
        }
        this.fitness = calculateFitness();
    }

    public int calculateFitness() {
        int fitness = 0;
        for (int i = 0; i < state.length; i ++) {
            if (state[i] == i / 9) {
                fitness ++;
            }
        }
        return fitness;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 54; i ++) {
            stringBuilder.append(state[i]).append(" ");
            if ((i + 1) % 3 == 0) stringBuilder.append("\n");
            if ((i + 1) % 9 == 0) stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private void roll(int rollId) {
        int t = -1;
        switch(rollId){
            case -1:
                break;
            case 0:
                /*
                 * =======    U    ========
                 */
                t = this.state[53];
                this.state[53] = this.state[33];
                this.state[33] = this.state[36];
                this.state[36] = this.state[20];
                this.state[20] = t;

                t = this.state[50];
                this.state[50] = this.state[34];
                this.state[34] = this.state[39];
                this.state[39] = this.state[19];
                this.state[19] = t;

                t = this.state[47];
                this.state[47] = this.state[35];
                this.state[35] = this.state[42];
                this.state[42] = this.state[18];
                this.state[18] = t;

                t = this.state[9];
                this.state[9] = this.state[15];
                this.state[15] = this.state[17];
                this.state[17] = this.state[11];
                this.state[11] = t;

                t = this.state[10];
                this.state[10] = this.state[12];
                this.state[12] = this.state[16];
                this.state[16] = this.state[14];
                this.state[14] = t;
                break;
            case 1:
                /*
                 * =======    U'   ========
                 */
                t = this.state[20];
                this.state[20] = this.state[36];
                this.state[36] = this.state[33];
                this.state[33] = this.state[53];
                this.state[53] = t;

                t = this.state[19];
                this.state[19] = this.state[39];
                this.state[39] = this.state[34];
                this.state[34] = this.state[50];
                this.state[50] = t;

                t = this.state[18];
                this.state[18] = this.state[42];
                this.state[42] = this.state[35];
                this.state[35] = this.state[47];
                this.state[47] = t;


                //Corner
                t = this.state[11];
                this.state[11] = this.state[17];
                this.state[17] = this.state[15];
                this.state[15] = this.state[9];
                this.state[9] = t;

                t = this.state[14];
                this.state[14] = this.state[16];
                this.state[16] = this.state[12];
                this.state[12] = this.state[10];
                this.state[10] = t;
                break;
            case 2:
                /*
                 * =======    D    ========
                 */
                t = this.state[0];
                this.state[0] = this.state[2];
                this.state[2] = this.state[8];
                this.state[8] = this.state[6];
                this.state[6] = t;

                t = this.state[3];
                this.state[3] = this.state[1];
                this.state[1] = this.state[5];
                this.state[5] = this.state[7];
                this.state[7] = t;

                t = this.state[45];
                this.state[45] = this.state[29];
                this.state[29] = this.state[44];
                this.state[44] = this.state[24];
                this.state[24] = t;

                t = this.state[48];
                this.state[48] = this.state[28];
                this.state[28] = this.state[41];
                this.state[41] = this.state[25];
                this.state[25] = t;

                t = this.state[51];
                this.state[51] = this.state[27];
                this.state[27] = this.state[38];
                this.state[38] = this.state[26];
                this.state[26] = t;
                break;
            case 3:
                /*
                 * =======    D'   ========
                 */

                //Corner
                t = this.state[6];
                this.state[6] = this.state[8];
                this.state[8] = this.state[2];
                this.state[2] = this.state[0];
                this.state[0] = t;

                t = this.state[7];
                this.state[7] = this.state[5];
                this.state[5] = this.state[1];
                this.state[1] = this.state[3];
                this.state[3] = t;

                t = this.state[24];
                this.state[24] = this.state[44];
                this.state[44] = this.state[29];
                this.state[29] = this.state[45];
                this.state[45] = t;

                t = this.state[25];
                this.state[25] = this.state[41];
                this.state[41] = this.state[28];
                this.state[28] = this.state[48];
                this.state[48] = t;

                t = this.state[26];
                this.state[26] = this.state[38];
                this.state[38] = this.state[27];
                this.state[27] = this.state[51];
                this.state[51] = t;
                break;
            case 4:
                /*
                 * =======    L    ========
                 */
                t = this.state[51];
                this.state[51] = this.state[53];
                this.state[53] = this.state[47];
                this.state[47] = this.state[45];
                this.state[45] = t;

                t = this.state[52];
                this.state[52] = this.state[50];
                this.state[50] = this.state[46];
                this.state[46] = this.state[48];
                this.state[48] = t;

                t = this.state[9];
                this.state[9] = this.state[35];
                this.state[35] = this.state[8];
                this.state[8] = this.state[26];
                this.state[26] = t;

                t = this.state[12];
                this.state[12] = this.state[32];
                this.state[32] = this.state[5];
                this.state[5] = this.state[23];
                this.state[23] = t;

                t = this.state[15];
                this.state[15] = this.state[29];
                this.state[29] = this.state[2];
                this.state[2] = this.state[20];
                this.state[20] = t;
                break;
            case 5:
                /*
                 * =======    L'   ========
                 */
                t = this.state[45];
                this.state[45] = this.state[47];
                this.state[47] = this.state[53];
                this.state[53] = this.state[51];
                this.state[51] = t;

                t = this.state[48];
                this.state[48] = this.state[46];
                this.state[46] = this.state[50];
                this.state[50] = this.state[52];
                this.state[52] = t;

                t = this.state[26];
                this.state[26] = this.state[8];
                this.state[8] = this.state[35];
                this.state[35] = this.state[9];
                this.state[9] = t;

                t = this.state[23];
                this.state[23] = this.state[5];
                this.state[5] = this.state[32];
                this.state[32] = this.state[12];
                this.state[12] = t;

                t = this.state[20];
                this.state[20] = this.state[2];
                this.state[2] = this.state[29];
                this.state[29] = this.state[15];
                this.state[15] = t;
                break;
            case 6:
                /*
                 * =======    R    ========
                 */
                t = this.state[36];
                this.state[36] = this.state[42];
                this.state[42] = this.state[44];
                this.state[44] = this.state[38];
                this.state[38] = t;

                t = this.state[39];
                this.state[39] = this.state[43];
                this.state[43] = this.state[41];
                this.state[41] = this.state[37];
                this.state[37] = t;

                t = this.state[6];
                this.state[6] = this.state[24];
                this.state[24] = this.state[11];
                this.state[11] = this.state[33];
                this.state[33] = t;

                t = this.state[3];
                this.state[3] = this.state[21];
                this.state[21] = this.state[14];
                this.state[14] = this.state[30];
                this.state[30] = t;

                t = this.state[0];
                this.state[0] = this.state[18];
                this.state[18] = this.state[17];
                this.state[17] = this.state[27];
                this.state[27] = t;
                break;
            case 7:
                /*
                 * =======    R'   ========
                 */
                t = this.state[38];
                this.state[38] = this.state[44];
                this.state[44] = this.state[42];
                this.state[42] = this.state[36];
                this.state[36] = t;

                t = this.state[37];
                this.state[37] = this.state[41];
                this.state[41] = this.state[43];
                this.state[43] = this.state[39];
                this.state[39] = t;

                t = this.state[33];
                this.state[33] = this.state[11];
                this.state[11] = this.state[24];
                this.state[24] = this.state[6];
                this.state[6] = t;

                t = this.state[30];
                this.state[30] = this.state[14];
                this.state[14] = this.state[21];
                this.state[21] = this.state[3];
                this.state[3] = t;

                t = this.state[27];
                this.state[27] = this.state[17];
                this.state[17] = this.state[18];
                this.state[18] = this.state[0];
                this.state[0] = t;
                break;
            case 8:
                /*
                 * =======    B    ========
                 */
                t = this.state[18];
                this.state[18] = this.state[24];
                this.state[24] = this.state[26];
                this.state[26] = this.state[20];
                this.state[20] = t;

                t = this.state[19];
                this.state[19] = this.state[21];
                this.state[21] = this.state[25];
                this.state[25] = this.state[23];
                this.state[23] = t;

                t = this.state[0];
                this.state[0] = this.state[45];
                this.state[45] = this.state[9];
                this.state[9] = this.state[36];
                this.state[36] = t;

                t = this.state[1];
                this.state[1] = this.state[46];
                this.state[46] = this.state[10];
                this.state[10] = this.state[37];
                this.state[37] = t;

                t = this.state[2];
                this.state[2] = this.state[47];
                this.state[47] = this.state[11];
                this.state[11] = this.state[38];
                this.state[38] = t;
                break;
            case 9:
                /*
                 * =======    B'   ========
                 */
                t = this.state[20];
                this.state[20] = this.state[26];
                this.state[26] = this.state[24];
                this.state[24] = this.state[18];
                this.state[18] = t;

                t = this.state[23];
                this.state[23] = this.state[25];
                this.state[25] = this.state[21];
                this.state[21] = this.state[19];
                this.state[19] = t;

                t = this.state[36];
                this.state[36] = this.state[9];
                this.state[9] = this.state[45];
                this.state[45] = this.state[0];
                this.state[0] = t;

                t = this.state[37];
                this.state[37] = this.state[10];
                this.state[10] = this.state[46];
                this.state[46] = this.state[1];
                this.state[1] = t;

                t = this.state[38];
                this.state[38] = this.state[11];
                this.state[11] = this.state[47];
                this.state[47] = this.state[2];
                this.state[2] = t;
                break;
            case 10:
                /*
                 * =======    F    ========
                 */
                t = this.state[27];
                this.state[27] = this.state[33];
                this.state[33] = this.state[35];
                this.state[35] = this.state[29];
                this.state[29] = t;

                t = this.state[28];
                this.state[28] = this.state[30];
                this.state[30] = this.state[34];
                this.state[34] = this.state[32];
                this.state[32] = t;

                t = this.state[44];
                this.state[44] = this.state[17];
                this.state[17] = this.state[51];
                this.state[51] = this.state[6];
                this.state[6] = t;

                t = this.state[43];
                this.state[43] = this.state[16];
                this.state[16] = this.state[52];
                this.state[52] = this.state[7];
                this.state[7] = t;

                t = this.state[42];
                this.state[42] = this.state[15];
                this.state[15] = this.state[53];
                this.state[53] = this.state[8];
                this.state[8] = t;
                break;
            case 11:
                /*
                 * =======    F'   ========
                 */
                t = this.state[29];
                this.state[29] = this.state[35];
                this.state[35] = this.state[33];
                this.state[33] = this.state[27];
                this.state[27] = t;

                t = this.state[32];
                this.state[32] = this.state[34];
                this.state[34] = this.state[30];
                this.state[30] = this.state[28];
                this.state[28] = t;

                t = this.state[6];
                this.state[6] = this.state[51];
                this.state[51] = this.state[17];
                this.state[17] = this.state[44];
                this.state[44] = t;

                t = this.state[7];
                this.state[7] = this.state[52];
                this.state[52] = this.state[16];
                this.state[16] = this.state[43];
                this.state[43] = t;

                t = this.state[8];
                this.state[8] = this.state[53];
                this.state[53] = this.state[15];
                this.state[15] = this.state[42];
                this.state[42] = t;
                break;
        }
    }
}
