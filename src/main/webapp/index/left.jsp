<%--
  Created by IntelliJ IDEA.
  User: Shaochenjie
  Date: 2019/10/7
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item ">
                    <a class="" href="javascript:;">后台管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${ctx}/sys/user/to/list">用户管理</a></dd>
                        <dd><a href="${ctx}/sys/role/to/list">角色管理</a></dd>
                        <dd><a href="${ctx}/sys/fun/to/list">权限管理</a></dd>
                        <dd><a href="${ctx}/sys/goods/to/list">商品管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">会员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${ctx}/user/to/register">注册新会员</a></dd>
                        <dd><a href="${ctx}/user/to/list">新会员管理</a></dd>
                        <dd><a href="${ctx}/user/to/updateUser">修改本人资料</a></dd>
                        <dd><a href="${ctx}/user/to/updatePwd">修改本人密码</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">电子银行</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${ctx}/account/toRecharge">汇款充值</a></dd>
                        <dd><a href="${ctx}/account/toAccountLog">基本账户</a></dd>
                        <dd><a href="javascript:;">消费账户</a></dd>
                        <dd><a href="${ctx}/account/toInternalTransfer">内部转账</a></dd>
                        <dd><a href="javascript:;">提现明细</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">购物管理</a>
                    <dl class="layui-nav-child">

                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">信息查询</a>
                    <dl class="layui-nav-child">

                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">咨询管理</a>
                    <dl class="layui-nav-child">

                    </dl>
                </li>
            </ul>
        </div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item ">
                <a class="" href="javascript:;">后台管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${ctx}/sys/user/to/list">用户管理</a></dd>
                    <dd><a href="${ctx}/sys/role/to/list">角色管理</a></dd>
                    <dd><a href="${ctx}/sys/fun/to/list">权限管理</a></dd>
                    <dd><a href="${ctx}/sys/goods/to/list">商品管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a class="" href="javascript:;">会员管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${ctx}/user/to/register">注册新会员</a></dd>
                    <dd><a href="${ctx}/user/to/list">新会员管理</a></dd>
                    <dd><a href="${ctx}/user/to/updateUser">修改本人资料</a></dd>
                    <dd><a href="${ctx}/user/to/updatePwd">修改本人密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">电子银行</a>
                <dl class="layui-nav-child">
                    <dd><a href="${ctx}/account/toRecharge">汇款充值</a></dd>
                    <dd><a href="${ctx}/account/toAccountLog">基本账户</a></dd>
                    <dd><a href="javascript:;">消费账户</a></dd>
                    <dd><a href="${ctx}/account/toInternalTransfer">内部转账</a></dd>
                    <dd><a href="${ctx}/account/toCashWithdrawal">申请提现</a></dd>
                    <dd><a href="javascript:;">提现明细</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">购物管理</a>
                <dl class="layui-nav-child">

                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">信息查询</a>
                <dl class="layui-nav-child">

                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">咨询管理</a>
                <dl class="layui-nav-child">

                </dl>
            </li>
        </ul>
    </div>
</div>
</div>


