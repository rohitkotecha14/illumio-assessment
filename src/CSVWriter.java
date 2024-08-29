import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CSVWriter {

    // Method to write tag counts to a CSV file
    public void writeTagCounts(String filePath, Map<String, Integer> tagCounts) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Tag,Count\n");
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                writer.append(entry.getKey()).append(",").append(entry.getValue().toString()).append("\n");
            }
        }
    }

    // Method to write port/protocol counts to a CSV file
    public void writePortProtocolCounts(String filePath, Map<String, Integer> portProtocolCounts) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Port,Protocol,Count\n");
            for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
                String[] portProtocol = entry.getKey().split(",");
                writer.append(portProtocol[0]).append(",").append(portProtocol[1]).append(",").append(entry.getValue().toString()).append("\n");
            }
        }
    }
}
