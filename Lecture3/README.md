


# Testing and project management
- Junit5 intro: https://programmingtechie.com/2020/12/26/junit-5-complete-tutorial/

1. Create Maven project
2. Implement function that finds all anagrams in a string list
3. Write JUnit tests for this function
4. Compile, test and install project using Maven

# Exam questions

1. Describe difference between namespace, module and service 
    - Service >> module >> package
    - Namespaces enables split codes to logically realated parts and to avoid name collisions
    - Modules enables split project to realated parts that can be shared with other projects (libraries, etc.)
    - Service is usually a collection of modules that provides certain functionality via API
2. Describe Maven POM
    - POM is a XML file that describes the project and its dependencies
    - It is used to build project and to deploy it to a repository
    - Contains information about project (name, group, version), dependencies, modules etc.
3. What is Super POM
    - Super POM is a default POM for ceratin Maven version that other POMs inherit from.
4. Describe Maven build lifecycle
    - Livecycle is a sequence of stages that are executed during the build process
    - For example default lifecycle looks like this:
        - <b>validate</b> - validate the project is correct and all necessary information is available
        - <b>compile</b> - compile the source code of the project
        - <b>test</b> - test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
        - <b>package</b> - take the compiled code and package it in its distributable format, such as a JAR.
        - <b>verify</b> - run any checks on results of integration tests to ensure quality criteria are met
        - <b>install</b> - install the package into the local repository, for use as a dependency in other projects locally
        - <b>deploy</b> - done in the build environment, copies the final package to the remote repository for sharing with other developers and projects.
5. Describe Maven goals
    - Goals that are asociated with stages of the build lifecycle
    - For example:
        - jar and war goals are used to create JAR and WAR files in package phase.
        - install is bound to install phase ... 
6. How are project dependencies managed by Maven
    - You add dependencies for your project to your Maven configuration file (also known as the  pom.xml file, for Project Object Model). As you build your project using Maven, it resolves these dependencies and downloads the dependencies to your local repository folder. This folder is usually located in your user’s home folder and is named .m2. Each dependency downloaded from the repository is a project itself, and has its own dependencies. Maven recursively resolves all of these dependencies for you, and then merges shared dependencies and downloads them.  At the end of the process you end up with a list of dependencies that are needed to run your project on your local machine.

