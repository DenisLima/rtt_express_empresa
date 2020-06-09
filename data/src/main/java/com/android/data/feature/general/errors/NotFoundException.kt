package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.feature.general.errors.ApiException


/**
 * This object represents a not found error.
 */
object NotFoundException : ApiException(404, "Not found error")