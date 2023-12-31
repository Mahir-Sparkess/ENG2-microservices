package uk.ac.york.eng2.cli.commands.subscription;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.Video;

@CommandLine.Command(name = "get-subscription")
public class GetSubscription implements Runnable {
    @Inject
    SubscriptionControllerClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        Iterable<Video> videos = client.getSubscription(id);
        for (Video v : videos){
            System.out.println(v);
        }
    }
}
