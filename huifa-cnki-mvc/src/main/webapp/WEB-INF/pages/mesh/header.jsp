<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016-11-30
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
    <div class="head">
        <a class="logo"><img src="${pageContext.request.contextPath}/resources/images/logo.jpg" alt="" title=""></a>
        <nav>
            <ul class="nav-ul">
                <li class="nav-li">
                    <a class="nav-a" href="${pageContext.request.contextPath}/">首页</a></li>
                <li>
                    <a class="nav-a" href="javascript:void(0);">开始检测</a>
                    <div class="nav-list">
                        <a href="javascript:void(0);" class="nav-list-a">系统简介</a>
                        <a href="javascript:void(0);" class="nav-list-a">产品动态</a>
                        <a href="javascript:void(0);" class="nav-list-a">行业动态</a>
                    </div>
                </li>
                <li><a class="nav-a" href="javascript:alert('正在准备中...');">下载秘籍</a></li>
                <li><a class="nav-a" href="javascript:alert('正在准备中...');">论文修改</a></li>
                <li><a class="nav-a" href="javascript:alert('正在准备中...');">优惠活动</a></li>
                <li><a class="nav-a" target="_blank" href="http://check7.cnki.net/codeverify/">真伪查询</a></li>
                <li>
                    <a class="nav-a" href="javascript:void(0);">免登录检测</a>
                    <div class="nav-list">
                        <a href="javascript:void(0);" class="nav-list-a">系统简介</a>
                        <a href="javascript:void(0);" class="nav-list-a">产品动态</a>
                        <a href="javascript:void(0);" class="nav-list-a">行业动态</a>
                    </div>
                </li>
            </ul>
        </nav>
        <div class="nav-top">
            <span>服务电话：</span>
            <i>400-0000-800</i>
            <a class="login-a">登陆</a>
            <a class="registered-a">注册</a>
        </div>
    </div>
</header>