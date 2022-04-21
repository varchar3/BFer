package com.lzc.bfer.ext

/**
 * getOrNull() 可能会出现问题，会出现 Success(Failure(Throwable)), 被再套了一层
 */
inline fun <T> Result<T>.getOrNullOnSafe(): T? =
    when {
        isFailure -> null
        else -> {
            val value = getOrNull()
            if (value is Result<*>) {
                value.getOrNull() as T?
            } else {
                value
            }
        }
    }

inline fun <T> Result<T>.isFailure(): Boolean =
    isFailure || when {
        getOrNull() is Result<*> -> (getOrNull() as Result<*>).isFailure
        else -> false
    }

inline fun <T> Result<T>.isSuccess() = !isFailure()