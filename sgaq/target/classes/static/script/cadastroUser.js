$(document).ready(function () {
    $('#tipo-cadastro').on('change', showHideUserFields);
    showHideUserFields();
});

function showHideUserFields() {
    var tipoCadastro = $('#tipo-cadastro').val();
    var departamentoField = $('#departamento-field');
    var coordenacaoField = $('#coordenacao-field');
    var matriculaField = $('#matricula-field');
    var cpfField = $('#cpf-field');
    var dateField = $('#date-field');

    matriculaField.hide();
    cpfField.hide();
    departamentoField.hide();
    coordenacaoField.hide();
    dateField.hide();

    if (tipoCadastro === '1') {
        matriculaField.show();
        cpfField.show();
        dateField.show();
        departamentoField.show();
        coordenacaoField.hide();
    } else if (tipoCadastro === '2'){
        matriculaField.hide();
        cpfField.hide();
        dateField.hide();
        departamentoField.show();
        coordenacaoField.hide();
    } else if (tipoCadastro === '3'){
        matriculaField.show();
        cpfField.show();
        dateField.show();
        departamentoField.hide();
        coordenacaoField.show();
    } else if (tipoCadastro === '4'){
        matriculaField.hide();
        cpfField.show();
        dateField.show();
        departamentoField.hide();
        coordenacaoField.hide();
    } else if (tipoCadastro === '5'){
        matriculaField.hide();
        cpfField.show();
        dateField.show();
        departamentoField.hide();
        coordenacaoField.hide();
    }
}

var teclasEspeciais = [8, 9, 27, 13, 35, 36, 37, 38, 39, 40, 46];

function formataCPF(campo, event, proximoCampoId) {
    var tecla = event.keyCode ? event.keyCode : event.charCode;

    if (teclasEspeciais.indexOf(tecla) !== -1) {
        return true;
    }

    if ('0123456789'.indexOf(String.fromCharCode(tecla)) === -1) {
        return false;
    }

    var rExp = /[^\0-\9]|\-|\.|\//g;
    var vr = campo.value.replace(rExp, "");
    vr = vr.replace("/", "");
    vr = vr.replace(".", "");
    vr = vr.replace("-", "");

    var saida = vr.substring(0, 3);
    var eventoApagar = (tecla === 8 || tecla === 46);
    var length = vr.length;

    if (eventoApagar) {
        if (campo.value.length >= 3 && length > 3) saida += "." + vr.substring(3, 6);
        if (campo.value.length >= 5 && length > 6) saida += "." + vr.substring(6, 9);
        if (campo.value.length >= 9 && length > 9) saida += "-" + vr.substring(9, 11);
    } else {
        if (campo.value.length >= 3) saida += "." + vr.substring(3, 6);
        if (campo.value.length >= 5 && length >= 6) saida += "." + vr.substring(6, 9);
        if (campo.value.length >= 9 && length >= 9) saida += "-" + vr.substring(9, 11);
    }

    campo.value = saida;

    if (proximoCampoId !== null && campo.value.length === 14) {
        document.getElementById(proximoCampoId).focus();
    }
}

function formatarTelefone(input) {
    // Remove todos os caracteres não numéricos do número
    var numeroLimpo = input.value.replace(/\D/g, '');

    // Aplica a formatação "(xx) xxxxx-xxxx" se o número for válido
    var regex = /^(\d{2})(\d{5})(\d{4})$/;
    if (regex.test(numeroLimpo)) {
        input.value = numeroLimpo.replace(regex, '($1) $2-$3');
    } else {
        input.value = numeroLimpo.substring(0, 14); // Limita o tamanho do número
    }
}

function validarSenha() {
    var senha = document.getElementById("senha").value;
    var confirmarSenha = document.getElementById("senha_conf").value;

    if (senha === "" || confirmarSenha === "") {
        document.getElementById("erro-senha").textContent = "Por favor, preencha ambos os campos.";
        return false;
    }

    if (senha !== confirmarSenha) {
        document.getElementById("erro-senha").textContent = "As senhas não correspondem.";
        return false;
    }

    return true; // Permite o envio do formulário se a validação passar
}