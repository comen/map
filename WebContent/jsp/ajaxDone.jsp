<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

{
	"statusCode":"${statusCode}", 
	"message":"${param.callbackType ne 'forwardConfirm' ? message : ''}", 
	"confirmMsg":"${param.callbackType eq 'forwardConfirm' ? message : ''}",
	"navTabId":"${param.navTabId}", 
	"rel":"${param.rel}",
	"callbackType":"${param.callbackType}",
	"forwardUrl":"${empty forwardUrl ? param.forwardUrl : forwardUrl}"
}