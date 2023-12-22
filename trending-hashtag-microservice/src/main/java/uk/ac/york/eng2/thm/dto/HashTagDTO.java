package uk.ac.york.eng2.thm.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HashTagDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
