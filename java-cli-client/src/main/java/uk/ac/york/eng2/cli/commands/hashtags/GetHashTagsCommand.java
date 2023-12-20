package uk.ac.york.eng2.cli.commands.hashtags;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.HashTag;

@CommandLine.Command(name="get-hashtags")
public class GetHashTagsCommand implements Runnable{
    @Inject
    private HashTagsClient client;

    @Override
    public void run() {
        Iterable<HashTag> tags = client.list();
        for (HashTag tag : tags) {
            System.out.println(tag);
        }
    }
}
