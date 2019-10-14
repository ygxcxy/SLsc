<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 14867
  Date: 2019/10/7
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="${ctx}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <jsp:include page="/index/header.jsp"/>
    <jsp:include page="/index/left.jsp"/>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <h4>汇款充值</h4>
            <hr>
            <form method="post">
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">汇款金额：</label>
                            <div class="layui-input-block">
                                <input type="text" name="amount" value="" class="layui-input">
                            </div>
                        </div>
                </div>
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">摘要：</label>
                            <div class="layui-input-block">
                                <input type="text" name="remark"  value="" class="layui-input">
                            </div>
                        </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">单号：</label>
                        <div class="layui-input-block">
                            <label class="layui-form-label" style="color: #007DDB">008085M0127735312020</label>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input class="layui-btn" type="btn" id="btn" lay-filter="demo1" value="立即提交"/>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="${ctx}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element','jquery','form','layer','table','laydate'], function(){
        var element = layui.element;
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;
        var table = layui.table;

        var laydate = layui.laydate;

        $("#btn").click(function () {
            var amount = $("input[name='amount']").val();
            var remark = $("input[name='remark']").val();
            $.ajax({
                url:"${ctx}/account/recharge",
                data:{"amount":amount,"remark":remark},
                success:function (data) {
                    if(data.code == 2000){
                        $("form")[0].reset();
                    }
                    layer.msg(data.msg);
                }
            })
        })
    });
</script>
</body>
</html>
