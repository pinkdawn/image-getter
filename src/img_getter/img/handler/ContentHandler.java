package img_getter.img.handler;

import img_getter.img.parser.BaseParser;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author daoyu
 */
public class ContentHandler extends BaseHandlerImpl {

    /*
     * Read the inputstream from URL, one line per time. Then process it to get the
     * img URLs.
     * TODO to resolve img URL that needs more than 1 line.
     * @parameter br the buffered reader that wrap the URL's open stream.
     */
    public void process(BufferedReader br) throws IOException {
        String temp = br.readLine();
        String protocol = "http";
        while (temp != null) {
            for (String format : BaseParser.IMAGE_FORMATS) {
                String lowerTemp = temp.toLowerCase();
                if (lowerTemp.contains(format)) {
                    int startIndex = lowerTemp.indexOf(format);
                    int endIndex = -1;
                    while (startIndex > 0) {
                        while (true) { // 获得离 .jpg 最近的 http的 index。
                            int tempIndex = lowerTemp.indexOf(protocol, endIndex == -1 ? 0 : endIndex + 1);
                            if (tempIndex < 0 || tempIndex > startIndex) {
                                break;
                            } else {
                                endIndex = tempIndex;
                            }
                        }

                        if (endIndex >= 0 && endIndex < startIndex) { // 拼接成一个img地址串。
                            String _img = temp.substring(endIndex, startIndex + format.length());
                            addExtImg(_img);
                        }
                        
                        startIndex = lowerTemp.indexOf(format, startIndex + 1); //获取本行下一个 .jpg
                    }
                }
            }

            temp = br.readLine();
        }
    }
}
