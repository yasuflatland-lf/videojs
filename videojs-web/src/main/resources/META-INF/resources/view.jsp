<%@ include file="/init.jsp" %>

<%
	PortletPreferences prefs = renderRequest.getPreferences();
	streamURL = prefs.getValue("streamURL", "failed");
%>

  <video id="<portlet:namespace />video_1" class="video-js vjs-default-skin" controls preload="auto" width="640" height="268" 
  data-setup='{}'>
    <source src="<%= streamURL %>" type="application/x-mpegURL">
  </video>