package uk.ac.york.eng2.cli.commands.videos;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.HashTag;

@CommandLine.Command(
        name="get-video-tags"
)
public class GetVideoTagsCommand implements Runnable{

    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;
    
    @Override
    public void run() {
        Iterable<HashTag> tags = client.getTags(id);
        if (tags == null) {
            System.err.println("Video not found!");
            System.exit(1);
        } else {
            for (HashTag tag : tags) {
                System.out.println(tag);
            }
        }
    }
}
