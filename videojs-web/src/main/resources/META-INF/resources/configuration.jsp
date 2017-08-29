<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>"
	var="configurationRenderURL" />
	
<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:input name="<%= Constants.CMD %>" type="hidden"
					value="<%= Constants.UPDATE %>" />
				<aui:input name="redirect" type="hidden"
					value="<%= configurationRenderURL %>" />

				<aui:fieldset>
					<aui:input type="text" name="streamURL"
						value="<%= streamURL %>" size="45" required="<%= true %>" />
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>
	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>
