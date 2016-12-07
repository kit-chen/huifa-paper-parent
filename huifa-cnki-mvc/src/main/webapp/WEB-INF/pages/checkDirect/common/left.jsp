<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016-12-05
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-----内容部分左侧----->
<div class="zwjc-left">
    <h4 class="left-h">系统检测文献库范围</h4>
    <ul class="def-fw">
        <li>中国学术期刊网络出版总库</li>
        <li>中国博士学位论文全文数据库</li>
        <li>中国优秀硕士学位论文全文数据库</li>
        <li>中国重要会议论文全文数据库</li>
        <li>中国重要报纸全文数据库</li>
        <li>中国专利全文数据库</li>
        <li>互联网资源</li>
        <li>英文数据库(涵盖期刊、博硕、会议的英文数据以及德国Springer、英国Taylor&amp;Francis 期刊数据库等)</li>
        <li>港澳台学术文献库</li>
        <li>优先出版文献库</li>
        <li>互联网文档资源</li>
        <li>图书资源</li>
    </ul>
    <h4 class="left-h">系统检测文献库范围</h4>
    <ul class="def-fw">
        <li>
            <span class="def-size">
                <c:if test="${param.type eq 'amlc'}">1.4</c:if>
                <c:if test="${param.type eq 'pmlc'}">6</c:if>
                <c:if test="${param.type eq 'vip'}">30</c:if>
                万字符以内
            </span>
        </li>
    </ul>
    <h4 class="left-h">联系客服</h4>
    <ul class="def-fw">
        <li>
            <a target="_blank"
               href="http://www.taobao.com/webww/ww.php?ver=3&amp;touid=${param.wangWang}&amp;siteid=cntaobao&amp;status=&amp;charset=utf-8">
                <img border="0"
                     src="http://amos.alicdn.com/online.aw?v=2&uid=${param.wangWang}&site=cntaobao&s=&charset=utf-8"
                     alt="点这里给我发消息"/>&nbsp;
            </a>
        </li>
        <%--<li>QQ:904061096</li>--%>
        <%--<li>电话:15344416639</li>--%>
        <%--<li>邮箱:<br>卖家未设置</li>--%>
    </ul>
    <h4 class="left-h">系统主要适用于</h4>
    <ul class="def-fw">
        <c:if test="${param.type eq 'amlc'}">
            <li>杂志社学术论文投稿发表使用</li>
        </c:if>
        <c:if test="${param.type eq 'pmlc'}">
            <li>本/专科毕业论文</li>
            <li>学校指定使用此系统</li>
        </c:if>
        <c:if test="${param.type eq 'vip'}">
            <li>硕士、博士毕业论文</li>
            <li>学校指定使用此系统</li>
        </c:if>
    </ul>
</div>
