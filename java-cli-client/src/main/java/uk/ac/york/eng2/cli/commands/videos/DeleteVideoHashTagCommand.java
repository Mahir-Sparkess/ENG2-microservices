package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        name="delete-video-tag"
)
public class DeleteVideoHashTagCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index = "0")
    private Long videoId;

    @CommandLine.Parameters(index = "1")
    private Long tagId;

    @Override
    public void run() {
        HttpResponse<String> response = client.removeTag(videoId, tagId);
        System.out.printf("Server responded with status %s: %s%n",
                response.getStatus(), response.getBody().orElse("(no text)"));
    }
}