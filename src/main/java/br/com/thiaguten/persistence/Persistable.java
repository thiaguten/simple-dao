package br.com.thiaguten.persistence;

import java.io.Serializable;

public interface Persistable<PK extends Serializable> extends Identificable<PK>, Serializable {

}
