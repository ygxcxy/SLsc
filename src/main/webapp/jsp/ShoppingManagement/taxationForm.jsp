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
        <form class="layui-form" method="post" action="#" enctype="multipart/form-data">
            <div class="layui-form-item">
                <div class="layui-col-xs3">
                    <label class="layui-form-label">当前余额：</label>
                </div>
                <div class="layui-col-xs3">
                    <label class="layui-form-label">${account.balance}元</label>
                    <input type="hidden" name="balance" class="layui-input" value="${account.balance}">

                </div>
                <div class="layui-col-xs3">
                    <label   style="width: 500px;display:inline-block;margin-top: 10px;margin-left: 35px" >
                        <a href="${ctx}/account/toRecharge" style="color: blue;">余额不足？ 马上去汇款充值</a>
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-xs3">
                    <label style="width: 200px;display:inline-block;margin-top: 10px;margin-left: 24px">报单购货会员用户名:</label>

                </div>
                <div class="layui-col-xs6">
                    <label style="width: 200px;display:inline-block;margin-top: 10px;margin-left: 40px">${shoppingUser.loginCode}</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-xs3">
                    <label  style="width: 500px;display:inline-block;margin-top: 10px;margin-left: 25px">
                        以下报单級別必须选择其一:
                    </label>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-col-xs3">
                    <div class="layui-input-block">
                        <input name="order"  title="消費会员:" lay-filter="erweima" type="radio" checked value="4600#300#690">
                    </div>
                </div>
                <div class="layui-col-xs3">
                    <div class="layui-input-block">
                        <input name="order" title="VIP会员:" lay-filter="erweima" type="radio" value="14600#900#1500">
                    </div>
                </div>
                <div class="layui-col-xs3">
                    <div class="layui-input-block">
                        <input name="order" title="加盟店:" lay-filter="erweima" type="radio" value="28800#1500#2800">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-col-xs3">
                    <label style="width: 200px;display:inline-block;margin-top: 10px;margin-left: 115px">
                        消费金额：4600元<br/>
                        PV ： 300<br/>
                        综合服务费： 690元<br/>
                    </label>
                </div>
                <div class="layui-col-xs3">
                    <label style="width: 200px;display:inline-block;margin-top: 10px;margin-left: 115px">
                        消费金额 ：14600元<br/>
                        PV： 900<br/>
                        综合服务费： 1500元<br/>
                    </label>
                </div>
                <div class="layui-col-xs3">
                    <label style="width: 200px;display:inline-block;margin-top: 10px;margin-left: 115px">
                        消费金额：28800元<br/>
                        PV：1500<br/>
                        服务费 2800元<br/>
                    </label>
                </div>
            </div>
        </form>

            <div id="showInfo">
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label  class="layui-form-label">
                            收货地址：
                        </label>
                        <div class="layui-input-block">
                            <label   style="width: 300px;font-size: 15px;display:inline-block;margin-left: 15px;margin-top: 10px">
                                <span id="country">${shoppingUser.country}</span>
                            </label>
                        </div>
                    </div>
                    <div class="layui-col-xs1">
                        <label   style="width: 300px;font-size: 15px;display:inline-block;margin-left: 15px;margin-top: 10px">
                            <span id="userAddress">${shoppingUser.userAddress}</span>
                        </label>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label  class="layui-form-label">
                            快递类型：
                        </label>
                        <div class="layui-input-inline">
                            <label  class="layui-form-label" style="width: 60px;font-size: 15px;display:inline-block;">
                                <span id="shipType">EMS</span>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label  class="layui-form-label">
                            邮编：
                        </label>
                        <div class="layui-input-block">
                            <label  class="layui-form-label" style="width: 20px;font-size: 15px;display:inline-block;">
                                <span id="postCode">${shoppingUser.postCode}</span>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="button" class="layui-btn" id="modAddress" value="修改"/>
                    </div>
                </div>
            </div>

            <div id="modInfo"  style="display: none;">
                <form action="#" id="orderInfo" class="layui-form" method="post">

                    <div class="layui-form-item" style="display: none;">
                        <input type="hidden" name="pickUserId" class="layui-input" value="${shoppingUser.id}">
                        <input type="hidden" name="buyUserId" class="layui-input" value="${sessionScope.user.id}">
                        <input type="hidden" name="mobile" class="layui-input" value="${shoppingUser.mobile}">
                        <input type="hidden" name="email" class="layui-input" value="${shoppingUser.email}">
                        <input type="hidden" name="purchaseFee" class="layui-input" value="4600">
                        <input type="hidden" name="basePv" class="layui-input" value="300">
                        <input type="hidden" name="shipNote" class="layui-input" value="消費会员">
                        <input type="hidden" name="serviceFee" class="layui-input" value="690">

                        <input type="hidden" name="totalPrice" class="layui-input" value="5290">
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-col-xs3">
                            <label  class="layui-form-label">
                                收货地址：
                            </label>
                            <div class="layui-input-inline">
                                <select name="country">
                                    <c:forEach items="${countryList}" var="obj">
                                        <option value="${obj.cname}" <c:if test="${shoppingUser.country eq obj.cname}">selected</c:if>>${obj.cname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-col-xs6">
                            <label  class="layui-form-label">
                                收货地址：
                            </label>
                            <div class="layui-input-block">
                                <input name="userAddress" class="layui-input" value="${shoppingUser.userAddress}">
                            </div>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-col-xs3">
                        <label  class="layui-form-label">
                            快递类型：
                        </label>
                        <div class="layui-input-inline">
                            <select name="shipType">
                                <c:forEach items="${byTypeCode}" var="obj">
                                    <option value="${obj.valueName}">${obj.valueName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    </div>

                    <div class="layui-form-item">
                    <div class="layui-col-xs3">
                        <label  class="layui-form-label">
                            邮编：
                        </label>
                        <div class="layui-input-inline">
                            <input name="postcode" class="layui-input" value="${shoppingUser.postCode}">
                        </div>
                    </div>
                </div>

                    <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input type="button" class="layui-btn" id="save" value="保存"/>
                    </div>
                </div>
                </form>
            </div>


            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" class="layui-btn" id="sub" value="立即提交"/>
                </div>
            </div>

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

        $("#modAddress").click(function () {
            $("#showInfo").hide();
            $("#modInfo").show();
        })
        $("#save").click(function () {
            $("#modInfo").hide();
            $("#showInfo").show();


            var userAddress = $("input[name=userAddress]").val();
            var shipType = $("select[name=shipType]").val();
            var postcode = $("input[name=postcode]").val();
            var country = $("select[name=country]").val();


            $("#userAddress").text(userAddress);
            $("#shipType").text(shipType);
            $("#postcode").text(postcode);
            $("#country").text(country);
        })

        form.on('radio(erweima)', function (data) {
            shipNote
            var strs = data.value.split("#");
            var shipNote = $(this).attr("title").split(":")[0];
            //下别0为消费金额，1为PV，2为服务费
            $("input[name=purchaseFee]").val(strs[0]);
            $("input[name=basePv]").val(strs[1]);
            $("input[name=serviceFee]").val(strs[2]);
            $("input[name=shipNote]").val(shipNote);
            $("input[name=totalPrice]").val(parseInt(strs[0])+parseInt(strs[2]));

        });

        $("#sub").click(function () {
            var totalPrice = parseInt($("input[name=totalPrice]").val());
            var balance = parseInt($("input[name=balance]").val());
            if(totalPrice > balance){
                layer.msg("余额不足，请先汇款充值！");
                return;
            }
            $.ajax({
                url:"${ctx}/order/addOrderInfo",
                data:$("#orderInfo").serialize(),
                success:function (data) {
                    if(data.code == 2000){
                        layer.open({
                            content:data.msg+'<br\>马上去激活购货吗？',
                            btn: ["去激活", "暂不"],
                            yes: function (index) {
                                window.location.href="${ctx}/order/toGetCode";
                            },
                            btn2: function (index) {
                                window.location.href="${ctx}/order/toGetCode";
                            }
                        })
                    }else{
                        layer.msg(data.code);
                    }
                }
            })
        })
    });
</script>
</html>