package img_getter.img.handler;

import java.util.Vector;

/**
 *
 * @author daoyu
 */
public interface BaseHandler {

    /*
     * @return Vector<String> the external image URL list, like "http://xmwq.net/icon.jpg"
     */
    public Vector<String> getExtImg();

    /*
     * @return Vector<String> the internal image URL list, like "/icon.jpg"
     */
    public Vector<String> getImg();

    /*
     * Empty the internal and external list. Try not to download the images twice.
     */
    public void reset();
}
