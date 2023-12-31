package uk.ac.york.eng2.cli.commands.users;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.domain.User;

@CommandLine.Command(
        name = "get-users"
)
public class GetUsersCommand implements Runnable {

    @Inject
    private UserControllerClient client;

    @Override
    public void run() {
        for (User u :  client.getUsers()){
            System.out.println(u);
        }
    }
}
