package img_getter.img.handler;

import img_getter.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;

public class BaseHandlerImpl implements BaseHandler {

    List<String> img;
    List<String> extImg;

    public BaseHandlerImpl() {
        img = new ArrayList<String>();
        extImg = new ArrayList<String>();
    }

    public List<String> getExtImg() {
        return extImg;
    }

    public List<String> getImg() {
        return img;
    }

    public void reset() {
        if (img != null) {
            img.clear();
        }
        if (extImg != null) {
            extImg.clear();
        }
    }

    public void addExtImg(String link) {
        String new_link = UrlUtils.getAbsoluteUrl(link);

        for (String _link : extImg) {
            String old_link = UrlUtils.getAbsoluteUrl(_link);
            if (old_link.startsWith(new_link)) {
                extImg.remove(_link);
                extImg.add(link);
                return;
            } else if (new_link.startsWith(old_link)) {
                return;
            }
        }
        extImg.add(link);
    }

    public void addImgLink(String link) {
        if (link == null) {
            return;
        }
        if (link.length() >= 4 && link.substring(0, 4).equalsIgnoreCase("http")) {
            addExtImg(link);
        } else {
            img.add(link);
        }
    }

    public static void main(String args[]) {
        BaseHandlerImpl bp = new BaseHandlerImpl();
        String tests[] = {"http://myicon211.poco.cn/5857/58573108_64.jpg",
            "http://www.poco.cn/css_common/v3/images/common/blank.gif",
            "http://my.poco.cn/manage/images/ajax_loader.gif",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_000.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_001.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_002.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_003.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_004.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_005.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_006.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_007.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_000.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_001.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_002.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_003.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_004.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_005.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_006.jpg",
            "http://image165-c.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_007.jpg",
            "http://my.poco.cn/v3/images/new.gif",
            "http://img165.poco.cn/mypoco/myphoto/20111030/13/58573108201110301359167533039441229_005_640.jpg",
            "http://myicon211-c.poco.cn/5857/58573108_64.jpg"};
        for (String s : tests) {
            bp.addExtImg(s);
        }

        for (String s : bp.getExtImg()) {
            System.out.println(s);
        }
    }
}
