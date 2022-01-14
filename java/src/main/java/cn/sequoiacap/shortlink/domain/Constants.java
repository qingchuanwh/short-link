package cn.sequoiacap.shortlink.domain;

/**
 * @author Bob Chin
 * created on 2021/12/26
 */
public class Constants {
    /**
     * characters used for short links
     */
    public static final String[] SHORT_LINK_CHARS = new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public static final int SHORT_LINK_MAX_SIZE = 8;

    public static final int SHORT_LINK_MIN_SIZE = 4;
}
