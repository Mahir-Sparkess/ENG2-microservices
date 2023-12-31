package uk.ac.york.eng2.cli.commands.hrm;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.HashTag;
import uk.ac.york.eng2.cli.domain.TrendingTag;

@CommandLine.Command(name = "get-rec")
public class GetRecommendationCommand implements Runnable{
    @Inject
    RecommendationControllerClient client;

    @CommandLine.Parameters(index = "0")
    private String hashtag;

    @Override
    public void run(){
        Iterable<HashTag> tags = client.getRecommendation(hashtag);
        for (HashTag t : tags) {
            System.out.println(t);
        }
    }
}
