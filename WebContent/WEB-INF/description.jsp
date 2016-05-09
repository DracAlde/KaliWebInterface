<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	Language language = new Language(); //class must be instancied each time we want to use the language parser
	language.setCurrentTool(Constants.TOOL_TLS_SLED);
	Constants langSelected = new Constants();
%>
<div id="desc" class="hidden">
	<div class="panel panel-default">
		<div class="panel-body" style="height: 150px; overflow: auto;">
			<%= language.getLanguageValue("/site/tools/$tool/desc-tool") %>
		</div>
	</div>
</div>