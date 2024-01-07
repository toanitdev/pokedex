package com.toandev.pokedex.domain.usecase

//import io.reactivex.rxjava3.core.Observable
//import io.reactivex.rxjava3.observers.DisposableObserver


//abstract class UseCase<in Params, Type> where Type : Any{
//    protected abstract fun createObservable(p: Params? = null) : Observable<Type>
//
//    fun execute(observer: DisposableObserver<Type>, p: Params?){
//        return  asObservable(p).subscribe(observer)
//    }
//
//    fun asObservable(p: Params? = null) : Observable<Type>{
//        return createObservable(p)
//    }
//}