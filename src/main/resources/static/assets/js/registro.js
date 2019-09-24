$(document).ready(function () {
    $('#formulario').validate({
        rules: {
            nome: {
                required: true,
                minlength: 3
            },
            email: {
                required: true,
                email: true
            },
            senha: {
                required: true
            },
            confirmar_senha: {
                required: true,
                equalTo: "#senha"
            },
            matricula: {
                required: true,
                equalTo: "#matricula"
            }
        }
    });
});
$('.password, .confirm_password').on('keyup', function () {
    if ($('.password').val() == $('.confirm_password').val()) {
        $('#message').html('Tudo certo').css('color', 'green');
        $('#botaoRegistra').removeAttr('disabled');
    } else {
        $('#message').html('As senhas não correspondem').css('color', 'red');
        $('#botaoRegistra').attr('disabled','');
    }

});

$(document).on('submit', '.img-ajax-modal', function (event) {
    var form = event.target;
    ajaxUploadFotoModal(form);
    return false;
});

function ajaxUploadFotoModal(form) {
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
            $('#botaoEnviaFoto').on("click", function(){
                $('#botaoEnviaFoto').attr('disabled', '');
            });
        },
        fail: function() {
            alert("Servidor em baixo ou falha de rede");
        }
    });
}


$('#aplicarEviarImg').on('click', function (){
    var img = $('#fotoCroppiedId').attr('src');
    $('#inputDaFotoForm').attr("value", img);
});


$(document).on('submit', '.form-ajax', function (event) {
    var form = event.target;
    ajaxSubmitForm(form);
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
        })
        .always(function () {
            $.unblockUI();
        });
}


    $('#aplicarEviarImg').on('click', function () {
        if($('#inputDaFotoForm').attr("value")){
            $('#botaoModal').removeClass("btn-outline-primary");
            $('#botaoModal').removeClass("btn-danger");
            $('#botaoModal').addClass("btn-success");
        }else{
            $('#botaoModal').removeClass("btn-outline-primary");
            $('#botaoModal').removeClass("btn-success");
            $('#botaoModal').addClass("btn-danger");
        }
    });

function verificaMostraBotao(){
    $('#inputGroupFile').each(function(index){
        if ($('#inputGroupFile').eq(index).val() != ""){
            $('#botaoEnviaFoto').removeAttr('disabled');
        }
    });
}

$('#inputGroupFile').on("change", function(){
    verificaMostraBotao();
});









