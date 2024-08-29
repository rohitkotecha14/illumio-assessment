import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LookupTable {
    private final Map<String, String> table = new HashMap<>();

    public void loadFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String key = parts[0] + "," + parts[1];
                    table.put(key, parts[2]);
                }
            }
        }
    }

    public String getTag(String dstPort, String protocol) {
        return table.getOrDefault(dstPort + "," + protocol, "Untagged");
    }
}
