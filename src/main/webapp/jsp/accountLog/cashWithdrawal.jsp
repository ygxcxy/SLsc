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
                            <label class="layui-form-label">${account.balance}元</label>
                            <input type="hidden" name="balance" value="${account.balance}" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label class="layui-form-label">提现金额：</label>
                            <div class="layui-input-block">
                                <input type="text" name="money"  value="" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-col-xs3">
                            <label  id="tipMoney" style="color: red;width: 500px;display:inline-block;margin-top: 10px;margin-left: 35px" >
                                (提现金额不能大于余额)
                            </label>
                        </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">银行名称：</label>
                        <div class="layui-input-block">
                            <label class="layui-form-label">${user.bankName}</label>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">银行开户名：</label>
                        <div class="layui-input-block">
                            <label class="layui-form-label">${user.accountHolder}</label>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">银行卡号：</label>
                        <div class="layui-input-block">
                            <label class="layui-form-label">${user.bankAccount}</label>
                        </div>
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

</body>
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


        var checkTMoney;
        $("input[name='money']").blur(function () {
            checkTMoney = false;
            var tipMoney= $("#tipMoney");
            //账号余额
            var balance= parseFloat($("input[name='balance']").val());

            //转账金额
            var money= parseFloat($("input[name='money']").val());


            if(isNaN(money)){
                tipMoney.html("请输入正确的提现金额");
                return;
            }else{
                tipMoney.html("");
                checkTMoney = true;
            }
        })

        $("#btn").click(function () {
            $("input[name='money']").blur()
            if(!checkTMoney){
                return;
            }
            //提现金额
            var money= parseFloat($("input[name='money']").val());

            $.ajax({
                url:"${ctx}/cash/cashWithdrawal",
                data:{"money":money},
                success:function (data) {
                    if(data.code == 2000){
                        layer.msg(data.msg,{
                            time:1500,
                            end:function () {
                                window.location.href = "${ctx}/account/toCashWithdrawal";
                            }
                        });
                    }else{
                        layer.msg(data.msg);
                    }
                }
            })
        })
    });
</script>
</html>
