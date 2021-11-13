package setupConfig;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import screen.web.BasePageObject;

/**
 * Created by pravin.kumbhare on 30-10-2021.
 */
public class TestListener extends BasePageObject implements ITestListener {

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(iTestResult.getName()+" test case started");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("The name of the testcase passed is :"+iTestResult.getName());
    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info("The name of the testcase failed is :"+iTestResult.getName());
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("The name of the testcase Skipped is :"+iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
