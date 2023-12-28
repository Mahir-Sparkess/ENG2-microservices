package uk.ac.york.eng2.vm.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.vm.gen.dto.VideoDTO;

import java.util.Set;

@Serdeable
public class VideoDTOExt extends VideoDTO {

    private Iterable<String> tags;

    public Iterable<String> getTags() {
        return tags;
    }

    public void setTags(Iterable<String> tags) {
        this.tags = tags;
    }
}
