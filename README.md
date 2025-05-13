# Open Cart E-Commerce Project

### Objectives

- Build Hybrid Automation Framework from scratch
- Get hands-on experience with end-to-end tools for Selenium Java Automation

### Purpose

- Work on a project that mimics real world project workflows

### Technologies/Tools Used:

- Programming Language: `Java`
- Build Tool: `Maven`
- Test Framework: `TestNG`
- Test Execution: `Selenium WebDriver & Selenium Grid`
- Configuration Management: `xml and .properties file`
- Logging: `Log4j2`
- Data-Driven Testing: `Excel-based test data`
- Reporting: `Extent Reports`
- CI: `Jenkins`
- Containerization and Parallel Execution: `Docker with Selenium Grid`
- Test Execution Methods: `Maven, Command Prompt, and run.bat file`

### Steps

1. Add necessary dependencies to pom.xml
2. Create appropriate framework folder structure
    - <img src="img/folderStructure.png" alt="Folder Structure" height="250" width="200">
3. Automate Account Registration test case
    - Create `BasePage`(Reduce code duplication/Reusability)
    - Create `BaseTest`(Reduce code duplication/Reusability)
    - Create `HomePage` and `AccountRegistrationPage`
    - Create and implement `AccountRegistrationTest`
4. Logging
    - Add `log4j2.xml` in `src/test/resources`
    - Update `BaseTest` to include `logger` object
    - Add `log statements` to `AccountRegistrationTest`
5. Parameterization
    - Add `parameter` tag to `xml` file
    - Add `@Parameter({"os", "browser"})` annotation to `setUp()` in `BaseTest`
    - Add parameters to `setUp()` -> `setUp(String os, String browser)`
6. Multiple browsers
    - Specify which browser you want to use in the parameter tag
        - `<parameter name="browser" value="chrome"/>`
7. Parallel Execution
    - Add appropriate attribute and value in to appropriate tag `xml` file (`parallel="tests"`)
        - `<suite name="Cross Browser" parallel="tests" thread-count="5">`
8. Read data from `.properties` file
    - Add `config.properties` file in `src/test/resources`
    - Add parameters as `key`=`value` pairs in `config.properties`
        - `homepageURL=https://tutorialsninja.com/demo/`
    - Update `BaseTest` to include `FileReader` and `Properties` objects
    - Update `setUp()`to load and read parameters from `config.properties` file
    - Update `BaseTest` to read URL from `config.properties` file instead of hard coding URL
9. Automate Login test case
    - Create/Update Page Classes:
        - `LoginPage` (New)
        - `MyAccountPage` (New)
        - `HomePage` (Updated)
        - Create and implement `LoginTest`
        - Add `LoginTest` to XML file
10. DataProvider
    - Update `MyAccountPage`
    - Place Excel file in `testData` folder
    - Create `ExcelUtility` Class in `test/java/utils`
    - Create `DataProviders` Class in `utils` package
    - Create `LoginDataDrivenTestCase`
    - Add `LoginDataDrivenTestCase` to `xml` file
11. Grouping tests
    - Add `group` attribute to each `@Test`
    - Add `group` attribute to `setUp()` and `tearDown()`
    - Create `grouping.xml` to run groups
12. Reporting - ExtentReports
    - Create `ExtentReportManager` Class in `test/java/utils`
      - Implement `ITestListener` in `ExtentReportManager` Class
    - Add `ExtentReportManager` to all TestNG `.xml` files
      - `<listeners><listener class-name="utils.ExtentReportManager"/></listeners>`
    - Make `BaseTest` `driver` `static`
13. Rerun only Failed Tests
    - test-output => `testng-failed.xml`
      - Run `_mvn test -DsuiteXmlFile=testng.xml` to generate `testng-failed.xml` file if one is not present
14. Selenium Grid
    - STANDALONE SETUP
      - Download Selenium Grid `.jar`
      - Start Selenium Grid `java -jar file-name.jar standalone`
      - Hub URL `http://localhost:4444/`
    - DISTRIBUTED SETUP (VM)
      - Download Grid `.jar` for every machine (hub & node)
      - Make machine a hub `java -jar file-name.jar hub`
      - Make machine a node and attach to hub `java -jar file-name.jar node --hub http://<hub-url>:4444`
      - Grid URL `http://localhost:4444/`
    - Running test cases on Grid environment
      - Add parameter to run tests in Grid environment or not in `config.properties` file
        - `executionEnv=local/remote` (you choose whether local OR remote not both)
      - Update `BaseTest` `setUp()`
        - Run in `remote` or `local`
        - `remote`
          - `String hubURL = http://localhost:4444/wd/hub` (Append `/wd/hub` to Hub URL)
          - Create `DesiredCapabilities` object
          - Set OS based on value from `config.properties` file -> `capabilities.setPlatform(Platform.WIN11);`
          - Set Browser based on value from `.xml` file -> `capabilities.setBrowserName("chrome");`
          - Create `RemoteWebDriver`object (we don't know the browser until runtime so this Class eliminates that issue)
            - `driver = new RemoteWebDriver(new URL(hubURL), capabilities);`