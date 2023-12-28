package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(name = "dislike-video")
public class DislikeVideoCommand implements Runnable {

    @Inject
    VideoControllerClient client;

    @CommandLine.Parameters(index = "0")
    private Long videoId;

    @CommandLine.Parameters(index = "1")
    private Long userId;

    @Override
    public void run() {
        HttpResponse<String> r = client.dislikeVideo(videoId, userId);
        System.out.println("Server responded with; "+r.getStatus()+": "+r.getBody());
    }
}
