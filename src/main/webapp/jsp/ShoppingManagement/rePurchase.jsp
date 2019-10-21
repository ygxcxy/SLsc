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
            <h4>提现明细</h4>
            <hr>
            <form method="post">
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label class="layui-form-label">编号</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" id="code">
                        </div>
                    </div>

                    <div class="layui-col-xs3">
                        <label class="layui-form-label">套餐标题</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" id="title">
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

        reloadTable(null,null);
        function reloadTable(beginDate,endDate){
            table.render({
                elem: '#test'
                ,url:'${ctx}/cash/showCashByEndDate'
                ,where:{"beginDate":beginDate,"endDate":endDate}
                ,cols: [[
                    {field:'cashId', width:180, title: '编号', sort: true}
                    ,{field:'cashTimeStr', width:180, title: '日期', sort: true}
                    ,{field:'cashMoney', width:180, title: '提现金额（元）'}
                    ,{field:'creditedMoney', width:180, title: '到账金额（元）', sort: true}
                    ,{field:'stat', width:180, title: '状态'
                        ,templet: function(d){
                            // 0申请提现,1审核通过,2转账完毕
                            if(d.stat == 0){
                                return "申请提现";
                            }else  if(d.stat == 1){
                                return "审核通过";
                            }else  if(d.stat == 2){
                                return "转账完毕";
                            }else{
                                return"";
                            }
                        }
                    }

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
