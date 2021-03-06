package subject

import observer.Observer

interface Subject<T> {
    fun registerObserver(observer: Observer<T>)
    fun removeObserver(observer: Observer<T>)
    fun notifyObservers(context: T)
}