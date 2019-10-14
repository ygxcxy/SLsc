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
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">商品名称:</label>
                <div class="layui-input-block">
                    <input type="text" value="${goods.goodsName}" readonly name="goodsName" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品编号:</label>
                <div class="layui-input-block">
                    <input type="text" name="goodsSn" readonly value="${goods.goodsSn}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">市场价:</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="marketPrice"value="${goods.marketPrice}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">优惠价：</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="realPrice" value="${goods.realPrice}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">库存</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="num" value="${goods.num}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">单位</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="unit" value="${goods.unit}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <c:if test="${goods.state==1}">
                        <input type="radio" checked disabled name="state" value="1" title="上架">
                    </c:if>

                    <c:if test="${goods.state==2}">
                        <input type="radio" checked disabled name="state" value="2" title="下架">
                    </c:if>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品规格：</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="goodsFormat" value="${goods.goodsFormat}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">商品说明:</label>
                <div class="layui-input-block">
                    <input type="text" readonly name="note" value="${goods.note}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" onclick="javascript:history.back()" class="layui-btn" value="返回">
                </div>
            </div>

        </form>
    </div>
</div>
</body>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'form'], function () {
        var element = layui.element;
        var form = layui.form;

    });
</script>
</html>