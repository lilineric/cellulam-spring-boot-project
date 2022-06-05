# cellulam-spring-boot-project
It depends on Project [cellulam-framework](https://github.com/lilineric/cellulam-framework)

## Quick Start
```xml
<dependency>
    <groupId>com.cellulam.framework</groupId>
    <artifactId>cellulam-spring-boot-starter</artifactId>
    <version>${cellulam.spring.version}</version>
</dependency>
```

## TODO
- [x] Distributed UID
- [x] Multiple DataSource
- [x] Sharding DataSource  
- [ ] Distributed Transaction
    - [ ] TCC
    - [ ] Saga
    - [ ] Transaction Table
    - [ ] Transaction Message


## Notes
### cellulam-spring-db-sharding
- In some projects table sharding is not necessary, so cellulam-spring-boot-starter does not integrate cellulam-spring-db-sharding by default.
- If you need the sharding ability, need to import cellulam-spring-db-sharding: 
```xml
<dependency>
    <groupId>com.cellulam.framework</groupId>
    <artifactId>cellulam-spring-db-sharding</artifactId>
    <version>${cellulam.spring.version}</version>
</dependency>
```
Disable the RoutingDataSourceAutoConfiguration:
```properties
spring.cellulam.routing-datasource.enabled=false
```