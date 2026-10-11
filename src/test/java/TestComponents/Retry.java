package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	// Only executes if the test has failed
	
	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count < maxTry)
		{
			return true; // will re-run the test
		}
		
		return false;
	}

	
}
