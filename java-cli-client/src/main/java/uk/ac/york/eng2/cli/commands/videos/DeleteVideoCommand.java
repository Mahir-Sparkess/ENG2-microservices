package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        name="delete-video"
)
public class DeleteVideoCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        HttpResponse<Void> response = client.deleteVideo(id);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
