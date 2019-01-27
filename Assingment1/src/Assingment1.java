import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * 
 */

/**
 * @author Subhasibi
 *
 */
public class Assingment1 {
	static int rows_containData = 0;
	static int rows_Skipped = 0;
	static int inte = 0;
	static String line = null;
	static FileWriter fileWriter = null;
	static BufferedWriter writer = null;
	static Handler consoleHandler = null;
	static Handler fileHandler = null;
	static Formatter simpleFormatter = null;
	static String Date = null;

	Logger logger = Logger.getLogger("Main");

	// private static final String HEADER = "First Name,Last Name,Street
	// Number,Street,City,Province,Postal Code,Country,Phone Number,email Address";

	public void walk(String path) {

		File root = new File(path);
		File[] list = root.listFiles();
		Handler fileHandler = null;
		String Dt = null;

		if (list == null)
			return;

		for (File f : list) {
			if (f.isDirectory()) {
				walk(f.getAbsolutePath());

			} else {
				// System.out.println("File: "+f.getAbsoluteFile());
				File file = f.getAbsoluteFile();
			
				Reader in;
				try {
					String fileName = file.getName();
					if ((fileName.substring(fileName.lastIndexOf(".") + 1).equals("csv"))) {
						in = new FileReader(file);					
						Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);					

						for (CSVRecord record : records) {
							
							Dt = file.toPath().toString().substring(57, 66);							
							
							if ( record.size() < 10 ||record.get(0).toString().length() == 0 ||record.get(1).isEmpty()
									|| record.get(2).isEmpty() || record.get(3).isEmpty() || record.get(4).isEmpty()
									|| record.get(5).isEmpty() || record.get(6).isEmpty() || record.get(7).isEmpty()
									|| record.get(8).isEmpty() || record.get(9).isEmpty()) {
								logger.log(Level.INFO, file.toString());
							//	logger.log(Level.INFO,fileName, record.getRecordNumber() );
							//	System.out.println(record.getRecordNumber());
								rows_Skipped++;

							} else {

								writer.append(record.get(0));
								writer.append(",");
								writer.append(record.get(1));
								writer.append(",");
								writer.append(record.get(2));
								writer.append(",");
								writer.append(record.get(3));
								writer.append(",");
								writer.append(record.get(4));
								writer.append(",");
								writer.append(record.get(5));
								writer.append(",");
								writer.append(record.get(6));
								writer.append(",");
								writer.append(record.get(7));
								writer.append(",");
								writer.append(record.get(8));
								writer.append(",");
								writer.append(Dt.toString());
								writer.append("\n");

								writer.flush();

								rows_containData++;
							}
						}
					}

				}

				catch (IOException e) {
					logger.config(e.toString());
					e.printStackTrace();

				}
			}
		}

	}

	public static void main(String[] args) {
		Assingment1 fw = new Assingment1();
		String outputFilename = "./ValidRecords.csv";

		Logger logger = Logger.getLogger("Main");
		consoleHandler = new ConsoleHandler();

		try {
			fileWriter = new FileWriter(outputFilename);
			writer = new BufferedWriter(fileWriter);
			fileHandler = new FileHandler("./sampleLogfile.log");
			simpleFormatter = new SimpleFormatter();

			// Setting formatter to the handler
			fileHandler.setFormatter(simpleFormatter);
			// fileWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.config(e.toString());
			e.printStackTrace();
		}
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		consoleHandler.setLevel(Level.ALL);
		final long startTime = System.currentTimeMillis();
		fw.walk("C:\\GITRepo\\A00431796_Assingmnet1\\Sample Data\\Sample Data");

		final long endTime = System.currentTimeMillis();

		logger.log(Level.INFO, "Total no.of Skipped Rows" + rows_Skipped);
		logger.log(Level.INFO, "Total no.of Rows " + rows_containData);
		logger.log(Level.INFO, "Total execution time: " + (endTime - startTime) + " ms");

		System.out.println("Total execution time: " + (endTime - startTime) + " ms");
		System.out.println("Total no.of Skipped Rows" + rows_Skipped);
		System.out.println("Total no.of Rows " + rows_containData);
	}

}
