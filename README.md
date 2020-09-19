# Introduction 
This is a automation framework for campaign monitor. I re-write webdriver default core fielddecorator, which gives me more control on webdriver behaviors.

# Project structure
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/projectStructure.PNG)

# Update configuration.xml
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/config.PNG)

# Coding Style
1.The java project follows google'java coding format
2.Run mvn git-code-format:format-code -Dgcf.globPattern=**/* to check and format java code 

# Build and Test
1.mvn clean install -DskipTests

2.Run mvn clean install -Dsurefire.suiteXmlFiles=".\Testsuite.xml" -DconfigFilePath=".\configuration.xml"

3.test report is under target folder


