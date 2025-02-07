package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer  {

	/*Logic Scenerios:
		 * If testcase fail and it comes to this block and ask u want to re-run again.
		 * How Many times you want to re run
	 */
	
	int count = 0;
	int maxTry = 1;           // how many times u want to re-run
	
	@Override
	public boolean retry(ITestResult result) 
	{
	  
      if (count<maxTry)
      {
    	  count++;
    	  return true;
      }
	     return false;
	}

}
