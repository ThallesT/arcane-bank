$(document).on('click', 'a[endpoint]', function (event) {
    var endpoint = $(this).attr('endpoint');
    $("#content").load("/"+endpoint);
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