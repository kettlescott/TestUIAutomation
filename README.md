# Environment
  1. Java >= 1.8.0_261

  2. Maven >= Apache Maven 3.6.1

# Framework Introduction
1 .The idea behind this framework is to re-write Webdriver core DefaultFieldDecorator.  The default DefaultFieldDecorator is not flexible and does not support customized object
initialization. By rewriting the default DefaultFieldDecorator, I can use webdriver to support more customized UI components.
there is an [article!](https://stackoverflow.com/questions/9478592/how-to-implement-user-types-for-findby-annotation) in stacksoverflow which discuss the idea.

2. The Framework core engine is under  /com/scott/web/tests

3. com/scott/web/datarepublic contains customized component I made the for the project

# Efficiency

1.I developed a in memory cache which is able to speedup UI tests. the idea is when a new page object is created , it will stores in a in-memory-cache.
if a test wants a page object , instead of creating new one,  it will query cache first. if a certain page object is not found, a new page object will be created

2.In memo cache and creating page object is using java reflection. for more details, please refer to
   \src\main\java\com\scott\web\base\page\PageVisitor.java



# Project structure
![alt text](https://github.com/kettlescott/TestUIAutomation/blob/master/Automation.jpg)

# Update configuration.xml
Configuration is under ./configuration.xml
```
<?xml version="1.0" encoding="utf-8"?>
<configuration>
    <appSettings>
        <add key="URL" value="https://auspost.com.au/parcels-mail/calculate-postage-delivery-times#/" />
        <add key="REPORT" value="target\" />
        <add key="DRIVER" value="chrome"/>
    </appSettings>
</configuration>
```

# Update Testsuite.xml
Configuration is under ./configuration.xml
```
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="CPGroupTest">
    <test name="CPGroupTest" >
        <classes>
            <class name="com.scott.web.tests.tests.HappyPathTest">
                <methods>
                    <include name="testCalculation" />
                </methods>
            </class>
            <class name="com.scott.web.tests.tests.NegativePathTest">
                <methods>
                    <include name="testInvalidAddress" />
                </methods>
                <methods>
                    <include name="testInvalidBox_Size_Weight_Length_Height_width" />
                </methods>
                <methods>
                    <include name="test_non_numeric_characters_Weight_Length_Height_width" />
                </methods>
                <methods>
                    <include name="test_empty_input" />
                </methods>
                <methods>
                    <include name="testInvalidDeliverDate" />
                </methods>
                <methods>
                    <include name="testEmptyDate" />
                </methods>
            </class>
        </classes>
    </test>

</suite>
```



# Coding Style
1.The java project follows google'java coding format, for detail please refer to [Google Java Format!](https://github.com/google/google-java-format)

2.Run mvn git-code-format:format-code -Dgcf.globPattern=**/* to check and format java code

# Build and Test
1.mvn clean install -DskipTests

2.Run mvn clean install -Dsurefire.suiteXmlFiles=".\Testsuite.xml" -DconfigFilePath=".\configuration.xml"

3.test report is under target folder


