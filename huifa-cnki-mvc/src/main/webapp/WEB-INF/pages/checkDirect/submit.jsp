<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1"/>
    <title>知网检测地址</title>
    <link href="${pageContext.request.contextPath}/resources/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/cnki.style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
</head>
<body>
<!-------检测地址-------->
<div class="head-zwjc">
        <span class="head-zwjc-img">
            <img class="head-zwjc-img01" src="${pageContext.request.contextPath}/resources/images/img01.gif" alt=""
                 title="">
            <img class="head-zwjc-img02" src="${pageContext.request.contextPath}/resources/images/img02.gif" alt=""
                 title="">
        </span>
    <span class="head-zwjc-s">
            <div class="head-zwjc-nav">
                <ul class="head-zwjc-ul">
                    <li class="head-zwjc-li"><a href="知网检测地址-提交检测.html">提交检测</a></li>
                    <li><a href="知网检测地址-查询结果.html">查询结果</a></li>
                    <li><a href="">下载秘籍</a></li>
                    <li><a href="">常见问题</a></li>
                    <li><a href="">卖家店铺</a></li>
                    <li><a href="">收藏系统</a></li>
                </ul>
            </div>
        </span>
</div>
<div class="bd-zwjc">
    <!-----内容部分左侧----->
    <jsp:include page="common/left.jsp">
        <jsp:param name="type" value="${type}"></jsp:param>
        <jsp:param name="wangWang" value="${seller.sellerNick}"></jsp:param>
    </jsp:include>
    <!-----内容部分右侧----->
    <div class="zwjc-right">
        <div class="zwijc-top">
            <button class="close zwjc-top-x">×</button>
            <span class="zwijc-top-s">温馨提示</span>
            <div class="zwijc-top-d">
                <p>1、现在处于毕业高峰期，检测的同学们比较多，所以知网论文检测系统检测所需时间较长，如果您的报告没有及时检测完成，请耐心等候！ </p>
                <p>2、提交完待测文章后，可以关闭检测页面，先休息会，过一阵再次访问检测系统，通过订单编号提取检测结果哦!</p>
                <p style="color:red">3、如果需要检测多篇文章，请将论文标题进行合理区分，如 1_小明_信息管理系统的设计1.doc / 2_小明_信息管理系统的设计2.doc /
                    ......,以此类推。</p>
            </div>
        </div>
        <div class="zwjc-main">
            <ul class="zwjc-main-nav">
                <li class="zwjc-nav01"><a href="javascript:void(0);">单篇上传</a></li>
                <li class="zwjc-nav02 zwjc-main-nav-li"><a href="javascript:void(0);">【多篇上传】</a></li>
                <li><a href="知网检测地址-查询结果.html">查询结果</a></li>
            </ul>
            <!-----订单信息----->
            <div class="information">
                <div class="sjx sjx-left"></div>
                <h3><i class="iconfont">&#xe63a;</i>订单信息</h3>
                <ul>
                    <li><span>订单件数：</span><i class="color01 order-total-num">0</i></li>
                    <li><span>已用件数：</span><i class="color02 order-used-num">0</i></li>
                    <li><span>可用件数：</span><i class="color03 order-able-num">0</i></li>
                </ul>
            </div>
            <!-----单篇上传----->
            <div class="zwjc-main-nr zwjc-nr01" style="display: none;">
                <ul class="zwjc-main-nr-ul">
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe604;</i>订单编号</span>
                        <div class="zwjc-main-nr-d">
                            <label class="zwjc-la">
                                <input type="text" name="tid" class="single-submit-tid" placeholder="请先输入在店铺购买的订单编号">
                            </label>
                            <span class="zwjc-im">[必填]</span>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe79e;</i>论文标题</span>
                        <div class="zwjc-main-nr-d">
                            <label class="zwjc-la">
                                <input type="text" name="title" disabled="disabled" placeholder="填写好论文标题">
                            </label>
                            <span class="zwjc-im">[必填]</span>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe62a;</i>论文作者</span>
                        <div class="zwjc-main-nr-d">
                            <label class="zwjc-la">
                                <input type="text" name="author" disabled="disabled" placeholder="填写好论文作者">
                            </label>
                            <span class="zwjc-im">[必填]</span>
                            <div class="clear"></div>
                            <p class="zwjc-wxts"><em>温馨提示</em>如果您这篇文章已发表过，或引用过您自己之前发表过的文章，请填写好作者真实姓名. </p>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe748;</i>待测论文</span>
                        <div class="zwjc-main-nr-d">
                            <strong class="no-choose">未选择文件</strong>
                            <p class="zwjc-choose"></p>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe65e;</i>选择论文</span>
                        <div class="zwjc-main-nr-d">
                            <div class="zwjc-box">
                                <label class="zwjc-box-bt zwjc-box-bt01">
                                    <input type="file" name="checkFile" id="single-file" disabled="disabled">
                                    <i class="iconfont">&#xe686;</i><span>选择文件...</span>
                                </label>
                                <button class="zwjc-box-bt zwjc-box-bt02">
                                    <i class="iconfont">&#xe748;</i><span>提交检测</span>
                                </button>
                                <a class="zwjc-box-bt zwjc-box-bt03" href="${seller.buyerLink}">
                                    <i class="iconfont">&#xe632;</i><span>去淘宝购买</span>
                                </a>
                            </div>
                            <span class="zwjc-im">[必填]</span>
                        </div>
                    </li>

                </ul>
            </div>
            <!-----多篇上传----->
            <div class="zwjc-main-nr zwjc-nr02">
                <ul class="zwjc-main-nr-ul">
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe600;</i>标题命名</span>
                        <div class="zwjc-main-nr-d">
                            <p class="zwjc-main-nr-p1"><strong>上传的检测文献文件名请按以下格式命名，有助于系统提取作者姓名。</strong></p>
                            <p class="zwjc-main-nr-p1">格式：随意数字_作者姓名_文献标题.doc</p>
                            <p class="zwjc-main-nr-p2">例如：1_小明_信息管理系统的设计1.doc</p>
                            <p class="zwjc-main-nr-p2" style="padding-left: 35px;">2_小明_信息管理系统的设计2.doc</p>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe604;</i>订单编号</span>
                        <div class="zwjc-main-nr-d">
                            <label class="zwjc-la">
                                <input type="text" value="" placeholder="请先输入在店铺购买的订单编号">
                            </label>
                            <span class="zwjc-im">[必填]</span>
                        </div>
                    </li>

                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe748;</i>待测论文</span>
                        <div class="zwjc-main-nr-d">
                            <!--<strong class="no-choose">未选择文件</strong>-->
                            <p class="zwjc-choose">
                                本系统支持多文件选择上传，或者直接将文档拖动到浏览器页面即可（拖拽仅支持高版本浏览器，如谷歌、IE9式及以上或高速模式），选择完成后，下方将显示待上传文件列表。</p>
                        </div>
                    </li>
                    <li>
                        <span class="zwjc-nub"><i class="iconfont">&#xe65e;</i>选择论文</span>
                        <div class="zwjc-main-nr-d">
                            <div class="zwjc-box">
                                <label class="zwjc-box-bt zwjc-box-bt01">
                                    <input type="file" name="checkFile" id="multipart-file">
                                    <i class="iconfont">&#xe686;</i><span>选择文件...</span>
                                </label>
                                <button class="zwjc-box-bt zwjc-box-bt02">
                                    <i class="iconfont">&#xe748;</i><span>提交检测</span>
                                </button>
                                <a class="zwjc-box-bt zwjc-box-bt04" href="">
                                    <i class="iconfont">&#xe632;</i><span>取消已选择文件</span>
                                </a>
                            </div>
                            <span class="zwjc-im">[必填]</span>
                        </div>
                    </li>

                </ul>
            </div>
            <jsp:include page="common/submit_tips.jsp">
                <jsp:param name="type" value="${type}"></jsp:param>
            </jsp:include>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp">
    <jsp:param name="type" value="${type}"></jsp:param>
</jsp:include>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/direct/cnki.check.js"></script>
</html>