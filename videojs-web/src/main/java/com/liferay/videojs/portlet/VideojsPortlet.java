package com.liferay.videojs.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.videojs.constants.VideojsPortletKeys;
import com.liferay.videojs.portlet.action.VideojsConfiguration;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;

/**
 * @author yasuflatland
 */
@Component(
    configurationPid =VideojsPortletKeys.VIDEOJS_CONFIG,
    immediate = true,
	property = {
		"com.liferay.portlet.display-category=videojs",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Videojs Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + VideojsPortletKeys.VIDEOJS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VideojsPortlet extends MVCPortlet {
	
    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        renderRequest.setAttribute(VideojsConfiguration.class.getName(), _videojsConfiguration);

        super.doView(renderRequest, renderResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _videojsConfiguration = Configurable.createConfigurable(VideojsConfiguration.class, properties);
    }

    private volatile VideojsConfiguration _videojsConfiguration;	
}