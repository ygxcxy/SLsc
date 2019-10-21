
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
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <div class="layui-col-xs6">
                    <label class="layui-form-label">报单购货会员用户名:</label>
                    <div class="layui-input-block">
                        <input type="text" name="shoppingCode" required  lay-verify="required" placeholder="请输入会员用户名" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>


            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="btn" value="下一步"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','element','jquery','layer'],function(){
        var element = layui.element;
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        $("#btn").click(function () {

            var shoppingCode = $("input[name='shoppingCode']").val();

            if(shoppingCode == ""){
                layer.msg("请输入报单会员用户名！");
                return;
            }

            $.ajax({
                url:"${ctx}/order/checkUserByCode",
                data:{"shoppingCode":shoppingCode},
                type:"post",
                success:function(data){
                    if(data.code==2000){
                        window.location.href = "${ctx}/order/toTaxationForm?shoppingCode="+shoppingCode;
                    }
                    if(data.code==4000){
                        layer.msg(data.msg)
                    }
                }
            })


        })
    });
</script>
</html>