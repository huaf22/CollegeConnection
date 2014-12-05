<%@ page language="java" contentType="text/xml;charset=utf-8" pageEncoding="utf-8"%><?xml version="1.0" encoding="utf-8"?> <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<list><c:forEach items="${usrs}" var="usr">
	<usr>
		<id>${usr.id}</id>
		<name>${usr.name}</name>
        <icon>${usr.icon}</icon>
        <sex>${usr.sex}</sex>
        <mode>${usr.mode}</mode>
        <status>${usr.status}</status>
		<place>${usr.place}</place>
	    <latitude>${usr.latitude}</latitude>
	    <longitiude>${usr.longitiude}</longitiude>
	    <addr>${usr.addr}</addr>
	    <ip>${usr.ip}</ip>
		</usr></c:forEach>
</list>
