package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.VideoDTO;

@CommandLine.Command(
        name="add-video"
)
public class AddVideoCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index="0")
    private String title;

    @Override
    public void run() {
        VideoDTO video = new VideoDTO();
        video.setTitle(title);

        HttpResponse<Void> response = client.addVideo(video);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
