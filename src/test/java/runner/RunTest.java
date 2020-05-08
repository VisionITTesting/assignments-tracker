package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "classpath:features",
		glue = "stepdefs" ,
		plugin = {"pretty",
				"html:target/html/",
				"json:target/json/file.json",
		},
		strict = true,
		dryRun = false
		) 
public class RunTest {

}