package utilities;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Assert;

public class Verify_the_precense_of_a_column_in_csvfile {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\raja.reddy\\Downloads\\CostBrackets.csv"));
		String line = br.readLine();
		String[] b = line.split(",");
		System.out.println(b[13]);
		Assert.assertTrue(b[13].contains("Effective Date"));
		br.close();

	}
}
