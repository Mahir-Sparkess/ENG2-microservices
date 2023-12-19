package uk.ac.york.eng2.cli.commands.videos;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.VideoDTO;

@CommandLine.Command(
        name = "get-video"
)
public class GetVideoCommand implements Runnable{

    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        VideoDTO video = client.getVideo(id);
        if (video == null) {
            System.err.println("Video not found!");
            System.exit(1);
        } else {
            System.out.println(video);
        }
    }
}
