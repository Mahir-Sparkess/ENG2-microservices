package uk.ac.york.eng2.cli.commands.trending;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.TrendingTag;

@CommandLine.Command(name = "get-trending")
public class GetTrendingCommand implements Runnable{
    @Inject
    TrendingClient client;
    @Override
    public void run() {
        Iterable<TrendingTag> tags = client.trending();
        for (TrendingTag t : tags) {
            System.out.println(t);
        }
    }
}
