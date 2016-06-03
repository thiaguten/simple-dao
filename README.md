# SimpleDAO

[![Build Status](https://travis-ci.org/thiaguten/simple-dao.svg)](https://travis-ci.org/thiaguten/simple-dao)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao/badge.svg)](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao)

Core DAO API that greatly facilitates the usage of multiple persistence providers. E.g: (JPA, Hibernate, Etc...).

SimpleDAO Core Reference Implementation:

```java
User user = new User("Thiago Gutenberg");

BasePersistence<Long, User> userPersistence = new GenericBasePersistence<Long, User>() {
    @Override
    public PersistenceProvider getPersistenceProvider() {
        return new YourPersistenceProviderImpl();
    }
};

userPersistence.create(user);
userPersistence.update(user);
userPersistence.delete(user);
...
```

More detail example can be found in test package.

SimpleDAO SPI Implementations:

- [SimpleDAO JPA](https://github.com/thiaguten/simple-dao-jpa).
- [SimpleDAO Hibernate](https://github.com/thiaguten/simple-dao-hibernate).

Requires JDK 1.6 or higher

## Latest release

SimpleDAO is available from [Maven Central](http://search.maven.org/).

SimpleDAO Javadoc is available [here](http://thiaguten.github.io/simple-dao/apidocs/).

To add a dependency using Maven, use the following:

```xml
<dependency>
    <groupId>br.com.thiaguten.persistence</groupId>
    <artifactId>simple-dao</artifactId>
    <version>1.0.3</version>
</dependency>
```

To add a dependency using Gradle:

```
dependencies {
    compile 'br.com.thiaguten.persistence:simple-dao:1.0.3'
}
```

For more informations: http://thiaguten.github.io/simple-dao/


