package com.beepnbuy.seller.util

/**
 * Created by Mayur Solanki on 29/06/21, 3:03 pm.
 */
open class ObserverEvent <out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}