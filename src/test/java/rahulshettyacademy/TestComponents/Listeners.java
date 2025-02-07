package rahulshettyacademy.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener {
	
	 ExtentReports  extentReport = reportConfig();
	  ExtentTest test;

 //Conccurancy problem : parallel Test run problem
	  
	  ThreadLocal<ExtentTest> threadLocal = new ThreadLocal(); 
	  
        //using this Thread local class u make ur object thread safe. thread safe means we u run parllel each testcase create it own thread.
         // so it won't interrupt other overriding variable.
	  // have to puch test object into this thread local in test enter method.
	  
	  
	@Override
	public void onTestStart(ITestResult result)  //Result veriable: it holds the testcase information
	{
		// TODO Auto-generated method stub
		
		//Test Entry : testCreate
			test=  extentReport.createTest(result.getMethod().getMethodName());
                         //result.getMethod().getMethodName(): it will give the test case name.
			threadLocal.set(test); 
			             // when puch this test object in the Thread local class, what it do, it will Assign one "unique thread ID".
		                 //So with this unique thread ID (like: ErrorValidationTest )-> "test" object it will create one map.
		                // in java each test case has one unique thread id.
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
		threadLocal.get().log(Status.PASS, "on Test is Success execute");  //this is just a log
		
	}

	@Override
	public void onTestFailure(ITestResult result) // this resul will the hold the  all testcase info
	{
		// TODO Auto-generated method stub
		
		//Fail:
		
		threadLocal.get().fail(result.getThrowable());                       //it will print error message in the reprot.
		
		 try
		 {
		 
		//Step1 ScreenShot
		//Step 2 attach
		//Since result will the hold the  all testcase info , so it is also store  driver . 
		   // so get the driver through this result variable  
				driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
				//this is the code which  helo u to get the driver information.	
		 }
		 
		 catch(Exception e1)
		 {
			 e1.printStackTrace();
			 
		 }
				
				String destPath = null;
		         
				try 
				{
					destPath = getScreenshot(result.getMethod().getMethodName(),driver);
				} 
				
				catch (IOException e) 
				{
							e.printStackTrace();
				}
				
				threadLocal.get().addScreenCaptureFromPath(destPath);   //attaching screen shot
	
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		System.out.println("TEst is compelte sussessfullll@@@");
		extentReport.flush();
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	
	
	}

	


