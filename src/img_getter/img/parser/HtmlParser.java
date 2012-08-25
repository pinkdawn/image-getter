/*
 * 使用org.htmlparser提供的解析器，可以解析非标准的HTML和xhtml。速度较慢。
 */
package img_getter.img.parser;

import img_getter.Img_getterView;
import img_getter.img.handler.BaseHandler;
import img_getter.img.handler.HtmlHandler;
import org.htmlparser.util.ParserException;

/**
 *
 * @author Administrator
 */
public class HtmlParser extends BaseParser {

    HtmlHandler handler;

    public HtmlParser(Img_getterView _view) {
        super(_view);
    }

    @Override
    public void parse() {
        try {
            handler.process(getUrl());
        } catch (ParserException ex) {
            log("无法解析制定的URL，请查看网址是否正确，和网络是否正常。");
        }
    }

    @Override
    public void setHandler(BaseHandler _handler) {
        handler = (HtmlHandler) _handler;
    }

    @Override
    public BaseHandler getHandler() {
        return handler;
    }
}
