package img_getter.img.handler;

import java.util.List;

/**
 *
 * @author daoyu
 */
public interface BaseHandler {

    /*
     * @return List<String> the external image URL list, like "http://xmwq.net/icon.jpg"
     */
    public List<String> getExtImg();

    /*
     * @return List<String> the internal image URL list, like "/icon.jpg"
     */
    public List<String> getImg();

    /*
     * Empty the internal and external list. Try not to download the images twice.
     */
    public void reset();
}
