# Section 9: Appendix Introduction to Spring Framework in 10 Steps

## 194*:

    - @Component
    - @AutoRewrite 
    - using spring boot create.bean to create an instance of a class

## 195:

    - Enabling debugger in the logging
    - Only one component should be declared in order to spring create a bean

## 196:

    - A component is a class managed by spring
    - Spring FW finds a component by searcing the @component tags
    in the current package
    - IOC: Inversion of control. Means the spring FW is in control not the developer.

## 198:

    - Making a bean primary by adding @Primary annotation

## 199:

    - Maven manages all the dependencies in the java project
    - Using pom.xml the dependency can be changed

## 200: types of spring dependencies

    - constrator-based: in GameRunner class
    - Field based: In the MyWebController using @Autowire
    - Setter dependency: In Business service using setter for DataService
    - Its recommended to use Constrator-Based so all the dependencies can be available just by creating the obj


