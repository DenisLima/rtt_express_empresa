package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.feature.general.errors.ApiException


/**
 * This object represents an un-processable entity error.
 */
open class UnProcessableEntityException(
    val errors: List<Throwable> = emptyList()
) : ApiException(CODE, DEFAULT_MSG) {

    companion object {
        const val CODE = 422
        const val DEFAULT_MSG = "Un-processable entity error"
    }
}