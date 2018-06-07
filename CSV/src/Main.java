import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {

		String csvFile = "rsc/test.csv";

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				int len = -1;

				for (String field : line) {
					if (!field.isEmpty()) {
						if (!field.contains("|")) {
							System.out.println(field);
						} else {
							List<String> splits = new ArrayList<String>(Arrays.asList(field.split("\\|")));
							if (len != -1 && len != splits.size()) {
								System.err.println("Errore. Dati non consistenti!!");
								return;
							}
							if (len == -1) {
								len = splits.size();
								System.out.format("-- ARRAYS len %d ---\n", len);
							}
							if (splits.size() > 0 && splits.get(0).contains(",")) {
								List<List<String>> coordinates = new ArrayList<>();
								for (String split : splits) {
									coordinates.add(Arrays.asList(split.split(",")));
								}
								System.out.println(coordinates);
							} else {
								System.out.println(splits);
							}
						}

					}
				}
				System.out.println(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
