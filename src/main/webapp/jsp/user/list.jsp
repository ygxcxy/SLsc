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
                    <input type="text" name="loginCode" required  lay-verify="required" placeholder="请输入关键字" autocomplete="off" class="layui-input">
                </div>
            </div>
            <input type="button" class="layui-btn" id="btn" value="查询">
        </form>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm" lay-event="getCheckData">删除选中行</button>
                <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
            </div>
        </script>
        <table id="test" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
        <script src="${ctx}/layui/layui.js" charset="utf-8"></script>
        <script>
            layui.use(['table','jquery','table','layer'], function(){
                var table = layui.table;
                var $ = layui.jquery;
                var layer = layui.layer;
                $("#btn").click(function () {
                    $.ajax({
                        url:'${ctx}/user/list',
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
                                url:'${ctx}/user/dels',
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
                            layer.msg('选中了：'+ data.length + ' 个');
                            break;
                        case 'isAll':
                            layer.msg(checkStatus.isAll ? '全选': '未全选');
                            break;

                        //自定义头工具栏右侧图标 - 提示
                        case 'LAYTABLE_TIPS':
                            layer.alert('这是工具栏右侧自定义的一个图标按钮');
                            break;
                    };
                });

                table.on('tool(test)', function(obj){

                    var data = obj.data;
                    if(obj.event === 'detail'){
                        layer.msg('ID：'+ data.id + ' 的查看操作');
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
                        layer.alert('编辑行：<br>'+ JSON.stringify(data))
                    }
                });

                //第一个实例
                main();
                function main() {
                    table.render({
                        elem: '#test'
                        ,url: '${ctx}/user/list' //数据接口
                        ,page: true //开启分页
                        ,limit:5
                        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                        ,cols: [[ //表头
                            {type: 'checkbox', fixed: 'left'}
                            ,{field: 'loginCode', title: '用户名', width:180, sort: true, fixed: 'left'}
                            ,{field: 'userName', title: '真实姓名', width:180}
                            ,{field:'mobile', title: '联系电话', width: 200
                                ,templet: function(d){
                                    return d.mobile.toString().substring(0,3)+"****"+d.mobile.toString().substring(7);
                                }
                            }
                            ,{field: 'referCode', title: '推荐人', width:180}
                            ,{field: 'createTime1', title: '注册日期', width: 177}
                            ,{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}

                        ]],
                        where:{
                            loginCode:$("input[name='loginCode']").val()
                        }
                    });
                }
            });
        </script>
    </div>
</div>
</body>
</html>