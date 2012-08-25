/*
 * 用来处理msn car上的图片地址的，图片放置格式为：
 * <a href=""><b>http://xxx.jpg</b></a>
 */
package img_getter.img.handler;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;

/**
 *
 * @author daoyu
 */
public class MsnImgHandler extends SwingHandler {

    private boolean isLink = false;

    @Override
    public void handleStartTag(
            HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        if (!(tag.equals(HTML.Tag.A))) {
            return;
        } else {
            isLink = true;
        }
    }

    @Override
    public void handleText(char[] data, int pos) {
        if (isLink) {
            String imgUrl = new String(data);
            if (SwingHandler.isImg(imgUrl)) {
                if (imgUrl.length() >= 4 && imgUrl.substring(0, 4).equalsIgnoreCase("http")) {
                    getExtImg().add(imgUrl);
                } else {
                    getImg().add(imgUrl);
                }
            }
        }
    }

    @Override
    public void handleEndTag(HTML.Tag tag, int pos) {
        if (tag.equals(HTML.Tag.A)) {
            if (isLink) {
                isLink = false;
            }
        }
    }
}
