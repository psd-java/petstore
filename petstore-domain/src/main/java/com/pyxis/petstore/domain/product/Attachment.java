package com.pyxis.petstore.domain.product;

import javax.persistence.Embeddable;

@Embeddable
public class Attachment {

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public String toString() {
        return fileName;
    }
}
