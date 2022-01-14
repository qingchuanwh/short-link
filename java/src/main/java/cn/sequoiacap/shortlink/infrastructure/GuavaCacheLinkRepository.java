package cn.sequoiacap.shortlink.infrastructure;

import cn.sequoiacap.shortlink.domain.model.Link;
import cn.sequoiacap.shortlink.domain.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Bob Chin
 * created on 2021/12/27
 */
@Repository
@RequiredArgsConstructor
public class GuavaCacheLinkRepository implements LinkRepository {
    private final LinkCache cache;

    @Override
    public Link save(Link link) {
        cache.put(link.getShortLink(), link);
        return link;
    }

    @Override
    public String findOriginalLink(String shortLink) {
        Link link = cache.get(shortLink);
        return link == null ? null : link.getOriginalLink();
    }
}
