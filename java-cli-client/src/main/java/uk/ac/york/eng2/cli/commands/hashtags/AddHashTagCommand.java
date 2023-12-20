package uk.ac.york.eng2.cli.commands.hashtags;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.HashTagDTO;

@CommandLine.Command(name="add-hashtag")
public class AddHashTagCommand implements Runnable{
    @Inject
    private HashTagsClient client;

    @CommandLine.Parameters(index = "0")
    private String name;

    @Override
    public void run() {
        HashTagDTO tag = new HashTagDTO();
        tag.setName(name);
        HttpResponse<Void> response = client.createHashTag(tag);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
