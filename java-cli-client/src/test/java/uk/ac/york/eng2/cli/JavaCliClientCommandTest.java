package uk.ac.york.eng2.cli;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.cli.commands.users.CreateUserCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaCliClientCommandTest {

    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
            String[] args = new String[] { "-v" };
            PicocliRunner.run(JavaCliClientCommand.class, ctx, args);

            // java-cli-client
            assertTrue(baos.toString().contains("Hi!"));
        }
    }
}
