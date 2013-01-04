/*
 * 使用sun swing提供的parser；速度尚可，缺点是非标准的html以及任何xhtml都不能解析，无法
 * 读出<IMG>标签。
 * 用来处理最简单的 <img src=""/>这种图像。
 * 其中以 http://开头的是图片的完整地址。
 * 否则就是 站内地址，下载时需加上 [protocol]://[host]
 */
package img_getter.img.handler;

import img_getter.utils.UrlUtils;
import java.util.Vector;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author daoyu
 */
public class SwingHandler extends HTMLEditorKit.ParserCallback implements BaseHandler{
    private Vector<String> img;
    private Vector<String> extImg;

    public SwingHandler(){
        img = new Vector<String>();
        extImg = new Vector<String>();
    }

    public static boolean isImg(String value){
        String _value = value.toLowerCase();
        for (String format:UrlUtils.IMAGE_FORMATS){
            if (_value.contains(format))
                    return true;
        }
        
        return false;
    }

    @Override
    public void handleSimpleTag(
            HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        if (tag.equals(HTML.Tag.IMG)) {
            String link = (String) attributes.getAttribute(HTML.Attribute.SRC);
            if (link == null || link.length() == 0) {
                return;
            }

            if (link.length() >= 4 && link.substring(0, 4).equalsIgnoreCase("http")) {
                extImg.add(link);
            } else {
                img.add(link);
            }
        }
    }

    public Vector<String> getExtImg() {
        return extImg;
    }

    public Vector<String> getImg() {
        return img;
    }

    public void reset() {
        if (img!=null)
            img.clear();
        if(extImg!=null)
            extImg.clear();
    }
}
