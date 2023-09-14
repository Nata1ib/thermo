import java.text.DecimalFormat;

public class TemperatureDistribution {
    private static final int GRID_SIZE = 10;
    private static final double DELTA_T = 0.01;
    private static final int NUM_THREADS = 4;

    public static double[][] distribution(int steps, double alpha) {
        double[][] temperature = new double[GRID_SIZE][GRID_SIZE];

        int centerX = GRID_SIZE / 2 - 1;
        int centerY = GRID_SIZE / 2 - 1;
        int radius = 1;
        for (int i = centerX - radius; i <= centerX + radius; i++) {
            for (int j = centerY - radius; j <= centerY + radius; j++) {
                temperature[i][j] = 100.0;
            }
        }

        Thread[] threads = new Thread[NUM_THREADS];
        int chunkSize = GRID_SIZE / NUM_THREADS; // Размер части сетки для каждого потока

        for (int t = 0; t < steps; t++) {
            for (int i = 0; i < NUM_THREADS; i++) {
                final int startRow = i * chunkSize;
                final int endRow = (i + 1) * chunkSize;

                threads[i] = new Thread(() -> {
                    for (int row = startRow; row < endRow; row++) {
                        for (int col = 0; col < GRID_SIZE; col++) {
                            if (!(row == 0  || row == GRID_SIZE - 1 || col == 0 || col == GRID_SIZE - 1)) {
                                double laplacian = (temperature[row + 1][col] + temperature[row - 1][col] +
                                        temperature[row][col + 1] + temperature[row][col - 1] - 4 * temperature[row][col]);
                                temperature[row][col] += alpha * laplacian * DELTA_T;
                            }
                        }
                    }
                });
            }

            for (Thread thread : threads) {
                thread.start();
            }

            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return temperature;
    }

    private static void printTemperature(double[][] temperature) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.printf("%4s ", decimalFormat.format(temperature[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printTemperature(distribution(50, 0.1));
    }
}