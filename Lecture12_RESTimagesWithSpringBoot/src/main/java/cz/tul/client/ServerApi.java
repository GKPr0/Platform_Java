package cz.tul.client;

import retrofit.client.Response;
import retrofit.http.*;
import retrofit.mime.TypedFile;

public interface ServerApi {

    public static final String DOWNLOAD_PATH = "/download/{name}";
    public static final String UPLOAD_PATH = "/upload/{name}";

    @Multipart
    @POST(UPLOAD_PATH)
    public ImageStatus uploadImage(@Path("name") String name, @Part("data") TypedFile imageData);

    @Streaming
    @GET(DOWNLOAD_PATH)
    Response downloadImage(@Path("name") String name);
}
