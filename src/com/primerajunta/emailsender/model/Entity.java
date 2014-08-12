package com.primerajunta.emailsender.model;

public interface Entity {
	
    Integer getId();

    void setId(Integer id);
    
    Class<? extends Entity> getEntityClass();

}
