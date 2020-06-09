package air.br.com.alelo.mobile.android.domain.features.general.errors

import com.android.data.feature.general.errors.ApiException


/**
 * This class represents a unknown error.
 *  @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/429">https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/429</a>
 */
object TooManyRequestsException : ApiException(429, "Too Many Requests")