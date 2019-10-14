<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shaochenjie
  Date: 2019/10/7
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="/index/jsp.jsp"/>
<body class="layui-layout-body">
<div class="layui-body">
    <jsp:include page="/index/header.jsp"/>
    <jsp:include page="/index/left.jsp"/>
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
        <form class="layui-form" method="post" action="${ctx}/sys/role/add">
            <div class="layui-form-item">
                <label class="layui-form-label">角色编号</label>
                <div class="layui-input-block">
                    <textarea name="roleCode" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-block">
                    <textarea name="roleName" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色状态</label>
                <div class="layui-input-block">
                    <input type="radio" name="isStart" value="0" title="未启用" checked="">
                    <input type="radio" name="isStart" value="1" title="启用">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="submit" class="layui-btn" value="提交">
                </div>
            </div>
            <script src="${ctx}/layui/layui.js" charset="utf-8"></script>
            <script>
                //JavaScript代码区域
                layui.use(['element', 'form'], function () {
                    var element = layui.element;
                    var form = layui.form;

                });
            </script>
            </div>
        </form>
    </div>
</div>
</body>
</html>