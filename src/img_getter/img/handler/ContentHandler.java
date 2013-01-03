package img_getter.img.handler;

import img_getter.img.parser.BaseParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentHandler extends BaseHandlerImpl {

    static String join(String[] s, String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            builder.append(s[i]);

            if (i != s.length - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    static final String reStr = String.format("((http|/)[:\\w-\\./\\?%s&=]*\\.(%s))", "%", join(BaseParser.IMAGE_FORMATS, "|").replace(".", ""));
    static Pattern re = Pattern.compile(reStr);

    /*
     * Read the inputstream from URL, one line per time. Then process it to get the
     * img URLs.
     * TODO to resolve img URL that needs more than 1 line.
     * @parameter br the buffered reader that wrap the URL's open stream.
     */
    public void process(BufferedReader br) throws IOException {
        String temp = br.readLine();
        while (temp != null) {
            Matcher m = re.matcher(temp);
            while (m.find()) {
                addImgLink(m.group(0));
            }

            temp = br.readLine();
        }
    }

    public static void main(String args[]) {
        System.out.println(reStr);
//        string s = "https://itunes.apple.com/us/app/diapers.com/id422304830?mt=8&uo=4'); _gaq.push(['_trackEvent', 'InternalCampaign', 'D-Promo-MobileApp', 'NVHP']); })(); void(0);\" ><img src=\"/images/banners/mobileshop/nvhp_freeiphoneapp.gif\"";
        String s = "http://diapers.suryani.cn/\"/Images/HomePage/NVHP_758-60.jpg";
        Matcher m = re.matcher(s);
        while (m.find()) {
            System.out.println(m.group(0));
        }
    }
}
