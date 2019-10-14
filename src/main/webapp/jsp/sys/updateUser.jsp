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
        <form class="layui-form" method="post" action="${ctx}/sys/user/updateUser" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${user.id}">

            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-inline">
                    <select name="roleId" lay-filter="roleId"  lay-verify="required">
                        <c:forEach var="obj" items="${role}">
                            <option value="${obj.id}" <c:if test="${user.roleId eq obj.id}">selected</c:if>>${obj.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="layui-form-label">会员类型</label>
                <div class="layui-input-inline">
                    <select name="userType" lay-filter="userType" lay-verify="required">

                        <c:if test="${user.roleId==1}">
                            <option value="0">请选择</option>
                        </c:if>
                        <c:if test="${user.roleId!=1}">
                            <option value="0">请选择</option>
                        <c:forEach var="obj" items="${data}">
                            <option value="${obj.valueId}" <c:if test="${user.userType eq obj.valueId}">selected</c:if>>${obj.valueName}</option>
                        </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">

                <label class="layui-form-label">证件类型</label>
                <div class="layui-input-inline">
                    <select name="cardType"  lay-verify="required">
                        <option value="0">请选择</option>
                        <c:forEach var="obj" items="${card}">
                            <option value="${obj.valueId}" <c:if test="${user.cardType eq obj.valueId}">selected</c:if>>${obj.valueName}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="layui-form-label">证件号码</label>
                <div class="layui-input-inline">
                    <input type="text" name="idCard" value="${user.idCard}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">

            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" value="${user.loginCode}" name="loginCode" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">用户真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" value="${user.userName}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">移动电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobile" value="${user.mobile}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">电子邮件</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" value="${user.email}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮政编码</label>
                <div class="layui-input-inline">
                    <input type="text" name="postCode" value="${user.postCode}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">收货国家</label>
                <div class="layui-input-inline">
                    <select name="country" lay-verify="required">
                        <c:forEach var="obj" items="${coun}">
                            <option value="${obj.cname}" <c:if test="${user.country eq obj.cname}">selected</c:if>>${obj.cname}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="layui-form-label">开户行</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankName" value="${user.bankName}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开户人</label>
                <div class="layui-input-inline">
                    <input type="text" name="accountHolder" value="${user.accountHolder}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <label class="layui-form-label">开户卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="bankAccount" value="${user.bankAccount}" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传身份证正面</label>
                <div class="layui-input-block">
                    <input type="file" name="idCardPicPosPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                    <img src="${ctx}/logo/${user.idCardPicPosPath}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传身份证反面</label>
                <div class="layui-input-block">
                    <input type="file" name="idCardPicNegPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                    <img src="${ctx}/logo/${user.idCardPicNegPath}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传银行卡</label>
                <div class="layui-input-block">
                    <input type="file" name="bankPicPathFile" lay-verify="required" placeholder="请输入标题"
                           autocomplete="off" class="layui-input">
                    <img src="${ctx}/logo/${user.bankPicPath}">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">收货地址</label>
                <div class="layui-input-inline">
                    <input type="text" value="${user.userAddress}" name="userAddress" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="submit" class="layui-btn" value="提交">
                </div>
            </div>

        </form>
    </div>
</div>
</body>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'form','jquery'], function () {
        var element = layui.element;
        var form = layui.form;
        var $ = layui.jquery;
        form.on("select(roleId)",function (data) {
            var pid = data.value;
            $("select[name='userType']").empty();
            $.ajax({
                url:'${ctx}/sys/user/userType',
                type:'post',
                success:function (data) {
                    var html ='<option value="0">-请选择-</option>';
                    for (i = 0;i<data.data.length;i++){
                        html +="<option value="+data.data[i].valueId+">"+data.data[i].valueName+"</option>"
                    }
                    if(pid !=1){
                        $("select[name='userType']").html(html);
                    }
                    form.render();
                }
            });
        })
    });
</script>
</html>