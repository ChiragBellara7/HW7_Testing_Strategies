import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FeatureA_Test {
    // Unhappy Path
    @Test
    public void calculateAverageVelocity_EmptyList_ReturnsZero() {
        List<String> sprintPoints = Arrays.asList();
        double result = Main.calculateAverageVelocity(sprintPoints);
        Assert.assertEquals(0.0, result, 0.0001);
    }

    // Unhappy Path
    @Test
    public void calculateAverageVelocity_NullList_ReturnsZero() {
        List<String> sprintPoints = null;
        double result = Main.calculateAverageVelocity(sprintPoints);
        Assert.assertEquals(0.0, result, 0.0001);
    }

    // Happy Path
    @Test
    public void calculateAverageVelocity_ValidInput_CalculatesCorrectly() {
        List<String> sprintPoints = Arrays.asList("20", "32", "28");
        double result = Main.calculateAverageVelocity(sprintPoints);
        Assert.assertEquals(26.66667, result, 0.0001);
    }
}
