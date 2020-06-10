package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.features.general.errors.ApiException


/**
 * This object represents a not active error.
 */
object ForbiddenException : ApiException(403, "Forbidden error")