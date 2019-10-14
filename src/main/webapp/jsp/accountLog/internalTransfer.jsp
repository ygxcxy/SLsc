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
            <h4>会员列表</h4>
            <hr>
            <form method="post">
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">当前余额：</label>
                        <div class="layui-input-block">
                            <label class="layui-form-label">${dAccount.balance}</label>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">转账目标会员：</label>
                            <div class="layui-input-block">
                                <input type="text" name="transferCard" value="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-col-xs3">
                            <label class="layui-form-label" id="tipCard" style="color: red;width: 420px" >（接收转帐的会员用户名必须填写正确，不能给註册会员转帐。）</label>
                        </div>
                </div>
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">转账金额：</label>
                            <div class="layui-input-block">
                                <input type="text" name="money"  value="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-col-xs3">
                            <label class="layui-form-label" style="width: 190px" >
                                <a href="${ctx}/account/toRecharge" style="color: blue">余额不足，马上去汇款充值</a>
                            </label>
                        </div>
                </div>
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">二级密码：</label>
                            <div class="layui-input-block">
                                <input type="password" autocomplete="new-password"   id="password2" value="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-col-xs3">
                            <label class="layui-form-label" id="tip" style="color: red;width: 175px" >
                                请输入二级密码进行验证
                            </label>
                        </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input class="layui-btn" type="button" id="btn" lay-filter="demo1" value="立即提交"/>
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


        var checkPwd;
        $("#password2").blur(function () {
            var password2 = $(this).val();
            var tip = $("#tip");
            if(password2 == ""){
                tip.text("请输入二级密码进行验证");
                checkPwd = false;
                return;
            }

            $.ajax({
                url:"${ctx}/user/checkPassword2",
                data:{"password2":password2},
                success:function (data) {
                    if(data == 2000){
                        checkPwd = true;
                        tip.text(data.msg);
                    }else{
                        checkPwd = false;
                        tip.text(data.msg);
                    }
                }
            })
        })

        var checkTCard;
        $("input[name='transferCard']").blur(function () {
            var transferCard = $(this).val();
            var tip = $("#tipCard");
            if(transferCard == ""){
                tip.text("请输入转帐的会员用户名");
                checkTCard = false;
                return;
            }

            $.ajax({
                url:"${ctx}/user/checkTransferCard",
                data:{"transferCard":transferCard},
                success:function (data) {
                    if(data == 2000){
                        checkTCard = true;
                        tip.text(data.msg);
                    }else{
                        checkTCard = false;
                        tip.text(data.msg);
                    }
                }
            })
        })
    });
</script>
</body>
</html>
