# Introduction 
This is a automation framework for campaign monitor. I re-write webdriver default core fielddecorator, which gives me more control on webdriver behaviors.

# Project structure
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/projectStructure.PNG)

# Update configuration.xml
![alt text](https://github.com/kettlescott/CPGroupAutomation/blob/master/config.PNG)

# Build and Test
1.mvn clean install -DskipTests

2.Run mvn clean install -Dsurefire.suiteXmlFiles=".\cpGroupTestsuite.xml" -DconfigFilePath=".\configuration.xml"

3.test report is under target folder

# Docker and zalenium
The framework supports docker and zalenium, please update REMOTE_DRIVER_URL to you own hub




# TestUIAutomation
