package com.android.presentation.features.loadings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.presentation.R
import com.android.presentation.features.general.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSimpleTitle(getString(R.string.loading_follow_label))

        tvGenerateLoading.setOnClickListener {
            val intent = Intent(requireActivity(), NavigationLoading::class.java)
            startActivity(intent)
        }
    }

}