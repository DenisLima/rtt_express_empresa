package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.feature.general.errors.ApiException


/**
 * This class represents a unknown error.
 */
object UnknownException : ApiException(-1, "Unknown error")