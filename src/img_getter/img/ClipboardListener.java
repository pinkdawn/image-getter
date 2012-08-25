/*
 * 从系统剪切板获取copy到的要下载的地址，并且自动放到下载栏中。
 */
package img_getter.img;

import img_getter.Img_getterView;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/** *
 * @author zhuangdy1 2012-2-2
 */
public class ClipboardListener implements FlavorListener, ClipboardOwner {

    Img_getterView view;
    Clipboard clip;
    Set<String> links;

    public ClipboardListener(Img_getterView igv, Clipboard _clip) {
        this.view = igv;
        this.clip = _clip;
        this.links = new HashSet<String>();
    }

    public void flavorsChanged(FlavorEvent e) {
        try {
            Clipboard clipboard = (Clipboard) e.getSource();
            Transferable content = clipboard.getContents(this);
            if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String data = (String) content.getTransferData(DataFlavor.stringFlavor);
                if (this.links.add(data)) {
                    if (data.startsWith("http")) {
                        view.log("检测到剪切板变化，新的url为：");
                        view.setUrl(data);
                        view.log(data);
                    }
                }
            }
        } catch (UnsupportedFlavorException ex) {
            //
        } catch (IOException ex) {
            view.log("检测到剪切板变化，从剪切板获取内容失败");
        } finally {
            this.clip.removeFlavorListener(this);
            this.clip.setContents(this.clip.getContents(null), this);
            this.clip.addFlavorListener(this);
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //
    }
}
