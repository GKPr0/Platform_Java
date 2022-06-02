

# Spring Intro

1. Create Spring project using Maven
2. Change autowired dependency based on configuration variable (e.g. profile)
3. Write JUnit tests for autowired dependency
4. Compile, test and install project using Maven

# Exam questions

1. <b>Describe dependency injection and inversion of control</b>
- <b>Inversion of Control (IoC)</b>
   - Is a software design pattern that enables loose coupling between objects.
   - IoC container creates the objects, configures and assembles their dependencies, manages their entire life cycle.
   - Since the Controlling of Java objects and their lifecycle is not done by the developers, hence the name Inversion Of Control
- <b>Dependency Injection (DI)</b>
   - Is the main functionality provided by Spring IOC
   - It provides a way to inject dependencies into objects at runtime.
2. <b>Describe Spring application container</b>
   - Appliaction container is IoC conatinaer that is used to create and manage Spring application context
3. <b>Describe annotations</b>
   1. @Autowired - marks a constructor, setter method, or field as to be automatically injected by Spring
   2. @Component - marks a class as a Bean that will be managed by IoC container
   3. @Configuration - marks a class as a configuration class
   4. @Qualifier - Specifies the name of the bean. Usefull when there are multiple beans of same type.
   5. @ComponentScan - use to scan for components in specicifc pakage and its subpackages. Use together with configuration class
4. <b>What does bean represent in Spring</b>
   - The objects that form the backbone of application and that are managed by the Spring IoC container
5. <b>Describe difference between Singleton and Prototype bean scope</b>
   - Singleton: bean is created only once and is shared between all requests
   - Prototype: bean is created for each request
6. <b>Describe bean lifecycle events</b>
   - Bean lifecycle events are:
       - Bean created
       - Bean injected
       - Bean initialized (user can override this method init() in bean class)
       - Bean destroyed (user can override this method destroy() in bean class)
