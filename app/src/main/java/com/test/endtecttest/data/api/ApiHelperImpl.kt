package com.test.endtecttest.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.endtecttest.data.api.RetrofitBuilder.apiService
import com.test.endtecttest.data.entity.Response
import com.test.endtecttest.data.model.Item
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiHelperImpl: ApiHelper {
    private var _itemsList :MutableLiveData<List<Item>> = MutableLiveData()
    private var _allItems: ArrayList<Item> = arrayListOf()

    override fun getAllItems(): LiveData<List<Item>> {
        getFisrtItems()
        return _itemsList
    }

    override fun getFisrtItems() {
        apiService
            .getFirstItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                getSecondItems()
            }
            .subscribe(getFirstItemsObserverRx())
    }

    override fun getSecondItems(){
        apiService
            .getSecondItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSecondItemsObserversRx())
    }

    fun getFirstItemsObserverRx(): Observer<Response> {
        return object : Observer<Response> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Response) {
                _allItems.addAll(t.product)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        }
    }

    fun getSecondItemsObserversRx(): Observer<Response> {
        return object : Observer<Response> {
            override fun onSubscribe(d: Disposable) {
                // loading
            }

            override fun onNext(t: Response) {
                _itemsList.postValue(_allItems.toList())
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
                //complete
            }

        }
    }

}