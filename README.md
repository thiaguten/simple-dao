# SimpleDAO

[![Build Status](https://travis-ci.org/thiaguten/simple-dao.svg)](https://travis-ci.org/thiaguten/simple-dao)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao/badge.svg)](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao)

DAO design pattern API that greatly facilitates the exchange and use of multiple persistence providers, like (JPA, Hibernate) in the same project.

In test package was implemented two demo examples using the SimpleDAO API. A demonstration where transactions are manually controlled using (beginTransaction, commitTransaction, rollbackTransaction, etc) and another using the SpringFramework using (@Transacional).

Requires JDK 1.6 or higher

## Latest release

SimpleDAO is available from [Maven Central](http://search.maven.org/).

To add a dependency using Maven, use the following:

```xml
<dependency>
    <groupId>br.com.thiaguten.persistence</groupId>
    <artifactId>simple-dao</artifactId>
    <version>1.0.1</version>
</dependency>
```

To add a dependency using Gradle:

```
dependencies {
    compile 'br.com.thiaguten.persistence:simple-dao:1.0.1'
}
```


