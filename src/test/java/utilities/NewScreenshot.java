package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

     public class NewScreenshot 
     {
  static WebDriver driver;
  static int i=1;
  public NewScreenshot(WebDriver driver)
  {
   NewScreenshot.driver=driver;
  }     
        public static String CaptureScreen(WebDriver driver, String imgcsi)
        {
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try 
        {
        fileInputStreamReader = new FileInputStream(sourceFile);
        byte[] bytes = new byte[(int)sourceFile.length()];
        fileInputStreamReader.read(bytes);
        encodedBase64 = new String(Base64.encodeBase64(bytes));       
        File file=new File("target\\");
        String path=file.getAbsolutePath();
        String DescFile="target\\"+ imgcsi + " " + System.currentTimeMillis()+".png";
           i++;
           System.out.println("Screenshot Taken");
                File destination = new File(DescFile);
                FileUtils.copyFile(sourceFile, destination);

            } catch (IOException e) 
            {
                e.printStackTrace();
            }
            return "data:image/png;base64,"+encodedBase64;
        }
}