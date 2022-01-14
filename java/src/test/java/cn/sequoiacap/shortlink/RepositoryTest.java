package cn.sequoiacap.shortlink;

import cn.sequoiacap.shortlink.domain.model.Link;
import cn.sequoiacap.shortlink.infrastructure.GuavaCacheLinkRepository;
import cn.sequoiacap.shortlink.infrastructure.LinkCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Bob Chin
 * created on 2022/1/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GuavaCacheLinkRepository.class, LinkCache.class})
public class RepositoryTest {
    @Autowired
    private GuavaCacheLinkRepository repo;

    @Test
    public void testWriteAndRead() {
        String originalLink = "http://www.baidu.com";
        String shortLink = "4b8SsX";
        Link link = new Link();
        link.setOriginalLink(originalLink);
        link.setShortLink(shortLink);
        repo.save(link);
        String found = repo.findOriginalLink(shortLink);
        Assert.assertEquals(found, originalLink);
    }

    @Test
    public void testReadNull() {
        String found = repo.findOriginalLink("SOME_URL_NOT_EXISTS");
        Assert.assertNull(found);
    }
}
