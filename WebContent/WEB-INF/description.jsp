<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="desc" class="hidden">
	<div class="panel panel-default">
		<div class="panel-body" style="height: 150px; overflow: auto;">
			<c:out value="${language.getLanguageValue('$tool', '/$tools/desc-tool')}" />
		</div>
	</div>
</div>