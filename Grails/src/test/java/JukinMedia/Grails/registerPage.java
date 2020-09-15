package JukinMedia.Grails;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class registerPage extends base{
	public WebDriver driver;
	registerpagePO rpo;	
	
	@Test(dataProvider = "getData")
	public void registerNew(String email,String password,String confPassword) throws IOException, InterruptedException {
		
		driver=initializeBrowser();
		driver.get(pro.getProperty("url"))	;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		rpo= new registerpagePO(driver);
		rpo.registerNew().click();
		Thread.sleep(3000);
		rpo.registerNewEmail().sendKeys(email);
		rpo.registerNewPassword().sendKeys(password);
		rpo.registerConfPassword().sendKeys(confPassword);
		rpo.registernewSubmit().click();
		String title = driver.getTitle();
		if(title.contains("Registration Successful"))
		{
			System.out.println(rpo.registernewSuccess().getText());
			System.out.println("New User Successfully Registered");
		}
		else 
		{	
			String Error = rpo.registernewFail().getText();
			System.out.println(Error);
			System.out.println("Error with data provided");
		}
		
	}
	
	@Test
	public void NavigateToMain() throws IOException
	{
		driver=initializeBrowser();
		driver.get(pro.getProperty("url"))	;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		rpo= new registerpagePO(driver);
		rpo.registerNew().click();
		rpo.backtoLogin().click();
		String title = driver.getTitle();
		Assert.assertEquals("Login Page", title);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object [][] data= new Object[4][3] ;
				data [0][0] = "ekansh.2711@gmail.com" ;
				data [0][1] = "ekansh" ;
				data [0][2]	= "ekansh" ;	
				
				data [1][0] = "ekansh" ;
				data [1][1] = "ekansh" ;
				data [1][2]	= "ekansh" ;	
				
				data [2][0] = "ekansh.2711@gmail.com" ;
				data [2][1] = "ekansh" ;
				data [2][2]	= "ekansh2" ;
				
				data [3][0] = "ekansh.2711@gmail.com" ;
				data [3][1] = " " ;
				data [3][2]	= " " ;
				
				return data;	
	}

}
