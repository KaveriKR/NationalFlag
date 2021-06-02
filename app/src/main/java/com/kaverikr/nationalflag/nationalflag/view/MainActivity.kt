package com.kaverikr.nationalflag.nationalflag.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kaverikr.nationalflag.R
import com.kaverikr.nationalflag.nationalflag.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var viewModel: ListViewModel
    private val listadapter: ListAdapter = ListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        swipeRefreshLayout.setOnRefreshListener(this)

        countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listadapter
        }

        startobserving()
    }

    private fun startobserving() {

        viewModel.list.observe(
            this,
            Observer { list ->
                list?.let { countriesList.visibility = View.VISIBLE
                listadapter.updateList(it)}
            }
        )

        viewModel.error.observe(this , Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.INVISIBLE }

        })

        viewModel.loading.observe(this , Observer { loading ->
            loading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.INVISIBLE

                if (it) {
                    list_error.visibility = View.GONE
                    countriesList.visibility = View.GONE
                }
            }
        })
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing  = false

        viewModel.refresh()
    }


}