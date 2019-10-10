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
        <form class="layui-form" method="post" action="" enctype="multipart/form-data">
            <div class="layui-form-item">
                <label class="layui-form-label">旧密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="oldPwd" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="newPwd" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认新密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="configNewPwd" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" id="but" class="layui-btn" value="提交">
                </div>
            </div>
        </form>
        修改商城2级密码
        <form class="layui-form" method="post" action="" enctype="multipart/form-data">
            <div class="layui-form-item">
                <label class="layui-form-label">旧密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="oldPwd2" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="newPwd2" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认新密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="configNewPwd2" required  lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" id="but2" class="layui-btn" value="提交">
                </div>
            </div>

        </form>
        <script src="${ctx}/layui/layui.js" charset="utf-8"></script>
        <script>
            //JavaScript代码区域
            layui.use(['element', 'form','jquery','layer'], function () {
                var element = layui.element;
                var form = layui.form;
                var $ = layui.jquery;
                var layer = layui.layer;
                $("#but").click(function () {
                    var newPwd = $("input[name='newPwd']").val();
                    var configNewPwd = $("input[name='configNewPwd']").val();
                    if(newPwd!=configNewPwd){
                        layer.msg("两次密码不一致");
                        return;
                    }else{
                        $.ajax({
                            url:"${ctx}/user/updatePwd",
                            data:{newPwd:$("input[name='newPwd']").val(),
                                oldPwd:$("input[name='oldPwd']").val()
                            },
                            type:"post",
                            success:function (data) {
                                if(data.code==4000){
                                    layer.msg(data.msg);
                                    return
                                }else {
                                    layer.msg(data.msg);
                                }
                            }
                        })
                    }
                });
                $("#but2").click(function () {
                    var newPwd = $("input[name='newPwd2']").val();
                    var configNewPwd = $("input[name='configNewPwd2']").val();
                    if(newPwd!=configNewPwd){
                        layer.msg("两次密码不一致");
                        return;
                    }else{
                        $.ajax({
                            url:"${ctx}/user/updatePwd2",
                            data:{newPwd2:$("input[name='newPwd2']").val(),
                                oldPwd2:$("input[name='oldPwd2']").val()
                            },
                            type:"post",
                            success:function (data) {
                                if(data.code==4000){
                                    layer.msg(data.msg);
                                    return
                                }else {
                                    layer.msg(data.msg);
                                }
                            }
                        })
                    }
                })
            });
        </script>
    </div>
</div>
</body>
</html>