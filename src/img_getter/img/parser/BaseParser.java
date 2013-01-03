/*
 * 从网页上解析图片。
 * 主要流程为 getInput() -> handler reset -> parse() -> get handler's Img and extImg list
 * -> start download Thread.
 */
package img_getter.img.parser;

import img_getter.img.handler.BaseHandler;
import img_getter.Img_getterView;
import img_getter.img.ImgDownloadThread;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author daoyu
 */
public abstract class BaseParser implements Runnable {

    private final int MAX_THREADS = 10;
    private Img_getterView view;
    private String url, path;
    private int height, width;
    public static final String[] IMAGE_FORMATS = {".jpg", ".png", ".bmp", ".gif"};
    public static final String[] WEB_PROTOCOLS = {"http://", "https://", "ftp://"};
    private String[] acceptFormats;

    public static String get_absolute_url(String link){
        link = link.toLowerCase();
        for (String s: BaseParser.WEB_PROTOCOLS){            
            link = link.replaceAll(s, "");
        }
        for (String s: BaseParser.IMAGE_FORMATS){
            link = link.replaceAll(s, "");
        }
        
        return link;
    }

    public BaseParser(Img_getterView _view) {
        this.view = _view;
    }

    /*
     * 获得用户输入的值，例如 图片的 网页地址，限制宽度，高度，存放路径等。
     * 放在这里读取，而不是放在 ImgDownloadThread里读取，是因为在downloadThread里，
     * 每次重新从前端获取数据，浪费资源。
     */
    public void getInput() {
        this.url = view.getUrl();
        this.height = Integer.parseInt(view.getMinHeight());
        this.width = Integer.parseInt(view.getMinWidth());
        this.path = view.getPath();
        acceptFormats = view.getImgFormats().split(" ");

        File pathDir = new File(path);
        pathDir.setReadable(true);
        pathDir.setWritable(true);

        if (!pathDir.exists()) { //如果指定文件夹不存在，则创建
            boolean success = pathDir.mkdirs();
            if (!success) {
                view.log("无法创建下载文件夹！");
            } else {
                view.log("创建文件夹: " + path);
            }
        }
    }

    public abstract void setHandler(BaseHandler _handler);

    public abstract BaseHandler getHandler();

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Img_getterView getView() {
        return view;
    }

    public void setView(Img_getterView view) {
        this.view = view;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /*
     * 以正则表达式替换掉目标URL中的相应字符串。多个表达式之间使用:::来分割
     * @parameter String oldName 输入的图片地址字符串
     * @parameter String pattenFrom 需要要被替换的 字符串。
     * @parameter String pattenTo 要替换成为 的字符串。
     */
    public String rex(String oldName, String patthenFrom, String pattenTo) {
        String[] froms = patthenFrom.split(":::");
        String[] tos = pattenTo.split(":::");

        String temp = oldName;

        for (int i = 0; i < froms.length; i++) {
            Pattern p = Pattern.compile(froms[i]);
            Matcher m = p.matcher(temp);
            temp = m.replaceAll(tos[i]);
        }

        return temp;
    }

    /*
     * 管理下载从Handler获得的所有image地址的下载线程。
     * @parameter Iterator<String> it, 指向存放image地址的容器的迭代器。
     * @parameter boolean wait，是否等到所有文件下载完再继续下一步。
     * @parameter String baseUrl 图像下载地址的前缀
     */
    public void download(Iterator<String> it, boolean wait, String baseUrl) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(8);

        while (it.hasNext()) {
            String imgUrl = it.next();
            if (view.enableRex()) { //如果需要替换正则表达式字符串
                imgUrl = rex(imgUrl, view.getFromPattern(), view.getToPattern());
            }
            if (null != baseUrl) {
                imgUrl = baseUrl + imgUrl;
            }
            
            executor.submit(new ImgDownloadThread(imgUrl, width, height, path, acceptFormats, view));
        }

        executor.shutdown();
        if (wait) {
            executor.awaitTermination(1, TimeUnit.DAYS);
        }
    }

    public void run() {
        try {
            getInput();
            getHandler().reset();

            parse(); //调用handler，将url解析出的img放到handler的链表中。

            int total = getHandler().getExtImg().size() + getHandler().getImg().size();
            view.log(total + " 张图片找到，现在开始下载。");

            Iterator<String> it = getHandler().getExtImg().iterator();
            download(it, true, null);

            it = getHandler().getImg().iterator();
            if (it.hasNext() && (view.getBaseUrl() == null || view.getBaseUrl().isEmpty())) {
                view.log("找到站内图片，但是未设置网站根目录，请设置。例如：\nhttp://www.35.com/abc/abc.jpg，请设置为\nhttp://www.35.com/ 注意最后的/。");
            } else {
                download(it, true, view.getBaseUrl());
            }

            view.log("下载完毕！");
        } catch (InterruptedException ex) {
            view.log("多线程下载异常！可能是由于太多图片");
        } finally {
            view.enableDownloadButton(true);
            view.setUrl("");
        }
    }

    /*
     * 解析HTML内容，找到所有的img链接。
     */
    public abstract void parse();

    /*
     * 在输出框里显示消息。
     */
    public void log(String data) {
        view.log(data);
    }
}
