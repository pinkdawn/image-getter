package img_getter.img;

import img_getter.Img_getterView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImgDownloadThread implements Callable {
    private int height, width;
    private String fileName, filePath;
    private Img_getterView view;
    private String[] acceptFormats;

    public ImgDownloadThread(String fn, Img_getterView _view) {
        fileName = fn;
        view = _view;
        width = Integer.parseInt(view.getMinWidth());
        height = Integer.parseInt(view.getMinHeight());
        filePath = view.getPath();
        acceptFormats = view.getImgFormats().split(" ");
        view = _view;
    }

    /*
     * Check whether the image that readed by ImageReader fulfills the width and height constrains.
     * @parameter ImageReader the image reader that will read the buffered image.
     * @return true if the image's size is qualified, false the image is too small.
     */
    private boolean checkImg(ImageReader ir) throws IOException {
        if (ir == null) {
            return false;
        }
        return ir.getHeight(0) >= this.height && ir.getWidth(0) >= this.width;
    }

    /*
     * Get the real image name for local storage useage.
     * @parameter fullUrl the full http image url, e.g. http://xmwq.net/icon.jpg?h=200&w=300
     * @parameter real name of the image, e.g. icon.jpg
     */
    private static String getRealName(String fullUrl) {
        String realName = fullUrl.substring(fullUrl.lastIndexOf("/") + 1);
        if (realName.contains("?")) {
            realName = realName.substring(0, realName.indexOf("?"));
        }

        return realName;
    }

    /*
     * Get the image's format by the image's local storage usage. The input of this function
     * is usually generate by getRealName(fullUrl);
     * @parameter fileName the image's full name, e.g. icon.jpg
     * @return the format of the image, e.g. jpg
     */
    private String getFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    private void CheckPathExists(){
        File pathDir = new File(filePath);
        pathDir.setReadable(true);
        pathDir.setWritable(true);

        if (!pathDir.exists()) { //如果指定文件夹不存在，则创建
            boolean success = pathDir.mkdirs();
            if (!success) {
                view.log("无法创建下载文件夹！");
            } else {
                view.log("创建文件夹: " + filePath);
            }
        }
    }

    @Override
    public Object call() {
        String localName = getRealName(fileName);
        String format = getFormat(localName);

        for (String _format : acceptFormats) {
            if (_format.equalsIgnoreCase(format)) { // 首先看是否是需要下载的 图片格式
                ImageReader ir = null;
                try {
                    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(format);
                    ir = readers.next();
                    ir.setInput(ImageIO.createImageInputStream(new URL(fileName).openStream()));

                    if (this.checkImg(ir)) { // 然后打开 图片头部，检查是否符合 图片大小要求。
                        CheckPathExists();
                        String destFileName = String.format("%s%s", filePath, localName);

                        File outTemp = new File(destFileName); //检查是否已有重名文件
//                        while (outTemp.exists()) {
//                            sb.insert(filePath.length(), "_");
//                            outTemp = new File(sb.toString());
//                        }
                        if (! outTemp.exists()){
                            ImageIO.write(ir.read(0), format, outTemp);
                            view.log("成功下载: " + destFileName);
                        }
                    }
                } catch (IllegalArgumentException iie) {
                    view.log("由于没有找到图片解码器，无法下载: " + fileName);
                } catch (IOException e) {
                    view.log("由于网络或错误的图像地址，无法下载: " + fileName);
                } finally {
                    if (ir != null) {
                        ImageInputStream input = (ImageInputStream) (ir.getInput());
                        if (input != null) {
                            try {
                                input.close();
                            } catch (IOException e) {
                                view.log("无法关闭文件读取流。");
                            }
                        }
                        ir.abort();
                        ir.dispose();
                    }
                }
            }
        }
        return new Object();
    }
}
