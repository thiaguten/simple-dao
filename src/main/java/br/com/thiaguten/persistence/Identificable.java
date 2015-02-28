package br.com.thiaguten.persistence;

import java.io.Serializable;

public interface Identificable<PK extends Serializable> {

    PK getId();

}
