<%@ page language="java" contentType="text/xml;charset=utf-8" pageEncoding="utf-8"%><?xml version="1.0" encoding="utf-8"?> <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<list><c:forEach items="${newses}" var="news">
	<news>
		<id>${news.id}</id>
		<title>${news.title}</title>
        <content>${news.content}</content>
        <picpath>${news.picpath}</picpath>
        <url>${news.url}</url>
        <time>${news.time}</time>
		</news></c:forEach>
</list>
