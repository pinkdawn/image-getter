/*
 * 从网页上解析图片
 */
package img_getter.img.parser;

import img_getter.Img_getterView;
import img_getter.img.handler.BaseHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 *
 * @author daoyu
 */
public class SwingParser extends BaseParser {

    BaseHandler handler;

    public SwingParser(Img_getterView _view) {
        super(_view);
    }

    /*
     * 解析HTML内容，找到所有的img链接。
     */
    public void parse() {
        InputStream stream = null;
        try {
            stream = new URL(getUrl()).openStream();
            if (stream == null) {
                log("网络错误！");
            }

            ParserDelegator pd = new ParserDelegator();

            pd.parse(new InputStreamReader(stream), (HTMLEditorKit.ParserCallback) getHandler(), true);
        } catch (IOException ioe) {
            log("无法打开网页！");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    log("无法打开网页的连接");
                }
            }
        }
    }

    @Override
    public void setHandler(BaseHandler _handler) {
        handler = _handler;
        handler.reset();
    }

    @Override
    public BaseHandler getHandler() {
        return handler;
    }
}
