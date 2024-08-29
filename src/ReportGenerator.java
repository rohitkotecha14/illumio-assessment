import java.util.Map;

public class ReportGenerator {
    public void printTagCounts(Map<String, Integer> tagCounts) {
        System.out.println("Tag Counts:");
        System.out.println("Tag,Count");
        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }

    public void printPortProtocolCounts(Map<String, Integer> portProtocolCounts) {
        System.out.println("\nPort/Protocol Combination Counts:");
        System.out.println("Port,Protocol,Count");
        for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }
}
