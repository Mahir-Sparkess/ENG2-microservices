package uk.ac.york.eng2.cli.commands.hashtags;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.HashTagDTO;

@CommandLine.Command(name = "delete-hashtag")
public class DeleteHashTagCommand implements Runnable {
    @Inject
    private HashTagsClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        HttpResponse<Void> response = client.deleteHashTag(id);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
