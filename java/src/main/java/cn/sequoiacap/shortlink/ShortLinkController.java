package cn.sequoiacap.shortlink;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 短链接服务
 *
 * @author Bob Chin
 * created on 2022/1/9
 */
@RestController
@Api("短链接服务")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService service;
    private final Config config;

    /**
     * 访问
     *
     * @param shortLink 短链接
     * @param response  HttpServletResponse instance
     */
    @ApiOperation("通过短链接访问原始链接")
    @RequestMapping("/{shortLink}")
    public void visit(@PathVariable("shortLink") @ApiParam("短链接") String shortLink, HttpServletResponse response) {
        try {
            String originalUrl = service.readOriginalLink(shortLink);
            if (!StringUtils.hasText(originalUrl)) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return;
            }
            response.setHeader("Location", originalUrl);
            response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    /**
     * 生成短链接
     *
     * @param originalLink 原始链接
     * @param response     HttpServletResponse instance
     */
    @ApiOperation("生成短链接")
    public void shorten(@PathVariable("originalLink") @ApiParam("原始链接") String originalLink, HttpServletResponse response) {
        try {
            String shortLink = service.shorten(originalLink, config.getLinkLength());
            response.getWriter().write(shortLink);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}