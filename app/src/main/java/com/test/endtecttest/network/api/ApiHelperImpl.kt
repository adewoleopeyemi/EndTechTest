package com.test.endtecttest.network.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.endtecttest.network.api.RetrofitBuilder.apiService
import com.test.endtecttest.network.entity.Response
import com.test.endtecttest.domain.model.Item
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApiHelperImpl: ApiHelper {
    private var _itemsList :MutableLiveData<List<Item>> = MutableLiveData()
    private var _allItems: ArrayList<Item> = arrayListOf()
    private var _disposable = CompositeDisposable()

    override fun getAllItems(): LiveData<List<Item>> {
        getFisrtItems()
        return _itemsList
    }

    override fun onClear() {
        _disposable.clear()
    }

    override fun getFisrtItems() {
        apiService
            .getFirstItems()
            .subscribeOn(Schedulers.io())
            .doOnNext {
                getSecondItems()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFirstItemsObserverRx())
    }

    override fun getSecondItems(){
        apiService
            .getSecondItems()
            .subscribeOn(Schedulers.io())
            .doOnNext {
                _itemsList.postValue(_allItems)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSecondItemsObserversRx())
    }

    private fun getFirstItemsObserverRx(): Observer<Response> {
        return object : Observer<Response> {
            override fun onSubscribe(d: Disposable) {
                _disposable.add(d)
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

    private fun getSecondItemsObserversRx(): Observer<Response> {
        return object : Observer<Response> {
            override fun onSubscribe(d: Disposable) {
                _disposable.add(d)
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

}