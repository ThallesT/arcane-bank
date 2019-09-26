$(window).scroll(function () {
    if ($('.navigation').offset().top > 100) {
        $('.navigation').addClass('nav-bg');
    } else {
        $('.navigation').removeClass('nav-bg');
    }
});

function pedirCartao(){
    var offset = $('#divFormConta').offset();
    window.scrollTo({
        top: offset.top -250,
        left: 0,
        behavior: 'smooth'
    });
}

$(document).on('keyup','.confirma-senha', function () {
    var senha =  $('#senha').val();
    var confirmaSenha =  $('#confirma-senha').val();
    if (senha != confirmaSenha) {
        $("#botao-finaliza").attr('disabled', "");
        $('#senha').addClass("alert-danger")
        $('#confirma-senha').addClass("alert-danger")
    } else {
        $("#botao-finaliza").removeAttr('disabled');
        $('#senha').removeClass("alert-danger")
        $('#confirma-senha').removeClass("alert-danger")
    }
});