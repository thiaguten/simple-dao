# SimpleDAO

[![Build Status](https://travis-ci.org/thiaguten/simple-dao.svg)](https://travis-ci.org/thiaguten/simple-dao)
[![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22br.com.thiaguten.persistence%22%20AND%20a%3A%22simple-dao%22)
[![Javadocs](http://www.javadoc.io/badge/br.com.thiaguten.persistence/simple-dao.svg)](http://www.javadoc.io/doc/br.com.thiaguten.persistence/simple-dao)
[![License](https://img.shields.io/badge/license-apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)
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
