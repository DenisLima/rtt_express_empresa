package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.features.general.errors.ApiException


/**
 * This object represents an unexpected error.
 */
object InternalServerException : ApiException(500, "Unexpected Error")