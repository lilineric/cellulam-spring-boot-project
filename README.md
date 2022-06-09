# cellulam-spring-boot-project
It depends on Project [cellulam-framework](https://github.com/lilineric/cellulam-framework)

## Quick Start
### Repository
```xml
<repositories>
    <repository>
        <id>github-snapshot</id>
        <url>https://maven.pkg.github.com/lilineric/maven-repo-snapshot</url>
    </repository>
</repositories>
```

### settings.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <mirrors>
        <mirror>
            <id>mirror</id>
            <mirrorOf>!github*</mirrorOf>
            <name>mirror</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
        </mirror>

    </mirrors>
    <servers>
        <server>
            <id>github-snapshot</id>
            <username>lilineric</username>
            <password>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</password>
        </server>
        <server>
            <id>github-release</id>
            <username>lilineric</username>
            <password>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</password>
        </server>
    </servers>
    <profiles>
        <profile>
            <id>nexus</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
                <repository>
                    <id>snapshots</id>
                    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                    <releases>
                        <enabled>false</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
                   <repository>
                        <id>github-snapshot</id>
                        <name>lilineric</name>
                        <url>https://maven.pkg.github.com/lilineric/maven-repo-snapshot</url>
                    </repository>
                    <repository>
                        <id>github-release</id>
                        <name>lilineric</name>
                        <url>https://maven.pkg.github.com/lilineric/maven-repo-release</url>
                    </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>central</id>
                    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </pluginRepository>
                <pluginRepository>
                    <id>snapshots</id>
                    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                    <releases>
                        <enabled>false</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>

    </profiles>

    <activeProfiles>
        <activeProfile>nexus</activeProfile>

    </activeProfiles>


</settings>
```

### Dependency
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
    - [ ] [Transaction Table](https://github.com/lilineric/trans-msg-db)
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
