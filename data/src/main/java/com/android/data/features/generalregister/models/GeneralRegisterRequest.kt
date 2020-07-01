package com.android.data.features.generalregister.models

data class GeneralRegisterRequest (
    val razaoSocial: String,
    val cnpj: String,
    val endereco: String,
    val numero: String,
    val complemento: String,
    val telefone1: String,
    val telefone2: String,
    val bairro: String,
    val cidade: String,
    val uf: String,
    val cep: String,
    val responsavelLegal: String,
    val site: String
)