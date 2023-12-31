package uk.ac.york.eng2.cli;

import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import uk.ac.york.eng2.cli.commands.hashtags.*;
import uk.ac.york.eng2.cli.commands.hrm.GetRecommendationCommand;
import uk.ac.york.eng2.cli.commands.subscription.GetSubscription;
import uk.ac.york.eng2.cli.commands.subscription.SubscribeCommand;
import uk.ac.york.eng2.cli.commands.subscription.UnsubscribeCommand;
import uk.ac.york.eng2.cli.commands.trending.GetTrendingCommand;
import uk.ac.york.eng2.cli.commands.users.*;
import uk.ac.york.eng2.cli.commands.videos.*;

@Command(name = "java-cli-client", description = "...",
        mixinStandardHelpOptions = true,
        subcommands = {
                // Video Microservice commands
                CreateVideoCommand.class,
                ViewVideoCommand.class,
                LikeVideoCommand.class,
                DislikeVideoCommand.class,
                CreateUserCommand.class,
                // Trending Hashtag Microservice commands
                GetTrendingCommand.class,
                // Subscription Microservice commands
                GetSubscription.class,
                SubscribeCommand.class,
                UnsubscribeCommand.class,
                // Recommendation Microservice Commands
                GetRecommendationCommand.class
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
