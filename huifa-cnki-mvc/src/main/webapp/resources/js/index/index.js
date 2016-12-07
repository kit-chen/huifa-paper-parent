$(function(){

    $(".nav-list").mouseleave(function(){
        $(this).slideUp();
    });

    $(".nav-a").mouseover(function(){
        $(".nav-list").slideUp();
        $(this).parent("li").find(".nav-list").stop(true,false).slideToggle();
    });

});
//点击出现下拉函数
function navList(t){
    $(".nav-list").slideUp();
    // $(this).next().stop(true,false).slideToggle();
    $(".nav-list" + t).stop(true, false).slideToggle();
};