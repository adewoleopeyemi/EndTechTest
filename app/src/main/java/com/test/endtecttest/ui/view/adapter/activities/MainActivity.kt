package com.test.endtecttest.ui.view.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.endtecttest.data.api.ApiHelperImpl
import com.test.endtecttest.data.model.Advert
import com.test.endtecttest.data.model.Item
import com.test.endtecttest.ui.intent.ItemsIntent
import com.test.endtecttest.ui.view.adapter.AdvertFragment
import com.test.endtecttest.ui.view.adapter.MainAdapter
import com.test.endtecttest.ui.viewmodels.MainViewModel
import com.test.endtecttest.ui.viewmodels.ViewModelFactory
import com.test.endtecttest.ui.viewstate.MainState
import com.test.endtecttest.util.loadImage
import endtecttest.R
import endtecttest.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_product_display_heading.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var adapter1 = MainAdapter(arrayListOf())
    private var adapter2 = MainAdapter(arrayListOf())
    private var adapter3 = MainAdapter(arrayListOf())
    private var adapter4 = MainAdapter(arrayListOf())
    private var adapter5 = MainAdapter(arrayListOf())
    private var adapterCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpUI()
        setupViewModel()
        observeViewModel()
        initViews()
        supportActionBar!!.hide()
    }

    private fun initViews() {
        lifecycleScope.launch {
            mainViewModel.userIntent.send(ItemsIntent.GetItems)
        }
    }

    private fun setUpUI() {
        setUpProductDisplays()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val allAds: ArrayList<Advert> = arrayListOf()
        allAds.add(Advert(R.drawable.ads1, "MAISON"))
        allAds.add(Advert(R.drawable.ads2, "VISIM"))
        allAds.add(Advert(R.drawable.ads3, "ARIES"))
        val adapter = AdvertAdapter(supportFragmentManager, allAds)
        binding.trendingVp.adapter = adapter
        binding.indicator.setViewPager(binding.trendingVp)
    }

    private fun setUpProductDisplays() {
        binding.products.productDisplay1.tvLeft.text = getString(R.string.latest_products)
        binding.products.productDisplay1.tvRight.text = getString(R.string.view_all)
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

        binding.products.productDisplay5.tvLeft.text = getString(R.string.latest_products)
        binding.products.productDisplay5.tvRight.text = getString(R.string.view_all)
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

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                this@MainActivity,
                ApiHelperImpl()
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel(){
        lifecycleScope.launchWhenStarted {
            mainViewModel.state.collect {
                when (it){
                    is MainState.Idle ->{

                    }
                    is MainState.Loading ->{
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        renderList(it.items)
                    }

                    is MainState.Error ->{
                    }
                }
            }
        }
    }

    private fun renderList(items: List<Item>){
        Observable.fromArray(adapter5, adapter4, adapter3, adapter2, adapter1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe ({ string: MainAdapter? ->
                popuplateRv(
                    string, items
                )
            })
        setUpProductImages(items.sortedByDescending { it.id }.subList(0, 10))
    }

    private fun popuplateRv(adapter: MainAdapter?, items: List<Item>) {
        try{
            var products = items.slice(adapterCount..(adapterCount*20))
            products.let { listOfUsers -> listOfUsers.let { adapter!!.addData(products.sortedByDescending { it.id }) } }
            adapter!!.notifyDataSetChanged()
        }

        catch (e: Exception){
            var products = items.slice(adapterCount until items.size-1)
            products.let { listOfUsers -> listOfUsers.let { adapter!!.addData(products.sortedByDescending { it.id }) } }
            adapter!!.notifyDataSetChanged()
        }
        adapterCount +=1
    }

    inner class AdvertAdapter(fm: FragmentManager, private var ads: ArrayList<Advert>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return AdvertFragment(ads.get(position).image)
        }

        override fun getCount(): Int = ads.size
    }


}