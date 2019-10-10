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
        <form class="layui-form" method="post" action="${ctx}/user/register" enctype="multipart/form-data">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginCode" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">用户真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">证件类型</label>
                <div class="layui-input-inline">
                    <select name="cardType" lay-verify="required">
                        <c:forEach var="obj" items="${dic}">
                            <option value="${obj.valueId}">${obj.valueName}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="layui-form-label">证件号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="idCard" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">移动电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobile" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">电子邮件</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="text" name="postCode" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">推荐人</label>
                <div class="layui-input-inline">
                    <input type="text" name="auUser.userName" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">收货国家</label>
                <div class="layui-input-inline">
                    <select name="country" lay-verify="required">
                        <c:forEach var="obj" items="${coun}">
                            <option value="${obj.cname}">${obj.cname}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="layui-form-label">开户行</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankName" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开户人</label>
                <div class="layui-input-inline">
                    <input type="text" name="accountHolder" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">开户卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankAccount" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传身份证正面</label>
                <div class="layui-input-block">
                    <input type="file" name="idCardPicPosPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传身份证反面</label>
                <div class="layui-input-block">
                    <input type="file" name="idCardPicNegPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传银行卡</label>
                <div class="layui-input-block">
                    <input type="file" name="bankPicPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">收货地址</label>
                <div class="layui-input-block">
                    <textarea name="userAddress" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="submit" class="layui-btn" value="提交">
                </div>
            </div>
            <script src="${ctx}/layui/layui.js" charset="utf-8"></script>
            <script>
                //JavaScript代码区域
                layui.use(['element', 'form'], function () {
                    var element = layui.element;
                    var form = layui.form;

                });
            </script>
        </form>
    </div>
</div>
</body>
</html>