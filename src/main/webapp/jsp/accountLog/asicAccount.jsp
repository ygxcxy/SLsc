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
                        <label class="layui-form-label">日期</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" id="beginDate">
                        </div>
                    </div>

                    <div class="layui-col-xs3">
                        <label class="layui-form-label">to</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" id="endDate">
                        </div>
                    </div>
                    <div class="layui-col-xs3">
                        <div class="layui-input-block">
                            <input class="layui-btn" type="button" id="btn" value="查询"/>
                        </div>
                    </div>
                </div>
            </form>

            <table class="layui-hide" id="test" lay-filter="demo"></table>

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

        //执行一个laydate实例
        laydate.render({
            elem: '#beginDate' //指定元素
        });
        laydate.render({
            elem: '#endDate' //指定元素
        });

        reloadTable(null,null);
        function reloadTable(beginDate,endDate){
            table.render({
                elem: '#test'
                ,url:'${ctx}/account/showAccountByEndDate'
                ,where:{"beginDate":beginDate,"endDate":endDate}
                ,cols: [[
                    {field:'accountDateStr', width:180, title: '日期', sort: true}
                    ,{field:'accountTimeStr', width:180, title: '时间'}
                    ,{field:'actionDesc', width:180, title: '摘要', sort: true}
                    ,{field:'baseIn', width:180, title: '入账金额（元）'}
                    ,{field:'baseOut', title: '出账金额（元）', width: 180, sort: true}
                    ,{field:'baseBalance', width:135, title: '余额（元）'}

                ]]
                ,page: true
            });
        }

        $("#btn").click(function () {
            var beginDate = $("#beginDate").val();
            var endDate = $("#endDate").val();
            reloadTable(beginDate,endDate);
        })
    });
</script>
</html>
