package com.android.domain.features.generalregister.ro

data class GeneralRegisterResultObject (
    var razaoSocial: String,
    var cnpj: String,
    var endereco: String,
    var numero: String,
    var complemento: String,
    var telefone1: String,
    var telefone2: String,
    var bairro: String,
    var cidade: String,
    var uf: String,
    var cep: String,
    var responsavelLegal: String,
    var site: String
)