package Service;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Service {
	
	public  static boolean  isElementPresent(By by,WebDriver driver){
	    try {
	    	
	    	System.out.println(by);
	        driver.findElement(by);
	        return true;
	    }
	    catch (org.openqa.selenium.NoSuchElementException e){
	    	System.out.println(by + "false");
	        return false;
	    }
	}
	
	public static void waitUntilElementIsNotVisible(By by,WebDriver driver){
		

		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void waitUntilElementIsNotClick(By by,WebDriver driver){
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		
	}
	
	public static void waitUntilElementIsNotClickable(By by,WebDriver driver){
		//WebDriverWait wait = new WebDriverWait(driver,20);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		
        WebElement element = driver.findElement(by);
        
        boolean clicked = false;
        do{
            try {
            	
                element.click();
            } catch (WebDriverException e) {
                continue;
            } finally {
                clicked = true;
            }
        } while (!clicked);
		
		
	}
	
	public static String rtfToHtml(Reader rtf) throws IOException {
		   JEditorPane p = new JEditorPane();
		   p.setContentType("text/rtf");
		   EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
		   try {
		      kitRtf.read(rtf, p.getDocument(), 0);
		      kitRtf = null;
		      EditorKit kitHtml = p.getEditorKitForContentType("text/html");
		      Writer writer = new StringWriter();
		      kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
		      return writer.toString();
		   } catch (BadLocationException e) {
		      e.printStackTrace();
		   }
		   return null;
		}

}
