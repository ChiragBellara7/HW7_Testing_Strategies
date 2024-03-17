
import java.util.*;

public class TeamMember {
    int memberId;
    double daysOff;
    double ceremonyHours;
    double minDailyHours;
    double maxDailyHours;

    public TeamMember() {
    }

    public TeamMember(int id, double minDailyHours, double maxDailyHours, double ceremonyHours, double daysOff) {
        this.memberId = id;
        this.minDailyHours = minDailyHours;
        this.maxDailyHours = maxDailyHours;
        this.ceremonyHours = ceremonyHours;
        this.daysOff = daysOff;
    }

    public Map<String, double[]> calculateIndividualCapacity(List<TeamMember> members, int sprintLength) {
        if (members.isEmpty()) {
            return null;
        }
        double[] minCapacities = new double[members.size()];
        double[] maxCapacities = new double[members.size()];
        for (int i = 0; i < members.size(); i++) {
            double availableDays = sprintLength - members.get(i).daysOff;
            double minAvailableHours = availableDays * (members.get(i).minDailyHours - members.get(i).ceremonyHours);
            double maxAvailableHours = availableDays * (members.get(i).maxDailyHours - members.get(i).ceremonyHours);
            minCapacities[i] = minAvailableHours;
            maxCapacities[i] = maxAvailableHours;
        }
        System.out.println("Sprint Length: " + sprintLength);
        System.out.println("Team Size: " + members.size());
        Map<String, double[]> capacities = new HashMap<>();
        capacities.put("minimum", minCapacities);
        capacities.put("maximum", maxCapacities);
        printIndividualCapacities(members, capacities);
        return capacities;
    }

    public String calculateTeamCapacity(Map<String, double[]> individualCapacities) {
        if (individualCapacities.isEmpty()) {
            return "";
        }
        double minTeamCapacity = 0;
        double maxTeamCapacity = 0;

        double[] minCapacities = individualCapacities.get("minimum");
        for (double capacity : minCapacities) {
            minTeamCapacity += capacity;
        }

        double[] maxCapacities = individualCapacities.get("maximum");
        for (double capacity : maxCapacities) {
            maxTeamCapacity += capacity;
        }

        String teamCapacityRange = minTeamCapacity + " - " + maxTeamCapacity;
        return teamCapacityRange;
    }

    public static void printIndividualCapacities(List<TeamMember> members, Map<String, double[]> capacityMap) {
        if (members == null || members.isEmpty()) {
            return;
        }
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-30s |\n", "Member ID", "Daily Hours", "Ceremony Hours",
                "Days Off", "Available Effort-Hours range");
        System.out.println(
                "|----------------------|----------------------|----------------------|----------------------|--------------------------------|");
        int i = 0;
        double[] minCapacities = capacityMap.get("minimum");
        double[] maxCapacities = capacityMap.get("maximum");

        for (TeamMember entry : members) {
            String effortHoursRange = minCapacities[i] + " - " + maxCapacities[i];
            String dailyHourRange = entry.minDailyHours + " - " + entry.maxDailyHours;
            System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-30s |\n", entry.memberId, dailyHourRange,
                    entry.ceremonyHours, entry.daysOff, effortHoursRange);
            i += 1;
        }
    }
}