$(window).scroll(function () {
    if ($('.navigation').offset().top > 100) {
        $('.navigation').addClass('nav-bg');
    } else {
        $('.navigation').removeClass('nav-bg');
    }
});

function pedirCartao(){
    window.scrollTo({
        top: 3900,
        left: 0,
        behavior: 'smooth'
    });
}