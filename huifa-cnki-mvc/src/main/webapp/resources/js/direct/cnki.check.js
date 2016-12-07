

$(function () {
    $(".zwjc-nav01").click(function () {
        $(this).addClass("zwjc-main-nav-li");
        $(".zwjc-nav02").removeClass("zwjc-main-nav-li");
        $(".zwjc-nr01").show();
        $(".zwjc-nr02").hide();
    });
    $(".zwjc-nav02").click(function () {
        $(this).addClass("zwjc-main-nav-li");
        $(".zwjc-nav01").removeClass("zwjc-main-nav-li");
        $(".zwjc-nr02").show();
        $(".zwjc-nr01").hide();
    });

    //关闭提示信息
    $(".zwjc-top-x").click(function () {
        $(".zwijc-top").fadeOut();
    });

    $('.single-submit-tid').on('valuechange', function (e, previous) {
        var currentValue = $(this).val();
        getTaoBaoOrder(currentValue,"zwjc-nr01");
    });
});

function getTaoBaoOrder(tid, tabs) {
    if (tid && tid.length >= 16) {
        $.ajax({
            url: PageCommon.contextPath + "/taobao/getTaoBaoOrder.html",
            data: {"tid": tid},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.status == 'success') {
                    $(".order-total-num,.order-able-num").text("1");
                    if (tabs == 'zwjc-nr01') {
                        $(".zwjc-nr01 input[name='title']").removeAttr("disabled");
                        $(".zwjc-nr01 input[name='author']").removeAttr("disabled");
                        $("#single-file").removeAttr("disabled");
                    } else if (tabs == 'zwjc-nr02') {
                        $("#multipart-file").removeAttr("disabled");
                    }
                } else {
                    if (tabs == 'zwjc-nr01') {
                        $(".zwjc-nr01 input[name='title']").attr("disabled", true);
                        $(".zwjc-nr01 input[name='author']").attr("disabled", true);
                        $("#single-file").attr("disabled", true);
                    } else if (tabs == 'tab2') {
                        $("#multipart-file").attr("disabled", true);
                    }
                    $(".order-total-num,.order-used-num").text("1");
                    $(".order-able-num").text("0");
                }
            }
        });
    } else {
        if (tabs == 'zwjc-nr01') {
            $(".zwjc-nr01 input[name='title']").attr("disabled", true);
            $(".zwjc-nr01 input[name='author']").attr("disabled", true);
            $("#single-file").attr("disabled", true);
        } else if (tabs == 'zwjc-nr02') {
            $("#multipart-file").attr("disabled", true);
        }
        $(".order-total-num,.order-used-num").text("0");
        $(".order-able-num").text("0");
    }
}