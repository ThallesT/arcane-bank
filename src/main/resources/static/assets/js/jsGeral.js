$(document).on('click', 'a[endpoint]', function (event) {
    var endpoint = $(this).attr('endpoint');
    $("#divConteudo").load("/"+endpoint);
});

$(document).on('click', '.nav-item', function (event) {
    $('.active').removeClass("active");
    $(this).addClass("active");
});

$(document).on('click', 'a[menu]', function (event) {
    $('.active').removeClass("active");
    var classe = '.menu-'+$(this).attr('menu');
    $(classe).addClass("active");
});

$(document).on('submit', '.form-ajax', function (event) {
    var form = event.target;
    ajaxSubmitForm(form);
    return false;
});


$(document).on('submit', '.img-ajax', function (event) {
    var form = event.target;
    ajaxUploadForm(form);
    return false;
});


function ajaxSubmitForm(form) {
    var container = $(form).attr('data-target');
    var containerId = '#' + container;
    var url = $(form).attr('action');

    $.ajax({
        type: "POST",
        url: url,
        data: $(form).serialize(), // serializes the form's elements.
        success: function (data) {
            $(containerId).html(data);
        }
    })

        .fail(function () {

        });

}

$(document).on('submit', '.form-transferencia', function (event) {
    var quantidade = $('.quantidade').val();
    var saldo = $('.saldo').attr('saldo');
    var saldoT = parseFloat(saldo);
    var quantidadeFormatada = parseFloat(quantidade.toString().replace(".", "").replace(",", "."));
    if(quantidadeFormatada <= saldoT){
        var form = event.target;
        ajaxSubmitForm(form);
        return false;
    }else{
        alert('saldo insuficiente')
    }
});


function ajaxUploadForm(form) {
    var formData = new FormData(form);
    var container = $(form).attr('data-target');
    var containerId = '#' + container;
    var url = $(form).attr('action');

    $.ajax({
        url: url,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (data) {
            $(containerId).html(data);
        },
        fail: function() {
            alert("Servidor em baixo ou falha de rede");
        }
    });
}

function ajaxBuscaFoto(idUsuario) {
    var containerId = '#divDaFoto';
    var url = '/recupera-foto/'+idUsuario;

    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            $('#iconeDaFoto').remove();
            $(containerId).html(data);
        },
        fail: function() {
            alert("Erro ao carregar a foto");
        }
    });
}