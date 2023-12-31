package uk.ac.york.eng2.cli.commands.subscription;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(
        name = "unsubscribe"
)
public class UnsubscribeCommand implements Runnable {

    @Inject
    private SubscriptionControllerClient client;

    @CommandLine.Parameters(index = "0")
    private String hashtag;

    @CommandLine.Parameters(index = "1")
    private Long userId;

    @Override
    public void run() {
        HttpResponse<String> r = client.unsubscribeHashtag(hashtag, userId);
        System.out.println("Server responded with; "+r.getStatus()+": "+r.body());
    }
}
