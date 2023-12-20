package uk.ac.york.eng2.cli.commands.videos;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.Video;

@CommandLine.Command(
        name = "get-videos"
)
public class GetVideosCommand implements Runnable {

    @Inject
    private VideosClient client;

    @Override
    public void run() {
        for (Video v : client.list()) {
            System.out.println(v);
        }
    }
}
