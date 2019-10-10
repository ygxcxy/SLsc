
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
        <form class="layui-form" method="post" action="${ctx}/user/login" enctype="multipart/form-data">
            <div class="layui-form-item">
                <label class="layui-form-label">账户:</label>
                <div class="layui-input-block">
                    <input type="text" name="loginCode" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码:</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="login" value="立即提交"/>
                </div>
            </div>
            <script src="${ctx}/layui/layui.js" charset="utf-8"></script>
            <script>
                layui.use(['form','jquery','layer','element'],function(){
                    var form = layui.form;
                    var $ = layui.jquery;
                    var element = layui.element;
                    var layer = layui.layer;
                    $("#login").click(function () {
                        $.ajax({
                            url:"${ctx}/user/login",
                            data:{loginCode:$("input[name='loginCode']").val(),
                                password:$("input[name='password']").val()
                                },
                            type:"post",
                            success:function(data){
                                if(data.code==2000){
                                    layer.msg(data.msg);
                                    location.href="${ctx}/user/main";
                                }
                                if(data.code==4000){
                                    layer.msg(data.msg)

                                }
                            }
                        })
                    })
                });
            </script>
        </form>
    </div>
</div>
</body>
</html>