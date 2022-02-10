package com.test.endtecttest.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.endtecttest.domain.model.Advert
import com.test.endtecttest.domain.model.Item
import com.test.endtecttest.presentation.intent.ItemsIntent
import com.test.endtecttest.presentation.view.adapter.MainAdapter
import com.test.endtecttest.presentation.viewmodels.MainViewModel
import com.test.endtecttest.presentation.viewstate.MainViewState
import com.test.endtecttest.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import endtecttest.R
import endtecttest.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_product_display_heading.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var adapter1 = MainAdapter(arrayListOf())
    private var adapter2 = MainAdapter(arrayListOf())
    private var adapter3 = MainAdapter(arrayListOf())
    private var adapter4 = MainAdapter(arrayListOf())
    private var adapter5 = MainAdapter(arrayListOf())
    private var adapterCount = 0
    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpUI()
        observeViewModel()
        initViews()
        supportActionBar!!.hide()
    }

    private fun initViews() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.userIntent.send(ItemsIntent.GetItems)
        }
    }

    private fun setUpUI() {
        setUpProductDisplays()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val allAds: ArrayList<Advert> = arrayListOf()
        allAds.add(Advert(R.drawable.ads1, "VETEMENTS", "Discover the Latest Arrivals."))
        allAds.add(Advert(R.drawable.ads2, "MAISON MARIGIELA", "Fresh Styles"))
        allAds.add(Advert(R.drawable.ads3, "VISVIM", "Now In."))
        val adapter = AdvertAdapter(supportFragmentManager, allAds)
        binding.promotedAd.adapter = adapter
        binding.indicator.setViewPager(binding.promotedAd)
    }

    private fun setUpProductDisplays() {
        binding.products.productDisplay1.tvLeft.text = getString(R.string.view_all)
        binding.products.productDisplay1.tvRight.text = getString(R.string.latest_products)
        binding.products.productDisplay1.rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.products.productDisplay1.rv.adapter = adapter1

        binding.products.productDisplay2.tvRight.text = getString(R.string.latest_products)
        binding.products.productDisplay2.tvLeft.text = getString(R.string.view_all)
        binding.products.productDisplay2.rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.products.productDisplay2.rv.adapter = adapter2

        binding.products.productDisplay3.tvRight.text = getString(R.string.latest_products)
        binding.products.productDisplay3.tvLeft.text = getString(R.string.view_all)
        binding.products.productDisplay3.rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.products.productDisplay3.rv.adapter = adapter3

        binding.products.productDisplay4.tvRight.text = getString(R.string.latest_products)
        binding.products.productDisplay4.tvLeft.text = getString(R.string.view_all)
        binding.products.productDisplay4.rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.products.productDisplay4.rv.adapter = adapter4

        binding.products.productDisplay5.tvLeft.text = getString(R.string.view_all)
        binding.products.productDisplay5.tvRight.text = getString(R.string.latest_products)
        binding.products.productDisplay5.rv.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        binding.products.productDisplay5.rv.adapter = adapter5
    }

    private fun setUpProductImages(urls: List<Item>) {
        binding.products.firstImageProduct.image.loadImage(urls[0].image, R.color.colorgrey)
        binding.products.firstImageProduct.textHeader.text = getString(R.string.stone_island)
        binding.products.firstImageProduct.textSubheader.text = getString(R.string.shop_now)

        binding.products.secondImageProduct.image.loadImage(urls[1].image, R.color.colorgrey)
        binding.products.secondImageProduct.textHeader.text = getString(R.string.stone_island)
        binding.products.secondImageProduct.textSubheader.text = getString(R.string.shop_now)

        binding.products.thirdImageProduct.image.loadImage(urls[2].image, R.color.colorgrey)
        binding.products.thirdImageProduct.textHeader.text = getString(R.string.john_elliot)
        binding.products.thirdImageProduct.textSubheader.text = getString(R.string.online_now)

        binding.products.fourthImageProduct.image.loadImage(urls[3].image, R.color.colorgrey)
        binding.products.fourthImageProduct.textHeader.text = getString(R.string.casablanca)
        binding.products.fourthImageProduct.textSubheader.text = getString(R.string.online_now)

        binding.products.fifthImageProduct.image.loadImage(urls[4].image, R.color.colorgrey)
        binding.products.fifthImageProduct.textHeader.text = getString(R.string.maharishi)
        binding.products.fifthImageProduct.textSubheader.text = getString(R.string.shop_now)

        binding.products.sixthImageProduct.image.loadImage(urls[5].image, R.color.colorgrey)
        binding.products.sixthImageProduct.textHeader.text = getString(R.string.focus)
        binding.products.sixthImageProduct.textSubheader.text = getString(R.string.shop_now)

        binding.products.sevethImageProduct.image.loadImage(urls[6].image, R.color.colorgrey)
        binding.products.sevethImageProduct.textHeader.text = getString(R.string.dries_van_noten)
        binding.products.sevethImageProduct.textSubheader.text = getString(R.string.online_now)

        binding.products.eightImageProduct.image.loadImage(urls[7].image, R.color.colorgrey)
        binding.products.eightImageProduct.textHeader.text = getString(R.string.jw_anderson)
        binding.products.eightImageProduct.textSubheader.text = getString(R.string.online_now)

        binding.products.ninthImageProduct.image.loadImage(urls[8].image, R.color.colorgrey)
        binding.products.ninthImageProduct.textHeader.text = getString(R.string.neighborhood)
        binding.products.ninthImageProduct.textSubheader.text = getString(R.string.shop_now)

        binding.products.tenthImageProduct.image.loadImage(urls[9].image, R.color.colorgrey)
        binding.products.tenthImageProduct.textHeader.text = getString(R.string.bottega_veneta)
        binding.products.tenthImageProduct.textSubheader.text = getString(R.string.online_now)
    }

    private fun observeViewModel(){
        lifecycleScope.launchWhenStarted {
            mainViewModel.viewState.collect {
                when (it){
                    is MainViewState.Idle ->{

                    }
                    is MainViewState.Loading ->{
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainViewState.Items -> {
                        progressBar.visibility = View.GONE
                        renderList(it.items)
                    }

                    is MainViewState.Error ->{
                    }
                }
            }
        }
    }

    private fun renderList(items: List<Item>){
        val adapters = arrayListOf<MainAdapter>()
        adapters.add(adapter1)
        adapters.add(adapter2)
        adapters.add(adapter3)
        adapters.add(adapter4)
        adapters.add(adapter5)
        Observable.fromIterable(adapters)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object: Observer<MainAdapter>{
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onNext(t: MainAdapter) {
                    populateRv(t, items)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            })
        setUpProductImages(items.sortedByDescending { it.id }.subList(0, 10))
    }

    private fun populateRv(adapter: MainAdapter?, items: List<Item>) {
        try{
            val products = items.slice((adapterCount*10) until ((adapterCount+1)*10))
            products.let { listOfUsers -> listOfUsers.let { adapter!!.addData(products.sortedByDescending { it.id }) } }
            adapter!!.updateData()
        }

        catch (e: Exception){
            val products = items.slice((adapterCount*10) until items.size-1)
            products.let { listOfUsers -> listOfUsers.let { adapter!!.addData(products.sortedByDescending { it.id }) } }
            adapter!!.updateData()
        }
        adapterCount +=1
    }

    inner class AdvertAdapter(fm: FragmentManager, private var ads: ArrayList<Advert>) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putSerializable("ad", ads[position])
            val fragment = AdvertFragment()
            fragment.arguments = bundle
            return fragment
        }

        override fun getCount(): Int = ads.size
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}