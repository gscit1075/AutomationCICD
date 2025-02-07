package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//monochrome: Which will give the result in readable format.
//Plugin: to genrate the report in html format.
//AbstractTestNGCucumberTest : Runs each cucumber scenario found in the features file 

@CucumberOptions(features ="src/test/java/cucumber",glue = "rahulshettyacademy.stepDefinitions", monochrome =true,tags = "@Purchase", plugin={"html:target/cucumber.html"} )
public class TestNgTestRunner extends AbstractTestNGCucumberTests
{
	
	
	
	

}
