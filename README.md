# spring-boot-actuator
things we can do with spring boot actuator

Below things can be done using the spring boot actuator. can see more details by referring the related commit.

- access actuator using */actuator* url
  - can see all the available end point urls
- */actuator/info* url
  - show arbitary data like application name, version etc...
  - these data can be put in applicatoin.properties file
- */actuator/health* url
  - see application status, up or down
  - see various appliction details such as db status
    - these data can be limited to only authorized users who has certain role in the application
  - create a custom group and group necessary data under this group
- secure actuator end points using spring security
- add a custom health indicator to the *health* endpoint
- add a custom actuator end point
- add micrometer and use prometheus to see the data collected by *actuator/prometheus*
- use grafana dashboards to see the data from prometheus
    
