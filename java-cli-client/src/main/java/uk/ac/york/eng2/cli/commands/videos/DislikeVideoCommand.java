package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        name="dislike-video"
)
public class DislikeVideoCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index="0")
    private Long videoId;

    @Override
    public void run() {
        HttpResponse<Void> response = client.dislikeVideo(videoId);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
