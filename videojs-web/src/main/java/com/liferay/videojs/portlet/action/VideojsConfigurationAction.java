package com.liferay.videojs.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.videojs.constants.VideojsPortletKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;

/**
 * Videojs Configuraion Aciton
 *
 * Determine the scope of the portlet configuration in DS properties of Portlet
 * class The default of this generator is Portlet Instance scope.
 *
 * Look at the elements preferences-company-wide, preferences-unique-per-layout
 * and preferences-owned-by-group to determine the right scope. The following
 * table maps out the scopes:
 *
 * liferay-portlet.xml Scope preferences-company-wide=true Company
 * preferences-owned-by-group=true AND preferences-unique-per-layout=false Group
 * preferences-owned-by-group=true AND preferences-unique-per-layout=true
 * Portlet Instance
 *
 * @author Yasuyuki Takeo
 *
 */
@Component(
    immediate = true,
    configurationPid = VideojsPortletKeys.VIDEOJS_CONFIG,
    configurationPolicy = ConfigurationPolicy.OPTIONAL,
    property = {
        "javax.portlet.name=" + VideojsPortletKeys.VIDEOJS,
    }, service = ConfigurationAction.class
)
public class VideojsConfigurationAction
    extends DefaultConfigurationAction {

    @Override
    public String getJspPath(HttpServletRequest httpServletRequest) {
        return "/configuration.jsp";
    }

    @Override
    public void processAction(
        PortletConfig portletConfig, ActionRequest actionRequest,
        ActionResponse actionResponse) throws Exception {

        String streamURL = ParamUtil.getString(actionRequest,"streamURL");

        if (_log.isDebugEnabled()) {
            _log.debug("stream URL:" + streamURL);
        }

        List<String> errors = new ArrayList<>();
        if (validate(streamURL, errors)) {
            setPreference(actionRequest,
                          "streamURL", streamURL);

            SessionMessages.add(actionRequest, "prefs-success");
        }

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        if (_log.isDebugEnabled()) {
            _log.debug("Videojs Portlet configuration include");
        }

        httpServletRequest.setAttribute(VideojsConfiguration.class.getName(),
                                        _videojsConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _videojsConfiguration = Configurable
            .createConfigurable(VideojsConfiguration.class, properties);
    }

    /**
     * Validate Preference
     *
     * @param streamURL Stream URL
     * @param errors
     * @return boolean
     */
    protected boolean validate(
        String streamURL, List<String> errors) {
        boolean valid = true;

        if (Validator.isNull(streamURL)) {
            errors.add("stream-URL-required");
            valid = false;
        }
        return valid;
    }

    private static final Log _log = LogFactoryUtil
        .getLog(VideojsConfigurationAction.class);

    private volatile VideojsConfiguration _videojsConfiguration;
}
