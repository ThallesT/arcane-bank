function aplicarMask() {
    $('.rg').mask('00.000.000-A');
    $('.cpf').mask('000.000.000-00');
    $('.celular').mask('(00)00000-0000');
    $('.telefone').mask('(00)0000-0000');
    $('.moeda').mask('000.000.000.000.000,00', {reverse: true});
    $('.date').mask('00/00/0000');
}