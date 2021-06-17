# Javacord springboot template project

This project was create for people to quickly set up a discordbot. The project has javacord, jpa, hamcrest (testing) and
H2 as default database.

# Getting Started

Things to do after cloning the project.

- Rename the folder com.example.springbootjavacord to: com."yourname"."botname" in intellij use shift+f6 and choose
  rename whole package
- Do the same in the pom.xml

```xml
<groupId>com."yourname"</groupId>
<artifactId>"botname"</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>"botname"</name>
<description>"description of bot"t</description>
```

- In the logback.xml find "example" and give your logs a proper name
- In the application properties set your token, change the db file name or use a real database
- The project has example classes to show how it works.

By default the project is set for java 16. In the pom.xml file you can set it to java 11 with the xml below

```xml
<configuration>
    <source>11</source>
    <target>11</target>
</configuration>
```

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#using-boot-devtools)
* [JavaCord](https://github.com/Javacord/Javacord)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

