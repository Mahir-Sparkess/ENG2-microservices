package uk.ac.york.eng2.cli.commands.hashtags;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.HashTagDTO;

@CommandLine.Command(name = "get-hashtag")
public class GetHashTagCommand implements Runnable{
    @Inject
    private HashTagsClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;
    @Override
    public void run() {
        HashTagDTO tag = client.getHashTag(id);
        if (tag == null) {
            System.err.println("Hashtag not found!");
            System.exit(1);
        } else {
            System.out.println(tag);
        }
    }
}
