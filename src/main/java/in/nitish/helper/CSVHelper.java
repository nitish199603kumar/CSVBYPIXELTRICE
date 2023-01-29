package in.nitish.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import in.nitish.entity.DeveloperTutorial;

public class CSVHelper {

	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	public static boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			System.out.println("True");
			return true;
		} else {
			System.out.println("False");
			return false;
		}
	}

	public static List<DeveloperTutorial> csvToTutorials(InputStream is) {
		
		//InputStream is used for reading file data 
		// InputStream is= file.getInputStream();
		System.out.println("(CSVHelper) File Name " + is);
		try
				
		(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.EXCEL
						.withSkipHeaderRecord()
						.withHeader("Id","Title","Description","Published")
						.withIgnoreHeaderCase()
						.withTrim());)
						
						
		
		{
			
			/*
			Reader reader = Files.newBufferedReader(Paths.get(is));
			 CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL
	            		.withSkipHeaderRecord()
	                    .withHeader("Id","Title","Description","Published")
	                    .withIgnoreHeaderCase()
	                    .withTrim());
	                    
	          */

			
			System.out.println("CSVHelper");
			List<DeveloperTutorial> developerTutorialList = new ArrayList<>();

//			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			System.out.println("CSVRECORD");
			for (CSVRecord csvRecord : csvParser) {
				System.out.println("CSVRecord");
				System.out.println(Integer.parseInt(csvRecord.get("Id")));
				System.out.println(csvRecord.get("Title"));
				System.out.println(csvRecord.get("Description"));
				System.out.println(csvRecord.get("Published"));

				DeveloperTutorial developerTutorial = new DeveloperTutorial(Integer.parseInt(csvRecord.get("Id")),
						csvRecord.get("Title"),
						csvRecord.get("Description"),
						Boolean.parseBoolean(csvRecord.get("Published")));

				developerTutorialList.add(developerTutorial);
			}

			System.out.println("Return From Helper");
			return developerTutorialList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}

	}

	public static ByteArrayInputStream tutorialsToCSV(List<DeveloperTutorial> developerTutorialList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (DeveloperTutorial developerTutorial : developerTutorialList) {
				List<String> data = Arrays.asList(String.valueOf(developerTutorial.getId()),
						developerTutorial.getTitle(), developerTutorial.getDescription(),
						String.valueOf(developerTutorial.isPublished()));

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
