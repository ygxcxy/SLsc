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
                <div class="layui-input-block">
                    <input type="text" name="goodsName" required  lay-verify="required" placeholder="请输入关键字" autocomplete="off" class="layui-input">
                </div>
            </div>
            <input type="button" class="layui-btn" id="btn" value="查询">
        </form>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="getCheckData">删除选中行</button>
                <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">新增</button>
            </div>
        </script>
        <table id="test" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
            <a class="layui-btn layui-btn-xs" lay-event="query">查看</a>
        </script>
        <script type="text/html" id="checkboxTpl">
            <input type="checkbox" name="state" value="{{d.state}}" title="上架" lay-filter="lockDemo" {{ d.state == 1 ? 'checked' : '' }}>
        </script>

    </div>
</div>
</body>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['table','jquery','table','layer'], function(){
        var table = layui.table;
        var $ = layui.jquery;
        var layer = layui.layer;
        $("#btn").click(function () {
            $.ajax({
                url:'${ctx}/sys/goods/list',
                type:'post',
                success:function (data) {
                    main()
                }
            })
        });
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    //获取选中行的数据，以json形式
                    var data = checkStatus.data;
                    // layer.alert(JSON.stringify(data));
                    var ids = [];
                    for(var i = 0;i<data.length;i++){
                        ids.push(data[i].id);
                    }
                    var obj = {'ids':ids};
                    $.ajax({
                        url:'${ctx}/sys/goods/dels',
                        type:'post',
                        data:JSON.stringify(ids),
                        contentType:"application/json",
                        success:function (data) {
                            if(data.code==4000){
                                layer.msg(data.msg);
                            }else{
                                layer.msg(data.msg);
                                main();
                            }
                        }
                    });
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    location.href="${ctx}/sys/goods/to/add";
                    break;
                case 'isAll':
                    location.href="${ctx}/sys/to/updateUser";
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });

        table.on('tool(test)', function(obj){

            var data = obj.data;
            if(obj.event === 'query'){
                location.href = "${ctx}/sys/goods/to/query?id="+data.id
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url:'${ctx}/user/del/'+data.id,
                        type:'post',
                        success:function (data) {
                            if(data.code==4000){
                                layer.msg(data.msg);
                            }else{
                                layer.msg(data.msg);
                                main();
                            }
                        }
                    })
                });
            } else if(obj.event === 'edit'){
                // layer.alert('编辑行：<br>'+ JSON.stringify(data))
                location.href="${ctx}/sys/goods/to/updateGoods?id="+data.id;
            }
        });

        //第一个实例
        main();
        function main() {
            table.render({
                elem: '#test'
                ,url: '${ctx}/sys/goods/list' //数据接口
                ,page: true //开启分页
                ,limit:5
                ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                ,cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}
                    ,{field: 'goodsName', title: '商品名称', width:180, sort: true, fixed: 'left'}
                    ,{field: 'marketPrice', title: '市场价(元)', width:180}
                    ,{field: 'realPrice', title: '优惠价(元)', width:180}
                    ,{field:'num', title: '库存', width: 200 }
                    ,{field: 'lastTime1', title: '最后修改时间', width: 177}
                    ,{field:'lock', title:'状态(上架/下架)', width:110, templet: '#checkboxTpl', unresize: true}
                    ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}
                ]],
                where:{
                    goodsName:$("input[name='goodsName']").val()
                }
            });
        }
    });
</script>
</html>