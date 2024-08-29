import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FlowLogParser {
    private final LookupTable lookupTable;
    private final Map<String, Integer> tagCounts = new HashMap<>();
    private final Map<String, Integer> portProtocolCounts = new HashMap<>();

    public FlowLogParser(LookupTable lookupTable) {
        this.lookupTable = lookupTable;
    }

    public void parse(String flowLogFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(flowLogFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Split by whitespace

                if (parts.length < 14) continue; // Invalid log line (should have at least 14 fields)

                String dstPort = parts[6]; // 7th field: Destination Port
                String protocol = mapProtocol(parts[7]); // 8th field: Protocol Number

                String tag = lookupTable.getTag(dstPort, protocol);

                updateCounts(tagCounts, tag);
                updateCounts(portProtocolCounts, dstPort + "," + protocol);
            }
        }
    }

    private String mapProtocol(String protocolNumber) {
        switch (protocolNumber) {
            case "6":
                return "tcp";
            case "17":
                return "udp";
            case "1":
                return "icmp";
            default:
                return "unknown";
        }
    }

    private void updateCounts(Map<String, Integer> counts, String key) {
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }

    public Map<String, Integer> getTagCounts() {
        return tagCounts;
    }

    public Map<String, Integer> getPortProtocolCounts() {
        return portProtocolCounts;
    }
}
