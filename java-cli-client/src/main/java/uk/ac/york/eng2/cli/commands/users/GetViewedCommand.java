package uk.ac.york.eng2.cli.commands.users;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.User;
import uk.ac.york.eng2.cli.domain.Video;

@CommandLine.Command(
        name="get-user-views"
)
public class GetViewedCommand implements Runnable {
    @Inject
    private UsersClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        Iterable<Video> videos = client.getViewed(id);
        if (videos == null) {
            System.err.println("User not found!");
            System.exit(1);
        } else {
            for (Video video : videos){
                System.out.println(video);
            }
        }
    }
}
