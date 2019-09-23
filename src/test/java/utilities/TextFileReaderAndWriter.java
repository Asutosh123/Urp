package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileReaderAndWriter {

	 
	//write the data to text file
	public   void writeDataToTextFile(String applicationIDValue ) throws IOException{
		String appTextFile=System.getProperty("user.dir")+"\\\\src\\test\\resources\\TestData\\TextFiles\\CaseId.txt";
		File file = new File(appTextFile);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(applicationIDValue);
		bw.close();

	}
	//write the data to text file
		public   String readApplicationIdFromTextFile() throws IOException{
			String appTextFile=System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TextFiles\\CaseId.txt";
			String sCurrentLine=null;
			File file = new File(appTextFile);
			BufferedReader br = new BufferedReader(new FileReader(file));
		 
				sCurrentLine = br.readLine() ;
				System.out.println(sCurrentLine);
				br.close();
			return sCurrentLine;
			
		}
 
}
