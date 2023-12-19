package uk.ac.york.eng2.cli.commands.users;

import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.UserDTO;

@CommandLine.Command(
        name = "get-user"
)
public class GetUserCommand implements Runnable{

    @Inject
    private UsersClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        UserDTO user = client.getUser(id);
        if (user == null) {
            System.err.println("User not found!");
            System.exit(1);
        } else {
            System.out.println(user);
        }
    }
}
