import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import Service.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import ModelEntity.OglasEntity;
import ModelEntity.OglasKP;


public class MainClass {

	public static void main(String[] args) {
		
		
	  	File fXmlFile2 = new File("parametri.xml");
		DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder2=null;
		Document doc2=null;
		try {
			dBuilder2 = dbFactory2.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			doc2 = dBuilder2.parse(fXmlFile2);
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// 

			
			e2.printStackTrace();
		}
		
		doc2.getDocumentElement().normalize();

		
		NodeList lista3=doc2.getElementsByTagName("uploadSlikaBrzina");
		
		int brzinaUploadSlika=0;
		
		try{
		brzinaUploadSlika=Integer.parseInt((String)lista3.item(0).getTextContent());
		}
		catch(Exception e){}
		
		
		 String brojOglasaParsiranje=null;
		 String [] parsedParts;
		 int brojOglasa=0;
		 
		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 //Document doc = docBuilder.parse (new File(".xml"));
		 
		 //------------------------------------------------------------------------------
			// TODO Auto-generated method stub
	      WebDriver driver;
	      
	      
	      System.out.println(" pre instanciranja web drivera");
	      System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\SeleniumWebDriver\\geckodriver.exe");
	      
	      System.out.println("pre instanciranja web drivera, nakon gecka");
	      
	      driver= new FirefoxDriver();
	      //Puts an Implicit wait, Will wait for 10 seconds before throwing exception
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     
	      new WebDriverWait(driver, 30);
	      driver.manage().window().maximize();
	      //Launch website
  	  		try{
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	      
  	  		System.out.println("Posle instanciranja web driver-a");
	      
	      driver.navigate().to("https://www.kupujemprodajem.com/user.php?action=login&data[remember]=0");
	      
	      ///////////////////////////////////////////////&data[remember]=0
	      

	      
	      /////////////////////////////////
	      
	      
	      //login on website
	      driver.findElement(By.xpath("//input[@name='data[email]']")).sendKeys("djordjinok1@live.com");
	      
	      //ucitava se sifra
	      
	  	File fXmlFile = new File("sifra.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=null;
		Document doc=null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// 

			
			e2.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();

		
		NodeList lista=doc.getElementsByTagName("sifra");
		
		
	      //System.out.println((String)lista.item(0).getTextContent());
	      driver.findElement(By.xpath("//input[@name='data[password]']")).sendKeys((String)lista.item(0).getTextContent());
		//driver.findElement(By.xpath("//input[@name='data[password]']")).sendKeys("solunacrastina1918");  
		
	      try{
	    	  
	      Service.waitUntilElementIsNotClickable(By.xpath("//input[@value='Ulogujte se']"), driver);
	      //driver.findElement(By.xpath("//input[@value='Ulogujte se']")).click();
	      }
	      catch(Exception e){
	    	  System.out.println("Nije pronasao login button ");
	    	  System.exit(0);
	      }
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("//a[@class='link-tab active-link-tab']"), driver);
	      
	      brojOglasaParsiranje=(String)driver.findElement(By.xpath("//a[@class='link-tab active-link-tab']")).getText();
	      parsedParts=brojOglasaParsiranje.split(" ");
	      String number= parsedParts[2];


	      String answer = number.substring(number.indexOf("(")+1,number.indexOf(")"));
	      brojOglasa=Integer.parseInt(answer);
	      
	      
	      String xPath="(//a[text()='Obriši'])[1]";
	      	      
	      //u ovom foru se brisu oglasi
	      
	      
	      ObjectMapper mapper2=new ObjectMapper();
		    
	      ArrayList<OglasKP> lista2 = new ArrayList<OglasKP>();


	     
	      
	      try {

			 mapper2.writeValue(new File("oglasiKP.json"),lista2);
		
			
			 
			 
			 }
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      // ovde se citaju podaci iz  json fajla koji je popunjen u C# delu aplikacije
	      ObjectMapper mapper=new ObjectMapper();
	      OglasEntity[] oglasi=null;
	      try {
	    	  String workingDir = System.getProperty("user.dir");
	    	  String jsonPath=workingDir+"oglasi.json";
	    	  
			 oglasi= (OglasEntity[]) mapper.readValue(new File("oglasi.json"),OglasEntity[].class);
			 /*
			 OglasEntity tempOglas;
			 if(oglasi.length >0)
			 {
				 
			 OglasEntity maxPriceOglas=oglasi[0];
			 for(int i=0;i<oglasi.length;i++)
				 for(int j=1;j<oglasi.length;j++)
				 {
					 if(oglasi[i].cena > oglasi[j].cena)
					 {
						 tempOglas=oglasi[i];
						 oglasi[i]=oglasi[j];
						 oglasi[j]=tempOglas;
					 }
				 }
			 }
			 */
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Ovo treba da ispise "+oglasi[0].textOglasa);
	      
	      
	      
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	      
	      //ako je podizanje oglasa, nemoj brisati stare
	      if(args.length == 1)
	      {

		      
	    	  
	      for(int i=0;i<oglasi.length;i++)
	      {
	    	  if(oglasi[i].obnavljanje)
	    	  {
	    	  //driver.navigate().to(); 
	    	 try{
	    		 
	    		 
	   	  	try{
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 
	    		 
	         Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='inputKeyword']"), driver);
	    		  
		     driver.findElement(By.xpath("//input[@id='inputKeyword']")).sendKeys(oglasi[i].nazivOglasa);  
		     
		   	  	try{
						Thread.sleep(1500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	

	    	 Service.waitUntilElementIsNotVisible(By.xpath("//input[@value=' Traži ']"), driver);
	    	 
	    	 
	    	 driver.findElement(By.xpath("//input[@value=' Traži ']")).click();
	         
		   	  	try{
						Thread.sleep(4000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	 
	    	 Service.waitUntilElementIsNotVisible(By.xpath(xPath), driver);
	    	 
	    	 String url=driver.findElement(By.xpath(xPath)).getAttribute("href");
	    	 
	    	 driver.navigate().to(url); 
	    	 
	    	 Service.waitUntilElementIsNotVisible(By.xpath("//input[@value='other']"), driver);
	    	 driver.findElement(By.xpath("//input[@value='other']")).click();
	    	 
	    	 Service.waitUntilElementIsNotVisible(By.xpath("//input[@value=' Obriši ']"), driver);
	    	 driver.findElement(By.xpath("//input[@value=' Obriši ']")).click();
	    	 }
	    	 catch(NoSuchElementException e){}
	    	 catch(TimeoutException e){}
	    	 finally
	    	    {
	    	        
	    	    }
	    	 
	    	 Service.waitUntilElementIsNotClickable(By.xpath("//input[@value=' Svi oglasi ']"), driver);
	    	 
		   	  	try{
						Thread.sleep((brzinaUploadSlika/2)*1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	 
	    	 
	    	 //driver.findElement(By.xpath("//input[@value=' Svi oglasi ']")).click();
	    	  }
	    	  
	      }
	      
	      }
	      else
	      {
	    	  System.out.println("nije brisanje");
	      }
	      
	   	      

	      
	      
	      
	      

	    //JSON from String to Object
	      

	      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    //klik na postavi oglas
	      //ovde se postavljaju svi oglasi
	      
	      
	      for(int i=0;i<oglasi.length;i++)
	      {
	    	  
	      try{
	      if(oglasi[i].obnavljanje)
	      {
	    	/*
	    	  Service.waitUntilElementIsNotVisible(By.xpath("//a[contains(text(),'Oglasi')]"), driver);
	    	
	    	driver.findElement(By.xpath("//a[contains(text(),'Oglasi')]")).click();
	    	  
		      Service.waitUntilElementIsNotVisible(By.xpath("//a[@class='link-tab active-link-tab']"), driver);
		      
		      brojOglasaParsiranje=(String)driver.findElement(By.xpath("//a[@class='link-tab active-link-tab']")).getText();
		      parsedParts=brojOglasaParsiranje.split(" ");
		      String number2= parsedParts[2];


		      String answer2 = number.substring(number2.indexOf("(")+1,number2.indexOf(")"));
		      int brojOglasa2=Integer.parseInt(answer2);
		    
		   */
		      
	    	  	try{
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
	         
	    	
	    	  
	      driver.navigate().to("https://www.kupujemprodajem.com/oglasi.php?action=new");
	      
	      
	      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	      
	      
	      //otvara kategorije
	      
  	  	try{
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	      
	      Service.waitUntilElementIsNotClick(By.xpath("//div[@id='categorySelection']"), driver);
	      
	      //Service.waitUntilElementIsNotVisible(By.xpath("//div[@id='categorySelection']"),driver);
	      
	      driver.findElement(By.xpath("//div[@id='categorySelection']")).click();
	      
	      
	  	  	try{
					Thread.sleep(2500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	      
	      //bira kategoriju
	  	  /*	
	      System.out.println("ispred kategorije");
	      Service.waitUntilElementIsNotClickable(By.xpath("//div[@data-value='"+oglasi[i].idKategorije+"']"), driver);
	      //driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idKategorije+"']")).click();
	      System.out.println("Posle kategorije");
	      */
	  	  	
		      System.out.println("Biranje kategorije");
		      WebElement elementToClick=driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idKategorije+"']"));
		      //((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementToClick.getLocation().y+")");
		      Service.waitUntilElementIsNotClick(By.xpath("//div[@data-value='"+oglasi[i].idKategorije+"']"), driver);
		      System.out.println("Nakon biranja grupeeee");
		      Actions act2 = new Actions(driver);
		      
		      
		      
		      
		      try {
					Thread.sleep(2500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		      driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idKategorije+"']")).click();	
	      
	      //klik na grupu
	      
	     // driver.findElement(By.xpath("//span[@id='groupInsertSpot']")).click();
	      /*
	      try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	      */
	      //bira grupu
	      
	      
	      
	      System.out.println("Biranje grupeeeeeeeeeeeee");
	      WebElement elementToClick2=driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idGrupe+"']"));
	    //  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementToClick2.getLocation().y+")");
	      Service.waitUntilElementIsNotClick(By.xpath("//div[@data-value='"+oglasi[i].idGrupe+"']"), driver);
	      System.out.println("Nakon biranja grupeeee");
	      Actions act = new Actions(driver);

	      try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	      //act.moveToElement(driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idGrupe+"']"))).click().perform();
	      driver.findElement(By.xpath("//div[@data-value='"+oglasi[i].idGrupe+"']")).click();


	      
	      //popuni naslov oglasa
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='data[name]']"), driver);
	      driver.findElement(By.xpath("//input[@id='data[name]']")).sendKeys(oglasi[i].getNazivOglasa());
	      
	      try{
		      
	      Service.waitUntilElementIsNotClick(By.xpath("//input[@id='data[condition]"+oglasi[i].getStanje()+"']"), driver);

	      driver.findElement(By.xpath("//input[@id='data[condition]"+oglasi[i].getStanje()+"']")).click();
	      }
	      catch(Exception e){}
	      
	      
	      //popuni cenu
	      if((oglasi[i].cena <5) && oglasi[i].isDin)
	      {
	    	  System.out.println("usao u for za cenu");
	    	  String dataText;
	    	  if(oglasi[i].cena == 1)
	    	  {
	    		  dataText="Dogovor";
	    	  }
	    	  else if(oglasi[i].cena == 2)
	    	  {
	    		  dataText="Kontakt";
	    	  }
	    	  else
	    	  {
	    		  dataText="Pozvati";
	    	  }
	    	
		      Service.waitUntilElementIsNotClick(By.xpath("//span[contains(text(),'ili sledeći opis')]"), driver);
		      driver.findElement(By.xpath("//span[contains(text(),'ili sledeći opis')]")).click();
	    	  
		      Service.waitUntilElementIsNotClick(By.xpath("//div[@data-value='"+dataText+"']"), driver);
		      driver.findElement(By.xpath("//div[@data-value='"+dataText+"']")).click();
		      
	    	  System.out.println("izasao iz if-a");

	      }
	      else
	      {
	    	  System.out.println("else deo");

		      Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='price_number']"), driver);

		      driver.findElement(By.xpath("//input[@id='price_number']")).sendKeys(String.valueOf(oglasi[i].getCena()));  
	      }
	      
	      
	      System.out.println("Popouni cenu.");
	      //popuni stanje //korisceno
	      

	      //popuni valutu
	      
	      if(!oglasi[i].isDin){
		   Service.waitUntilElementIsNotClick(By.xpath("//input[@id='currency_eur']"), driver);

	      driver.findElement(By.xpath("//input[@id='currency_eur']")).click();
	      }
	      else
	      {
	      Service.waitUntilElementIsNotClick(By.xpath("//input[@id='currency_rsd']"), driver);

	      driver.findElement(By.xpath("//input[@id='currency_rsd']")).click();
	      }
	      
	      
	      //zamena
	      if(oglasi[i].isZamena())
	      {
	      try{
		  Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='exchange_yes']"), driver);
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='exchange_yes']")));
	      driver.findElement(By.xpath("//input[@id='exchange_yes']")).click();
	      }
	      catch(Exception e){}
	      }

	      
	      //prebaci se na iframe
	    
		  Service.waitUntilElementIsNotVisible(By.id("data[description]_ifr"), driver);

	      driver.switchTo().frame(driver.findElement(By.id("data[description]_ifr")));
	      
	      
	      
		  Service.waitUntilElementIsNotVisible(By.id("tinymce"), driver);

	    //  driver.findElement(By.id("tinymce")).sendKeys(oglasi[i].getTextOglasa());
	      
	      
		  WebElement element=driver.findElement(By.id("tinymce"));
		  
		  String content="";
		  
		try {
		  content =  Service.rtfToHtml(new StringReader(oglasi[i].getTextOglasa()));
		 
		  StringBuilder contentBuilder = new StringBuilder();
		  try {
		      BufferedReader in = new BufferedReader(new StringReader(content));
		      String str;
		      while ((str = in.readLine()) != null) {
		          contentBuilder.append(str);
		      }
		      in.close();
		  } catch (IOException e) {
		  }
		  content = contentBuilder.toString();
		  
		 //content.replaceAll("\"", "&quot;");
		  
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		System.out.println(content + "   editor");  
		  
		  
		  ((RemoteWebDriver) driver).executeScript("arguments[0].innerHTML = '"+ content+ "';", element);
		  
		  
		  /*
	      try {
	    	  int opisOglasaLength=oglasi[i].textOglasa.length();
	    	  int valueForSleep=6*opisOglasaLength;
				Thread.sleep(valueForSleep);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	      */
	      // vracanje na osnovnu stranicu sa iframe-a
	      driver.switchTo().defaultContent();
	      

	      //klikni na dodaj slike button
	      
	      
	      //ovde se dodaju slike u foru
	      for(int j=0;j<oglasi[i].imagesPath.length;j++)
	      {
	    	  
		      try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	  
	      	
	      Service.waitUntilElementIsNotVisible(By.id("addPhotoButtonInList"), driver);

	      driver.findElement(By.id("addPhotoButtonInList")).click();
	      
	      
	      try{
		      
	      try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      
	      //String commandString="autoItKP.exe"+" "+"image1.jpg";
	      
	      
	      //System.out.println("\""+ oglasi[i].imagesPath[j]+"\"");
	      
	      new ProcessBuilder("autoItKP.exe","\""+oglasi[i].imagesPath[j]+"\"").start();
	      
	      //Runtime.getRuntime().exec(commandString);
	      
	      
	      try {
			Thread.sleep(brzinaUploadSlika*1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	      }
	      catch(Exception e){}
	      }
	      
	      
	      try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      
	      //cekiranje za kp izlog
	      if(oglasi[i].kpIzlogDodavanje)
	      try{
	    
	      Service.waitUntilElementIsNotVisible(By.id("data[kpizlog]yes"), driver);
	      driver.findElement(By.id("data[kpizlog]yes")).click();

	      }
	      
	      catch(Exception e){}
	      


	      
	      Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='accept_yes']"), driver);

	      driver.findElement(By.xpath("//input[@id='accept_yes']")).click();
	      

	      if(oglasi[i].idKategorije == 26 )
	      {
		      Service.waitUntilElementIsNotVisible(By.id("agreement_checkbox_3"), driver);

	      
		      driver.findElement(By.id("agreement_checkbox_3")).click();
	      }
	      
	      
	      //Service.waitUntilElementIsNotClickable(By.xpath("//input[@action-name='adInfoNextButton']"), driver);
	      try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("(//input[@action-name='adInfoNextButton'])[2]"), driver);
	      
	      driver.findElement(By.xpath("(//input[@action-name='adInfoNextButton'])[2]")).click();
	      

	      
	      //Service.waitUntilElementIsNotClick(By.xpath("(//input[@action-name='adPromoNextButton'])[2]"), driver);
	      
	      try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("(//input[@action-name='adPromoNextButton'])[2]"), driver);
	      
	      driver.findElement(By.xpath("(//input[@action-name='adPromoNextButton'])[2]")).click();;
	      

	      try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("//input[@id='swear_yes']"), driver);

	      driver.findElement(By.xpath("//input[@id='swear_yes']")).click();
	      
	      
	      
	      Service.waitUntilElementIsNotVisible(By.xpath("(//input[@action-name='adDeclarationPostButton'])[2]"), driver);

	      driver.findElement(By.xpath("(//input[@action-name='adDeclarationPostButton'])[2]")).click();
	      
	      
	      
	      
	     
	      ObjectMapper mapper3=new ObjectMapper();
	      ArrayList oglasiAL=new ArrayList<OglasKP>();
	      try {

	    	  
			 oglasiAL= (ArrayList<OglasKP>) mapper3.readValue(new File("oglasiKP.json"),new TypeReference<ArrayList<OglasKP>>(){});

			 System.out.println(oglasiAL.toString()+ " oglasi obnavlajanje da");

			 
			 oglasiAL.add(new OglasKP(true));
			 
			 mapper3.writeValue(new File("oglasiKP.json"), oglasiAL);
			 
			 
			 }
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      
	      }//end broj oglasa < od brojdozovljenih
	      
	      else{
	      
	      ObjectMapper mapper4=new ObjectMapper();
	      ArrayList<OglasKP> oglasiKP2=new ArrayList<OglasKP>();
	      try {

	    	  
			 oglasiKP2= (ArrayList<OglasKP>) mapper4.readValue(new File("oglasiKP.json"),new TypeReference<ArrayList<OglasKP>>(){});
			 
			 
			 oglasiKP2.add(new OglasKP(false));
			 System.out.println(oglasiKP2.toString()+ " oglasi obnavlajanje ne");
	
			 
			 mapper4.writeValue(new File("oglasiKP.json"), oglasiKP2);
			 
			 
			 }
		catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      }
	      }
	      catch(TimeoutException e){}
	      catch(NoSuchElementException e){}
	      finally{}
	      
	      //kraj else dela
	      }//end obnavaljanje
	      
	      
	      
	      }//kraj main-a
	      
	      
	}


