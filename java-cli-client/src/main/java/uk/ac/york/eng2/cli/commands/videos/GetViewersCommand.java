package uk.ac.york.eng2.cli.commands.videos;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.User;

@CommandLine.Command(
        name = "get-viewers"
)
public class GetViewersCommand implements Runnable {
    @Inject
    private VideosClient client;

    @CommandLine.Parameters(index="0")
    private Long id;

    @Override
    public void run() {
        Iterable<User> viewers = client.getViewers(id);
        if (viewers == null) {
            System.err.println("Video not found!");
            System.exit(1);
        } else {
            for (User user : viewers) {
                System.out.println(user);
            }
        }
    }
}
