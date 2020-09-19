# Environment
  1. Java >= 1.8.0_261

  2. Maven >= Apache Maven 3.6.1

# Framework Introduction
1 .The idea behind this framework is to re-write Webdriver core DefaultFieldDecorator.  The default DefaultFieldDecorator is not flexible and does not support customized object
initialization. By rewriting the default DefaultFieldDecorator, I can use webdriver to support more customized UI components.
there is an [article!](https://stackoverflow.com/questions/9478592/how-to-implement-user-types-for-findby-annotation) in stacksoverflow which discuss the idea.

2. The Framework core engine is under  /com/scott/web/tests

3. com/scott/web/datarepublic contains customized component I made the for the project



# Project structure
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/projectStructure.PNG)

# Update configuration.xml
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/config.PNG)

# Coding Style
1.The java project follows google'java coding format, for detail please refer to [Google Java Format!](https://github.com/google/google-java-format)

2.Run mvn git-code-format:format-code -Dgcf.globPattern=**/* to check and format java code

# Build and Test
1.mvn clean install -DskipTests

2.Run mvn clean install -Dsurefire.suiteXmlFiles=".\Testsuite.xml" -DconfigFilePath=".\configuration.xml"

3.test report is under target folder


