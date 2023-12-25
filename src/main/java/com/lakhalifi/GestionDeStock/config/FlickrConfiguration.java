package com.lakhalifi.GestionDeStock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("flickr.apiSecret")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

    /* @Bean (on a la commenté car la configuration se fait une seule fois)
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr= new Flickr(apiKey, apiSecret, new REST());

        OAuth10aService service= new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));//On a choisit cette permission car elle nous donne toutes les droits: lecture, écriture et suppression

        final Scanner scanner= new Scanner(System.in);

        final OAuth1RequestToken request= service.getRequestToken();

        final String authUrl= service.getAuthorizationUrl(request);

        System.out.println(authUrl);
        System.out.println("Paste it here >> ");

        final String authVerifier= scanner.nextLine();

        OAuth1AccessToken accessToken= service.getAccessToken(request, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth= flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("________________________________");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }*/

    @Bean //pour que dés le demarrage, l'app va automatiquement cree mon bean Flickr
    public Flickr getFlickr(){

        Flickr flickr= new Flickr(apiKey, apiSecret, new REST());

        Auth auth= new Auth();

        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext= RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);

        return flickr;

    }
}
