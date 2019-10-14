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
        <form class="layui-form" method="post" action="${ctx}/sys/role/updateRole">
            <input type="hidden" name="id" value="${role.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">角色编号</label>
                <div class="layui-input-inline">
                    <input type="text" value="${role.roleCode}" name="roleCode" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="roleName" value="${role.roleName}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色状态</label>
                <div class="layui-input-block">
                    <input type="radio" name="isStart" <c:if test="${role.isStart==1}">checked</c:if> value="1" title="启用">
                    <input type="radio" name="isStart" <c:if test="${role.isStart==0}">checked</c:if> value="0" title="禁用">
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
                layui.use(['element', 'form','jquery'], function () {
                    var element = layui.element;
                    var form = layui.form;
                    var $ = layui.jquery;
                    form.on("select(roleId)",function (data) {
                        var pid = data.value;
                        $("select[name='userType']").empty();
                        $.ajax({
                            url:'${ctx}/sys/user/userType',
                            type:'post',
                            success:function (data) {
                                var html ='<option value="0">-请选择-</option>';
                                for (i = 0;i<data.data.length;i++){
                                    html +="<option value="+data.data[i].valueId+">"+data.data[i].valueName+"</option>"
                                }
                                if(pid !=1){
                                    $("select[name='userType']").html(html);
                                }
                                form.render();
                            }
                        });
                    })
                });
            </script>
        </form>
    </div>
</div>
</body>
</html>