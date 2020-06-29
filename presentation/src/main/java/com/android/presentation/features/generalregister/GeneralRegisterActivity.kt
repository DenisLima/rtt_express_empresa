package com.android.presentation.features.generalregister

import android.os.Bundle
import android.widget.Toast
import com.android.presentation.R
import com.android.presentation.extensions.observeOn
import com.android.presentation.features.general.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_general_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class GeneralRegisterActivity : BaseActivity() {

    private val viewModel: GeneralRegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_layout)

        prepareObservers()
        initComponents()

    }

    private fun prepareObservers() {
        viewModel.isLoading()
            .observeOn(this) { isLoading ->
                when {
                    isLoading -> showLoading()
                    else -> hideLoading()
                }
            }.removeObserversOnDestroy()

        viewModel.getErrorMessage()
            .observeOn(this){ message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }.removeObserversOnDestroy()
    }

    private fun initComponents() {
        btnRegister.setOnClickListener {
            viewModel.putTokenManual(
                razaoSocial = etRazaoSocial.text.toString(),
                cnpj = edCnpj.text.toString(),
                endereco = edEndereco.text.toString(),
                numero = etNumero.text.toString(),
                complemento = etComplemento.text.toString(),
                telefone1 = etTelefoneUm.text.toString(),
                telefone2 = etTelefoneDois.text.toString(),
                bairro = etBairro.text.toString(),
                cidade = etCidade.text.toString(),
                uf = etUf.text.toString(),
                cep = etCep.text.toString(),
                responsavelLegal = etRespLegal.text.toString(),
                site = etSite.text.toString(),
                token = "Bearer 909090"
            )
        }
    }

}