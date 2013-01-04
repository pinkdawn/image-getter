/*
 * 使用htmlparser来解析HTML标签，下载其中的图片。
 */
package img_getter.img.handler;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

/**
 *
 * @author daoyu
 */
public class HtmlHandler extends BaseHandlerImpl {

    public void process(String url) throws ParserException {
        Parser p = new Parser();
        NodeFilter filter = new TagNameFilter("IMG");
        p.setResource(url);
        NodeList nl = p.parse(filter);
        SimpleNodeIterator it = nl.elements();
        while (it.hasMoreNodes()) {
            TagNode tn = (TagNode) it.nextNode();
            String link = tn.getAttribute("src");
            addImgLink(link);

            link = tn.getAttribute("file");
            addImgLink(link);
        }
    }
}
