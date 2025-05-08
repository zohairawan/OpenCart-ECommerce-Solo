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
- Reporting: `Extent Reports`
- Data-Driven Testing: `Excel-based test data`
- CI: `Jenkins`
- Containerization and Parallel Execution: `Docker with Selenium Grid`
- Test Execution Methods: `Maven, Command Prompt, and run.bat file`

### Steps
1. Add necessary dependencies to pom.xml
2. Create appropriate framework folder structure
   - <img src="img/folderStructure.png" alt="Folder Structure" height="250" width="200">
3. Automate Account Registration test case
   - Create `BasePage`(Reduce code duplication/Reusability)
   - Create `HomePage` and `AccountRegistrationPage`
   - Create `BaseTest`(Reduce code duplication/Reusability)
   - Create `AccountRegistrationTest`
4. Add logging
   - Add `log4j2.xml` in `src/test/resources`
   - Update `BaseTest` to include `logger` object
   - Add `log statements` to `AccountRegistrationTest`
5. Parameterize browser
   - Add `parameter` tag to `xml` file
   - Add `@Parameter` annotation to `setUp()` in `BaseTest`
   - Add parameters to `setUp()`
6. Execute tests across multiple browsers
   - Specify which browser you want to use in the parameter tag
     - `<parameter name="browser" value="chrome"/>`
7. Enable Parallel Execution of tests
   - Add appropriate attribute and value in to appropriate tag `xml` file (`parallel="tests"`)
     - `<suite name="Cross Browser" parallel="tests" thread-count="5">`
8. Read data from `.properties` file
   - Add `config.properties` file in `src/test/resources`
   - Add data as `key`=`value` pairs in `config.properties`
     - `homepageURL=https://tutorialsninja.com/demo/`
   - Update `BaseTest` to include `FileReader` and `Properties` objects
   - Update `setUp()`to load and read data from `config.properties` file
   - Update `BaseTest` to read URL from `config.properties` file instead of hard coding URL
9. Automate Login test case
    - Create/Update Page Classes:
        - `LoginPage` (New)
        - `MyAccountPage` (New)
        - `HomePage` (Updated)
    - Create `LoginTest`
    - Add `LoginTest` to XML file
10. Automate Data-Driven Login test case