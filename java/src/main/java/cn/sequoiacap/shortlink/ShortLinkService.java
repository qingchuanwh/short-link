package cn.sequoiacap.shortlink;

/**
 * short link service api
 *
 * @author Bob Chin
 * created on 2021/12/25
 */
public interface ShortLinkService {
    /**
     * convert the original link to a short link
     *
     * @param originalLink original link
     * @param linkLength   short link length
     * @return short link
     */
    String shorten(String originalLink, int linkLength);

    /**
     * read the original link of the short link inputted
     *
     * @param shortLink short link
     * @return original link
     */
    String readOriginalLink(String shortLink);
}
