# BirdsApp
Birds Registry REST Service API

1) BASIC DESIGN:
    
    * REST SERVICE API designed and implemented in Java using Jersey(JAX-RS ) web framework.
    * Design mainly has 2 components:
      1) Web framework - Jersey(JAX-RS)
      2) Web Service Layer implmented using Jersey
      3) Data access layer
      
    * Web Server used in Tomcat on localhost:8080, database is MongoDb on port 27017
    * The Webframework routes the HTTP(REST) requests to  the Service Layer. The Service Layer has the main busniness logic for each 
      of the requets ie GET,POST, DELETE. The Service Layer contacts the Data Access Layer for Data persistence and Data retrieval.
      
      
 2) TESTING:
 
   The prerequisites for testing :
    JDK8
    Tomcat(preferably the latest)
    MongoDB
    IntelliJ IDE Community edition(preferably latest)
    
    Buiilding the App:
    
    1) Create new project in IntelliJ from Existing sources by choosing the uploaded source code folder.
    2) Open BirdsApp. Goto  src-->main. Right click on main and Choose "Mark Directory as"--> "Sources Root"
    3) Goto  test-->main. Right click on main and Choose "Mark Directory as"--> "Test Sources Root"
    4) Open pom.xml at the bottom(bottommost file), and click Import Changes on the dialogbox(or pop up) appearing on bottom right.
    5) Once dependencies have been synchronized the project is in buildable state.
    
    Running the Unit test suite(JUNIT):
    1) Right click on the Project folder ie BirdsApp(topmost) and click "Run All Tests"
    2) The UT will run and outputs displayed on Console window.
    
    NOTE:  1) UT has only been written for Utils.
           2) The Services layer needs Jersey UNIT TEST framework and the  latest Maven dependencies for it are conflicting(on my home          system). Hence couldnt write for it on account of limited time and limited value. In My opinion Services later needs more of integration(System) testing.The parts for which UT adds lot of have Has UT.
           

    Running the Integration Tests:
    1) Make Sure Tomcat is installed and NOT RUNNING. Modify tomcat-usees.xml as attached.
    2) Make Sure Mongo DB is installed and running on port 27017.
    3)Goto View->Tool Windows->Maven Projects.
    4) On the RIGHT HAND PANE, goto Plugins->tomcat7->tomcat:run.  The App Runs  with logs on Console.
    5)Install POSTMAN(Community edition).
    6) On the POSTMAN GUI, select Import and Import the 3 collections attached.
    7) Once imported, Press Right Arrow(>) and Select Run Collection.
    8) The test cases run and results are presented.
    
    NOTE: 1) I was not sure about completely automating the tests with the Community Edition
          2) Also some tests may fail(Will) in get by Id and Delete as it depends upon the Object Id in Database and that is hardcoded ans scripting was not possible in that case. Need to make a few manual adjustments.
          3) To create JSOn Body quickly the following site can be used: http://objgen.com/json
          
       
       
    NOTE:
    1) Any problem in configuration can be fixed by  looking up documentation on Internet.
    2) IntelliJ has good online documentation.
    3)https://chiaboy.wordpress.com/2014/07/20/simple-jersey-example-with-intellij-idea-and-tomcat/ is helpful for tomcat.
    
    
    
    
      
      
    
    
  
