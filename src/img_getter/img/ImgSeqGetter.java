/*
 * 顺序的下载一些里，只有部分数字变化的 图片。
 */
package img_getter.img;

import img_getter.Img_getterView;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ImgSeqGetter extends Thread {

    private Img_getterView view;
    private int height, width;
    String path;
    private String[] acceptFormats;

    public ImgSeqGetter(Img_getterView _view) {
        this.view = _view;
    }

    /*
     * 给出数字，和想要格式化为长度为多少的字符串，如不足，自动在前边补0.
     * @parameter value e.g. 199
     * @parameter length e.g. 5
     * @return String the formated String, e.g. 00199
     */
    private String parseString(int value, int length) {
        String _value = Integer.toString(value);
        int valueLength = _value.length();

        StringBuilder sb = new StringBuilder();
        if (sb.length() < length) {
            for (int i = 0; i < (length - valueLength); i++) {
                sb.append("0");
            }
        }
        sb.append(_value);

        return sb.toString();
    }

    /*
     * 输入：http://xxx.xxxx/ 001 ~ 025 .jpg
     * @return String[] 每个jpg的下载地址
     */
    private String[] getImgUrls() {
        String imgFrom = view.getImgFrom();
        String imgTo = view.getImgTo();

        if (imgFrom.length() != imgTo.length()) {
            view.log("变化部分的长度必须是相等的！");
            return null;
        }

        String imgBeginUrl = view.getImgBeginUrl();
        String imgEndUrl = view.getImgEndUrl();

        try {
            int from = Integer.parseInt(imgFrom);
            int to = Integer.parseInt(imgTo);
            int length = to - from + 1;
            String results[] = new String[length];
            int i = 0;
            for (; from != (to + 1); from++) {
                StringBuilder sb = new StringBuilder();

                sb.append(imgBeginUrl);
                sb.append(parseString(from, imgTo.length()));
                sb.append(imgEndUrl);

                results[i] = sb.toString();
                i++;
            }
            return results;
        } catch (NumberFormatException nfe) {
            view.log("错误的变化地址！请仅在变化部分输入数字，把字母等放在前边或者后边");
            return null;
        }
    }

    @Override
    public void run() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        this.height = Integer.parseInt(view.getMinHeight());
        this.width = Integer.parseInt(view.getMinWidth());
        this.path = view.getPath();
        this.acceptFormats = view.getImgFormats().split(" ");

        String[] imgUrls = getImgUrls();
        if (imgUrls != null) {
            for (String imgUrl : imgUrls) {
                executor.submit(new ImgDownloadThread(imgUrl, width, height, path, acceptFormats, view));
            }

            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
                view.log("线程被终止，下载结束！");
            }
        }
    }
//    public static void main(String []args){
//        ImgSeqGetter isg = new ImgSeqGetter(null);
//        System.out.println(isg.parseString(123, 8));
//    }
}
