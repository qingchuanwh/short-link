package cn.sequoiacap.shortlink.domain;

import cn.sequoiacap.shortlink.domain.model.Link;
import cn.sequoiacap.shortlink.domain.repository.LinkRepository;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * A short link generator based on guava Murmur3 hash algorithm
 *
 * @author Bob Chin
 * created on 2021/12/26
 */
@Component
@RequiredArgsConstructor
public class MurmurHash3ShortLinkGenerator implements ShortLinkGenerator {
    private final LinkRepository linkRepository;

    @Override
    public String shorten(String originalLink, int length) {
        long hashcode = Hashing.murmur3_32_fixed().hashUnencodedChars(originalLink).padToLong();
        String encoded, exists;
        do {
            encoded = encode(hashcode++, length);
            exists = linkRepository.findOriginalLink(encoded);
        } while (exists != null && !exists.equals(originalLink));
        Link link = new Link(originalLink, encoded);
        linkRepository.save(link);
        return encoded;
    }

    private String encode(long oct, int length) {
        BigInteger octLong = BigInteger.valueOf(oct);
        StringBuilder builder = new StringBuilder(length);
        while (!octLong.equals(BigInteger.ZERO)) {
            BigInteger[] divideAndReminder = octLong.divideAndRemainder(BigInteger.valueOf(Constants.SHORT_LINK_CHARS.length));
            builder.append(Constants.SHORT_LINK_CHARS[divideAndReminder[1].intValue()]);
            octLong = divideAndReminder[0];
        }
        return builder.reverse().toString();
    }
}
