package uk.ac.york.eng2.cli.commands.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.UserDTO;

@CommandLine.Command(
        name = "create-user"
)
public class CreateUserCommand implements Runnable {

    @Inject
    private UserControllerClient client;

    @CommandLine.Parameters(index = "0")
    private String username;

    @Override
    public void run() {
        UserDTO u = new UserDTO();
        u.setUsername(username);
        HttpResponse<String> r = client.addUser(u);
        System.out.println("Server responded with; "+r.getStatus()+": "+r.body());
    }
}
