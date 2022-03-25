$(function(){
    $("nav>ul>li").hover(function(){
        $(this).children(".subMenu").slideToggle();
    });

    $(".menubar").click(function(){
        $("nav>ul").slideToggle();
    });
});

$(window).resize(function(){
    var windowWidth = $(window).width();
    if(windowWidth > 768){
        $("nav>ul").css('display','flex');
    }
    else {$("nav>ul").css('display','none');}
});

