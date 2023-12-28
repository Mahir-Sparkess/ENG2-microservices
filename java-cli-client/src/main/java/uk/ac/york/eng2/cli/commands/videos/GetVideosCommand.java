package uk.ac.york.eng2.cli.commands.videos;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.Video;

@CommandLine.Command(
        name = "get-videos"
)
public class GetVideosCommand implements Runnable {

    @Inject
    private VideoControllerClient client;

    @Override
    public void run() {
        for (Video v : client.getVideos()) {
            System.out.println(v);
        }
    }
}
