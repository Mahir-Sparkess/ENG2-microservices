package uk.ac.york.eng2.cli;

import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import uk.ac.york.eng2.cli.commands.hashtags.*;
import uk.ac.york.eng2.cli.commands.users.*;
import uk.ac.york.eng2.cli.commands.videos.*;

@Command(name = "java-cli-client", description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {
                //Videos Commands
                GetVideosCommand.class,
                GetVideoCommand.class,
                GetVideoTagsCommand.class,
                GetViewersCommand.class,
                AddVideoCommand.class,
                AddVideoHashTagCommand.class,
                AddVideoViewerCommand.class,
                UpdateVideoCommand.class,
                DeleteVideoCommand.class,
                DeleteViewerCommand.class,
                DeleteVideoHashTagCommand.class,
                LikeVideoCommand.class,
                DislikeVideoCommand.class,
                //Users Commands
                AddUserCommand.class,
                DeleteUserCommand.class,
                GetUsersCommand.class,
                GetUserCommand.class,
                GetViewedCommand.class,
                UpdateUserCommand.class,
                //Hashtags Commands
                AddHashTagCommand.class,
                DeleteHashTagCommand.class,
                GetHashTagsCommand.class,
                GetHashTagCommand.class,
                GetTaggedVideosCommand.class,
                UpdateHashTagCommand.class
        })
public class JavaCliClientCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(JavaCliClientCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
