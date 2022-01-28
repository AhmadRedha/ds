package ir.ds.api;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ir.ds.obj.Template;
import ir.ds.obj.bg.BgColor;
import ir.ds.obj.resource.Direction;
import ir.ds.obj.resource.RssResource;
import ir.ds.obj.resource.RssText;
import ir.ds.obj.tile.Border;
import ir.ds.obj.tile.Design;
import ir.ds.obj.tile.Position;
import ir.ds.obj.tile.Tile;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ahmad R. Nazemi
 * Date: 27/01/2022
 */
@RestController
public class TestApi {
    static SyndFeed feed;

    static{

        Request request = new Request.Builder()
                .url("https://www.farsnews.ir/rss")
                .build();

        //File cacheDirectory = new File("src/test/resources/cache");
       // Cache cache = new Cache(cacheDirectory, cacheSize);

        OkHttpClient client =getUnsafeOkHttpClient();

        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            SyndFeedInput input = new SyndFeedInput();
             feed = input.build(new XmlReader(response.body().byteStream()));
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }



    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/")
    Template template() {
        Position position = Position.builder().top(45).left(0).width(100).height(10).build();
        BgColor bgColor = BgColor.builder().color("rgba(37, 139, 167, 0.46)").build();
        Border border = Border.builder().width(1).round(5).color("rgba(0, 0, 0, 0.72)").build();
        Design design = Design.builder().bg(bgColor)
                .border(border).build();
        RssResource res = RssResource.builder().
                color("rgba(0, 85, 255, 0.72)").
                direction(Direction.RTL).speed(1).
                font("http://127.0.0.1:9091/BYekan.ttf").
                icon("http://127.0.0.1:9091/download.png").texts(new ArrayList<RssText>()).
                build();
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            res.addToRss(entry.getTitle());
        }
        Tile tile = Tile.builder().design(design).resource(res).position(position).build();
        return Template.builder().tile(tile).build();
    }

    // end::get-aggregate-root[]
















    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
