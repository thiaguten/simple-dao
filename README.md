# SimpleDAO

[![Build Status](https://travis-ci.org/thiaguten/simple-dao.svg)](https://travis-ci.org/thiaguten/simple-dao)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao/badge.svg)](https://maven-badges.herokuapp.com/maven-central/br.com.thiaguten.persistence/simple-dao)
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/br.com.thiaguten.persistence/simple-dao/badge.svg)](http://www.javadoc.io/doc/br.com.thiaguten.persistence/simple-dao)
[![License](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Dependency Status](https://www.versioneye.com/user/projects/577e7c025bb13900493de577/badge.svg)](https://www.versioneye.com/user/projects/577e7c025bb13900493de577)

Core DAO API that greatly facilitates the usage of multiple persistence providers. E.g: (JPA, Hibernate, Etc...).

SimpleDAO Core Reference Implementation:

```java
class User implements Persistable<Long> {
    ...
}

Persistence<Long, User> userPersistence = new BasePersistence<Long, User>() {
    @Override
    public PersistenceProvider getPersistenceProvider() {
        return new SomePersistenceProviderImpl();
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
    <version>2.0.0</version>
</dependency>
```

To add a dependency using Gradle:

```
dependencies {
    compile 'br.com.thiaguten.persistence:simple-dao:2.0.0'
}
```

For more informations: http://thiaguten.github.io/simple-dao/


