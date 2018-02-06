package com.trust.tnighttalk.tool.kttest

import android.content.Context
import com.trust.tnighttalk.tool.TrustLogTool
import com.trust.tnighttalk.tool.retrofit.RetrofitTest
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit
import java.util.function.Function


/**
 * Created by Trust on 2018/2/5.
 */
class RxKotlin {
    init{
        rxFlowable()
//        rxZip()
        /* 接收没有顺序
        Observable.create(ObservableOnSubscribe<Int>{
            e ->
            e.onNext(1)
            e.onNext(2)
            e.onNext(3)
        }).flatMap { t ->
            val ml = ArrayList<String>()
            for (x in 1..3) {
                ml.add("this is flatMap:"+t)
            }
            Observable.fromIterable(ml).delay(10,TimeUnit.MILLISECONDS)
        }.subscribe(Consumer { s->
            TrustLogTool.d(s)
        })*/


     /* 接收按照顺序
     Observable.create(ObservableOnSubscribe<Int> { e->
            e.onNext(1)
            e.onNext(2)
            e.onNext(3)
        }).concatMap { t ->
            val  ml = ArrayList<String>()
            for (x in 1..3){
                ml.add("this is concatMap"+t)
            }

            Observable.fromIterable(ml).delay(10,TimeUnit.MILLISECONDS)
        }.subscribe ( Consumer<String> {s->
            TrustLogTool.d(s)
        } )*/
    }

    /*
    map使用
    init{
         Observable.create(ObservableOnSubscribe<String> { e ->
             e.onNext("1111111")
             e.onNext("222222")
             TrustLogTool.d( "Observable thread is : " + Thread.currentThread().name)
         })
                 .map { t ->
                     var a :Int = t.toInt()
                     a;
                 }
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(object :Observer<Int>{
             override fun onComplete() {

             }

             override fun onSubscribe(d: Disposable) {

             }

             override fun onNext(t: Int) {
                 TrustLogTool.d("this is map:"+t)
                 TrustLogTool.d( "onNext thread is : " + Thread.currentThread().name)
             }

             override fun onError(e: Throwable) {
             }
         })
    }*/


    /**
     * rx zip  使两个不同类型 合并成一个指定类型  （有坑，当a中无限发送数据  b随便发一点数据  会造成背压现象 导致内存爆掉）
     */
    fun rxZip(){
        var a = Observable.create(ObservableOnSubscribe<Int> {e->
            e.onNext(1)
            e.onNext(2)
            e.onNext(3)
            e.onNext(4)
            e.onNext(5)
            e.onComplete()
        })

        var b = Observable.create(ObservableOnSubscribe<String> {e->
            e.onNext("A")
            e.onNext("B")
            e.onNext("C")
            e.onComplete()
        })

        Observable.zip(a,b, BiFunction<Int, String, String> { t1, t2 -> t2+t1 })
                .subscribe(object :Observer<String>{
                    override fun onError(e: Throwable) {
                        TrustLogTool.d("onError:")
                    }

                    override fun onComplete() {
                        TrustLogTool.d("onComplete:")
                    }

                    override fun onNext(t: String) {
                        TrustLogTool.d("onNext:"+t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        TrustLogTool.d("onSubscribe:")
                    }

                })
    }



    fun rxFlowable(){
        var flowable = Flowable.create(FlowableOnSubscribe<Int> {e->
            TrustLogTool.d("Flowable:"+Thread.currentThread().name)
            e.onNext(1)
            e.onNext(2)
            e.onNext(3)
            e.onComplete()
        },BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        var subscriber = object :Subscriber<Int>{
            override fun onComplete() {
                TrustLogTool.d("onComplete:")
            }

            override fun onSubscribe(s: Subscription?) {
                TrustLogTool.d("onSubscribe:")
                s!!.request(Long.MAX_VALUE)
            }

            override fun onNext(t: Int?) {
                TrustLogTool.d("onNext:"+t)
                TrustLogTool.d("onNext:"+Thread.currentThread().name)
            }

            override fun onError(t: Throwable?) {
                TrustLogTool.d("onError:"+t)
            }

        }

        flowable.subscribe(subscriber)

    }



    fun TestRetrofit (context:Context){
        var rest =  RetrofitTest(context)
        rest.sendLogin("https://www.jianshu.com/")
    }
}


