import cz.tul.client.ImageStatus;
import cz.tul.client.ServerApi;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataTest {

    private final String TEST_URL = "http://localhost:8080";

    private File testImageData = new File("src/test/resources/sychrov.jpg");
    private String filename = "sychrov";

    private ServerApi insecureService = new RestAdapter.Builder()
            .setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build().create(ServerApi.class);

    @Test
    public void UploadAndDownloadData() throws Exception {


        ImageStatus status = insecureService.uploadImage(filename, new TypedFile("image/jpeg", testImageData));
        assertEquals(ImageStatus.ImageState.READY, status.getState());

        Response response = insecureService.downloadImage(filename);
        assertEquals(200, response.getStatus());

        InputStream imageData = response.getBody().in();
        byte[] originalFile = IOUtils.toByteArray(new FileInputStream(testImageData));
        byte[] retrievedData = IOUtils.toByteArray(imageData);
        assertTrue(Arrays.equals(originalFile, retrievedData));
    }
}


