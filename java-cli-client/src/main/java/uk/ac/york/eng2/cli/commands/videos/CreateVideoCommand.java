package uk.ac.york.eng2.cli.commands.videos;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import picocli.CommandLine;
import uk.ac.york.eng2.cli.dto.VideoDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CommandLine.Command(
        name = "create-video"
)
public class CreateVideoCommand implements Runnable {

    @Inject
    VideoControllerClient client;

    @CommandLine.Parameters(index = "0")
    private Long userId;

    @CommandLine.Parameters(index = "1")
    private String title;

    @CommandLine.Parameters(index = "2..")
    private List<String> tags;

    @Override
    public void run() {
        VideoDTO newVideo = new VideoDTO();
        newVideo.setTitle(title);
        Set<String> tagSet = new HashSet<>(tags);
        newVideo.setTags(tagSet);

        HttpResponse<String> r = client.uploadVideo(userId, newVideo);

        System.out.println("Server responded with; "+r.getStatus()+": "+r.getBody());
    }
}
