import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <flowLogFile> <lookupTableFile>");
            System.exit(1);
        }

        String flowLogFile = args[0];
        String lookupFile = args[1];
        String outputDirectory = "output";  // Output directory for CSV files

        // Ensure the output directory exists
        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String tagCountsFile = outputDirectory + "/tag_counts.csv";  // Output file for tag counts
        String portProtocolCountsFile = outputDirectory + "/port_protocol_counts.csv";  // Output file for port/protocol counts

        LookupTable lookupTable = new LookupTable();
        FlowLogParser parser = new FlowLogParser(lookupTable);
        ReportGenerator reportGenerator = new ReportGenerator();
        CSVWriter csvWriter = new CSVWriter();

        try {
            // Load the lookup table
            lookupTable.loadFromFile(lookupFile);

            // Parse the flow logs
            parser.parse(flowLogFile);

            // Generate and print the reports
            reportGenerator.printTagCounts(parser.getTagCounts());
            reportGenerator.printPortProtocolCounts(parser.getPortProtocolCounts());

            // Write the results to CSV files in the output directory
            csvWriter.writeTagCounts(tagCountsFile, parser.getTagCounts());
            csvWriter.writePortProtocolCounts(portProtocolCountsFile, parser.getPortProtocolCounts());

            System.out.println("Results written to CSV files in " + outputDirectory + " successfully.");

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
