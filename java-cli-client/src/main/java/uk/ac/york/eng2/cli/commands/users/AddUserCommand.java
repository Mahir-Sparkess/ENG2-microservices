package uk.ac.york.eng2.cli.commands.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.UserDTO;

@CommandLine.Command(name="add-user")
public class AddUserCommand implements Runnable{
    @Inject
    private UsersClient client;

    @CommandLine.Parameters(index = "0")
    private String username;

    @Override
    public void run() {
        UserDTO user = new UserDTO();
        user.setUsername(username);

        HttpResponse<Void> response = client.addUser(user);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
