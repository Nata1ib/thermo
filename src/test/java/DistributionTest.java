import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class DistributionTest {

    private static final double deltaArea = 0.1;
    private static final double deltaIntegration = 0.2;

    @Test
    public void tests() {
        double[][] expectedArray1 = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0.33, 1.52, 4.21, 5.20, 4.22, 1.52, 0.33, 0.14, 0 },
                { 0, 1.52, 7.12, 19.44, 23.67, 19.40, 7.12, 1.52, 0.33, 0 },
                { 0, 4.23, 19.44, 52.31, 63.67, 52.31, 19.44, 4.21, 0.71, 0 },
                { 0, 5.27, 23.67, 63.68, 77.5, 63.67, 23.67, 5.27, 0.8, 0 },
                { 0, 4.23, 19.44, 52.31, 63.68, 52.31, 19.44, 4.21, 0.71, 0 },
                { 0, 1.52,  7.12, 19.44, 23.4, 19.44, 7.12, 1.58, 0.33, 0 },
                {0, 0.33, 1.52, 4.21, 5.20, 4.22, 1.52, 0.33, 0.14, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        double tolerance = 0.5; // Допустимая погрешность

        // 100 шагов, коэффицент теплопроводимости 0.5
        assertTrue(compareArrays(expectedArray1, TemperatureDistribution.distribution(100,0.5), tolerance));


        double[][] expectedArray2 = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0.11, 0.13, 0.11, 0, 0, 0, 0 },
                { 0, 0, 0.26, 4.44, 4.62, 4.44, 0.25, 0, 0, 0 },
                { 0, 0.11, 4.44, 90.91, 95.15, 90.91, 4.44, 0.11, 0, 0 },
                { 0, 0.11, 4.64, 95.15, 99.57, 95.15, 4.64, 0.11, 0, 0 },
                { 0, 0.11, 4.44, 90.91, 95.15, 90.91, 4.44, 0.11, 0, 0 },
                { 0, 0, 0.26, 4.44, 4.62, 4.44, 0.25, 0, 0, 0 },
                { 0, 0, 0, 0.11, 0.13, 0.11, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        // 50 шагов, коэффицент теплопроводимости 0.1
        assertTrue(compareArrays(expectedArray2, TemperatureDistribution.distribution(50,0.1), tolerance));
    }

    private boolean compareArrays(double[][] expected, double[][] actual, double tolerance) {

        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                if (Math.abs(expected[i][j] - actual[i][j]) > tolerance) {
                    return false;
                }
            }
        }

        return true;
    }
}
