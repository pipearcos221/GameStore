package unicauca.movil.gamestore.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import unicauca.movil.gamestore.R
import java.net.SocketTimeoutException

/**
 * Created by Asus on 19/01/2018.
 */
class LifeDisposable(owner: LifecycleOwner): LifecycleObserver {

    init {
        owner.lifecycle.addObserver(this)
    }

    private val dis : CompositeDisposable = CompositeDisposable()

    infix fun add(disposable: Disposable){
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearDisposable(){
        dis.clear()
    }
}

fun <T> Flowable<T>.applySchedulers(): Flowable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

//Extension para usar unicamente en subjects y como respuesta a acciones del usuario

fun <T> Observable<T>.subscribeByAction(onNext: (T) -> Unit, onHttpError: (resString: Int) -> Unit,
                                        onError: ((error: Throwable) -> Unit)? = null): Disposable =

        doOnError {
            when(it){
                is SocketTimeoutException -> onHttpError(R.string.socket)
                is HttpException -> {
                    when(it.code()) {
                        404 -> onHttpError(R.string.http_404)
                        401 -> onHttpError(R.string.http_401)
                        else -> onHttpError(R.string.http_500)
                    }
                }
                else -> onError?.invoke(it)
            }
        }
                .retry()
                .subscribe(onNext, {})

//Extension para validar errores con observables

fun <T> Observable<T>.subscribeByShot(onNext: (T) -> Unit, onHttpError: (resString: Int) -> Unit,
                                      onError: ((error: Throwable) -> Unit)? = null): Disposable =

        doOnError {
            when(it){
                is SocketTimeoutException -> onHttpError(R.string.socket)
                is HttpException -> {
                    when(it.code()) {
                        404 -> onHttpError(R.string.http_404)
                        401 -> onHttpError(R.string.http_401)
                        else -> onHttpError(R.string.http_500)
                    }
                }
                else -> onError?.invoke(it)
            }
        }
                .subscribe(onNext, {})