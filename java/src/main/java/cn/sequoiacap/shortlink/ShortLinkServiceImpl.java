package cn.sequoiacap.shortlink;

import cn.sequoiacap.shortlink.domain.Constants;
import cn.sequoiacap.shortlink.domain.ShortLinkGenerator;
import cn.sequoiacap.shortlink.domain.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * short link service implement
 *
 * @author Bob Chin
 * created on 2021/12/25
 */
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl implements ShortLinkService {
    private ShortLinkGenerator generator;
    private final LinkRepository repo;

    @Autowired
    @Qualifier("murmurHash3ShortLinkGenerator")
    public void setGenerator(ShortLinkGenerator generator) {
        this.generator = generator;
    }

    @Override
    public String shorten(String originalLink, int linkLength) {
        if (linkLength < Constants.SHORT_LINK_MIN_SIZE || linkLength > Constants.SHORT_LINK_MAX_SIZE) {
            throw new IllegalArgumentException(String.format("length must between %d and %d",
                    Constants.SHORT_LINK_MIN_SIZE,
                    Constants.SHORT_LINK_MAX_SIZE));
        }
        return generator.shorten(originalLink, linkLength);
    }

    @Override
    public String readOriginalLink(String shortLink) {
        return repo.findOriginalLink(shortLink) ;
    }
}
