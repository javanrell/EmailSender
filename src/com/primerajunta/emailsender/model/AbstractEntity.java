package com.primerajunta.emailsender.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class AbstractEntity implements Entity {

    @Id
    @GeneratedValue
    protected Integer id;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
