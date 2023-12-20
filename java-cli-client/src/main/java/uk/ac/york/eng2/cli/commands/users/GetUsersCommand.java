package uk.ac.york.eng2.cli.commands.users;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.User;

@CommandLine.Command(
        name="get-users"
)
public class GetUsersCommand implements Runnable{
    @Inject
    private UsersClient client;

    @Override
    public void run() {
        Iterable<User> users = client.list();
        for (User user: users) {
            System.out.println(user);
        }
    }
}
