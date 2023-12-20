package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.VideoDTO;

@CommandLine.Command(
        name="update-video"
)
public class UpdateVideoCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index="0")
    private Long id;

    @CommandLine.Option(names = {"-t", "--title"})
    private String title;

    @Override
    public void run() {
        VideoDTO video = new VideoDTO();
        if (title != null) {
            video.setTitle(title);
        }

        HttpResponse<Void> response = client.updateVideo(id, video);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
