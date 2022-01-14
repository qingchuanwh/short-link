package cn.sequoiacap.shortlink.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

/**
 * a link domain model
 *
 * @author Bob Chin
 * created on 2021/12/25
 */
@Getter
@Setter
public class Link {
    private String id;
    private String originalLink;
    private String shortLink;
    private Integer hits;
    private OffsetDateTime createdDate;
    private OffsetDateTime latestAccessDate;

    public Link() {
    }

    public Link(String originalLink, String shortLink) {
        this.originalLink = originalLink;
        this.shortLink = shortLink;
    }
}
