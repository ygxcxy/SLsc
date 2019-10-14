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
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">角色列表:</label>
                <div class="layui-input-block">
                    <input type="radio" name="roleId" value="1" title="系统管理员"/>
                    <input type="radio" name="roleId" value="2" title="会员" checked/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限列表:</label>
                <div class="layui-input-block">
                    <button class="layui-btn" id ="bth" lay-filter="formDemo">保存</button>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="checkbox" name="like[read]" title="阅读" checked>

                </div>
            </div>
            <%--一級--%>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <c:forEach items="${first}" var="obj" varStatus="index">
                        <br> <input type="checkbox" name="functionId" title="${obj.functionName}">
                        <c:forEach var="second" items="${second}">
                            <c:if test="${obj.id==second.parentId}">
                                <input type="checkbox" name="functionId" title="${second.functionName}">
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>


        </form>


    </div>
</body>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script>
    // JavaScript代码区域
    layui.use(['element','form'], function () {
        var element = layui.element;
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</html>