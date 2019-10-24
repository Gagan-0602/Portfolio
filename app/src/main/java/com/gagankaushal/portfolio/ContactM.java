package com.gagankaushal.portfolio;

import java.io.Serializable;

public class ContactM implements Serializable {
    String id;
    String subject;
    String message;

    public ContactM(String id,String subject,String message)
    {
       this.id=id;
       this.subject=subject;
       this.message=message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
