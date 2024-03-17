import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class FeatureB_Test {
    @Test
    public void calculateIndividualCapacity_EmptyList_ReturnsEmptyMap() {
        TeamMember member = new TeamMember();
        List<TeamMember> members = Arrays.asList();
        int sprintLength = 10;
        Assert.assertEquals(null, member.calculateIndividualCapacity(members, sprintLength));
    }

    @Test
    public void calculateIndividualCapacity_ValidInput_CalculatesCorrectly() {
        TeamMember member = new TeamMember();
        List<TeamMember> members = new ArrayList<>();
        members.add(new TeamMember(1, 6.0, 8.0, 1.0, 2.0));
        members.add(new TeamMember(2, 7.0, 9.0, 1.5, 3.0));
        int sprintLength = 10;
        Map<String, double[]> capacities = member.calculateIndividualCapacity(members, sprintLength);

        assertNotNull(capacities);
        assertTrue(capacities.containsKey("minimum"));
        assertTrue(capacities.containsKey("maximum"));

        double[] minCapacities = capacities.get("minimum");
        double[] maxCapacities = capacities.get("maximum");
        assertEquals(2, minCapacities.length);
        assertEquals(2, maxCapacities.length);

        double expectedMinCapacity = ((sprintLength - 2.0) * (6.0 - 1.0));
        double expectedMaxCapacity = ((sprintLength - 2.0) * (8.0 - 1.0));
        Assert.assertEquals(expectedMinCapacity, minCapacities[0], 0.00001);
        Assert.assertEquals(expectedMaxCapacity, maxCapacities[0], 0.00001);
    }

    @Test
    public void calculateTeamCapacity_ValidInput_CalculatesCorrectly() {
        TeamMember member = new TeamMember();
        double[] minCapacities = { 20.0, 25.0, 30.0 };
        double[] maxCapacities = { 30.0, 35.0, 40.0 };

        Map<String, double[]> capacities = new HashMap<>();
        capacities.put("minimum", minCapacities);
        capacities.put("maximum", maxCapacities);
        double expectedMin = 20.0 + 25.0 + 30.0;
        double expectedMax = 30.0 + 35.0 + 40.0;
        assertEquals(expectedMin + " - " + expectedMax,
                member.calculateTeamCapacity(capacities));
    }
}
