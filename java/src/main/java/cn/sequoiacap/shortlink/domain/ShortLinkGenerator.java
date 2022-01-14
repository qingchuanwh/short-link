package cn.sequoiacap.shortlink.domain;

/**
 * interface of short link generator
 *
 * @author Bob Chin
 * created on 2021/12/25
 */
public interface ShortLinkGenerator {
    /**
     * convert the original link to a short link
     *
     * @param originalLink original link
     * @param length       length of the short link to be generated
     * @return short link
     */
    String shorten(String originalLink, int length);
}
