package uk.ac.york.eng2.cli.commands.hashtags;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.Video;

@CommandLine.Command(name="get-tagged-videos")
public class GetTaggedVideosCommand implements Runnable {
    @Inject
    private HashTagsClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        Iterable<Video> videos = client.getTaggedVideos(id);
        if (videos == null){
            System.err.println("Hashtag not found!");
            System.exit(1);
        } else {
            for (Video video:videos){
                System.out.println(video);
            }
        }
    }
}
