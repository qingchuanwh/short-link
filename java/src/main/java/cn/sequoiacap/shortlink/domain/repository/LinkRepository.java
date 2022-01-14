package cn.sequoiacap.shortlink.domain.repository;

import cn.sequoiacap.shortlink.domain.model.Link;

/**
 * repository interface for link
 *
 * @author Bob Chin
 * created on 2021/12/26
 */
public interface LinkRepository {
    Link save(Link link);

    String findOriginalLink(String shortLink);
}
