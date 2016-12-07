<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016-12-05
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="foot-zwjc">
    <div class="foot-zwjc-bd">
        <p class="foot-zwjc-p">
            <c:if test="${type eq 'amlc'}">
                <strong>知网期刊学术不端文献检测系统</strong>知网期刊系统针对编辑部来稿，已发表的文献，学校、事业单位职称论文检测的检测!是国内大部分杂志社专用文献抄袭检测系统，并非学位论文系统。本系统以《中国学术文献网络出版总库》为全文比对数据库，可检测抄袭与剽窃、伪造、篡改、不当署名、一稿多投等学术不端文献，可供期刊编辑部检测来稿和已发表的文献。
            </c:if>
            <c:if test="${type eq 'pmlc'}">
                <strong>中国知网大学生论文管理系统</strong>知网PMLC 知网PMLC系统，也叫大学生论文抄袭检测系统，是专门用于大学生（本科、专科等）论文检测的系统， 在限制字符范围能也可检测硕士论文；大多数本科院校都是用PMLC系统来检测本科生论文；PMLC的数据库比知网VIP多了一个“大学生联合对比库”，因此一定程序上与知网VIP结果很接近 最多检测4万字符。
            </c:if>
            <c:if test="${type eq 'vip'}">
                <strong>知网学术不端文献检测系统</strong> 学术不端检测5.1（一般习惯叫做知网vip），100000000篇中文文献，1000万篇各类独家文献，300万港澳台地区学术文献4000万篇英文文献资源，20个亿中英文互联网资源是全国高校用来检测硕博论文的检测系统，检测范围广，数据来源权威，检测算法严格!
            </c:if>

        </p>
        <p class="foot-zwjc-list">
            <a href="javascript:void(0);" target="_blank">网站首页</a>&nbsp;&nbsp;|
            <a href="javascript:void(0);">关于我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">实用帮助</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">相关文档</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">报告样本</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">报告验证</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">常见问题</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="javascript:void(0);">免责声明</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        </p>
    </div>
    <div class="foot-zwjc-btm">
        <p>
            Copyright © 2014-2015 【知网检测】. &nbsp; | &nbsp;
            <img class="foot-zwjc-btm-img" src="" style="margin-right: 3px; margin-bottom: 3px;" height="20" width="20"> Copyright ©
            2012-2014 paperfree.cn All Rights Reserved QQ联系: 396772284
        </p>
    </div>
</div>