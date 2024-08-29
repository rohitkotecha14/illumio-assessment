# Flow Log Parser

This project parses AWS flow log data and maps each row to a tag based on a lookup table. The results are then written to CSV files, which include tag counts and port/protocol combination counts.

## Prerequisites

- Java Development Kit (JDK) installed on your system
- Git installed on your system

## Steps to Run the Project

### 1. Clone the Repository

First, clone the project repository from GitHub:

```bash
git clone https://github.com/rohitkotecha14/illumio-assessment.git
```
Alternatively, you can download the project as a ZIP file and extract it.

### 2. Navigate to the Project Directory

```bash
cd illumio-assessment
```

### 3. Compile the Java Source Files

```bash
javac -d bin src/*.java
```

### 4. Run the Project

```bash
java -cp bin Main input/flow_logs.txt input/lookup_table.csv
```

### 5. Check the Output

```bash
cat output/tag_counts.csv
cat output/port_protocol_counts.csv
```

### 6. Directory Overview

- `bin/`: Contains compiled Java classes.
- `input/`: Contains the input files, `flow_logs.txt` and `lookup_table.csv`.
- `output/`: Contains the generated output files, `tag_counts.csv` and `port_protocol_counts.csv`.
- `src/`: Contains the Java source files.

### 7. Cleaning Up

```bash 
rm -rf bin
rm -rf output
```

## Assumptions

### Protocol Numbers
- The program currently recognizes the following protocol numbers:
    - `6` → `tcp`
    - `17` → `udp`
    - `1` → `icmp`
- Any other protocol numbers will be mapped to `unknown`.

### Flow Log Structure
- The flow logs are expected to have at least 14 fields. The parser will skip any lines that do not meet this requirement.
- The following fields are specifically used:
    - **Destination Port**: Extracted from the 7th field in the log line.
    - **Protocol Number**: Extracted from the 8th field in the log line.

### Tag Mapping
- The lookup table file (`lookup_table.csv`) should have three columns: `dstport`, `protocol`, and `tag`.
- If a destination port and protocol combination is not found in the lookup table, the row will be tagged as `Untagged`.


