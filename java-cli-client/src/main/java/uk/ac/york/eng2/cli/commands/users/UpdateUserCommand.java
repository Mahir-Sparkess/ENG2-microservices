package uk.ac.york.eng2.cli.commands.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.UserDTO;

@CommandLine.Command(name="update-user")
public class UpdateUserCommand implements Runnable{
    @Inject
    private UsersClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @CommandLine.Option(names = {"-u", "--username"})
    private String username;

    @Override
    public void run() {
        UserDTO user = new UserDTO();
        if (username != null){
            user.setUsername(username);
        }
        HttpResponse<Void> response = client.updateUser(id, user);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
