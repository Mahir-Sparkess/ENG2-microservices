package uk.ac.york.eng2.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Video {

    private Long id;

    private String title;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
