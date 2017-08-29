package com.liferay.videojs.portlet.action;

import com.liferay.videojs.constants.VideojsPortletKeys;

import aQute.bnd.annotation.metatype.Meta;

/**
 * Videojs Configuration
 *
 * @author Yasuyuki Takeo
 */
@Meta.OCD(id = VideojsPortletKeys.VIDEOJS_CONFIG)
public interface VideojsConfiguration {

    @Meta.AD(deflt = "https://d2zihajmogu5jn.cloudfront.net/bipbop-advanced/bipbop_16x9_variant.m3u8", required = false)
    public String streamURL();
}
