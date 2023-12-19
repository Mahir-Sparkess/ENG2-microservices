package uk.ac.york.eng2.cli.commands.hashtags;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.HashTagDTO;

@CommandLine.Command(name="update-hashtag")
public class UpdateHashTagCommand implements Runnable {
    @Inject
    private HashTagsClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @CommandLine.Option(names = {"-n", "--name"})
    private String name;
    @Override
    public void run() {
        HashTagDTO tag = new HashTagDTO();
        if (name != null){
            tag.setName(name);
        }
        HttpResponse<Void> response = client.updateHashTag(id, tag);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
