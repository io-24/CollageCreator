package collage;

import collage.controller.CustomBackOff;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

/**
 * Created by Ruslan on 21.12.2015.
 */

@Controller
public class CheckSiteStatusController {

    private static final HttpRequestFactory WEB_CLIENT;

    static {
        WEB_CLIENT = new NetHttpTransport().createRequestFactory(new HttpRequestInitializer() {
            @Override
            public void initialize(final HttpRequest request) {
                request.setThrowExceptionOnExecuteError(false);
                request.setReadTimeout(45000);
                request.setConnectTimeout(45000);
            }
        });
    }

    public boolean checkSiteStatus(String str_url){
        try {
            HttpRequest request = WEB_CLIENT.buildGetRequest(new GenericUrl(str_url));
            configure(request);
            final HttpResponse response = request.execute();
            Logger.getGlobal().info(String.valueOf(response.getStatusCode()));
            return  response.getStatusCode() == 200;
        } catch (Exception e){
            return false;
        }
    }

    protected void configure(final HttpRequest request) {
        request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new CustomBackOff()));
    }
}
