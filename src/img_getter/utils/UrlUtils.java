package img_getter.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
    public static final String[] IMAGE_FORMATS = {".jpg", ".png", ".bmp", ".gif"};
    public static final String[] WEB_PROTOCOLS = {"http://", "https://", "ftp://"};

    public static String getAbsoluteUrl(String link) {
        link = link.toLowerCase();
        for (String s : WEB_PROTOCOLS) {
            link = link.replaceAll(s, "");
        }
        for (String s : IMAGE_FORMATS) {
            link = link.replaceAll(s, "");
        }

        return link;
    }

    public static String getAbsolutePath(String input){
        try {
            URL u = new URL(input);
            return String.format("%s://%s/", u.getProtocol(), u.getHost());
        } catch (MalformedURLException ex) {
            return "";
        }
    }
}
