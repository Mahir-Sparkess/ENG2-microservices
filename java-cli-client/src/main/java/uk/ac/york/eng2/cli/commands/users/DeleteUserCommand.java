package uk.ac.york.eng2.cli.commands.users;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(name="delete-user")
public class DeleteUserCommand implements Runnable {
    @Inject
    private UsersClient client;

    @CommandLine.Parameters(index = "0")
    private Long id;

    @Override
    public void run() {
        HttpResponse<Void> response = client.deleteUser(id);
        System.out.println("Server responded with: " + response.getStatus());
    }
}
