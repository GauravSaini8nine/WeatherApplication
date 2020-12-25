package com.example.weather.ui.weather.future.list
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.LinearLayoutManager
//
//import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
//import com.example.weatherapplication.R
//import com.example.weatherapplication.ui.base.ScopedFragment
//import com.example.weatherapplication.ui.weather.future.list.FutureWeatherItem
//import com.xwray.groupie.GroupAdapter
//import com.xwray.groupie.ViewHolder
//import kotlinx.android.synthetic.main.future_list_weather_fragment.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//
//import org.kodein.di.KodeinAware
//import org.kodein.di.android.x.closestKodein
//import org.kodein.di.generic.instance
//import org.threeten.bp.LocalDate
//
//class FutureListWeatherFragment : ScopedFragment(), KodeinAware {
//    override val kodein by closestKodein()
//    private val viewModelFactory: FutureListWeatherViewModelFactory by instance()
//
//
//    private lateinit var viewModel: FutureListWeatherViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        recyclerView= rootView.findViewById(R.id.recyclerView)
////        val rootView= inflater.inflate(R.layout.future_list_weather_fragment,container,false)
//        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        viewModel = ViewModelProvider(this, viewModelFactory)
//            .get(FutureListWeatherViewModel::class.java)
//        bindUI()
//    }
//
//
//    @SuppressLint("FragmentLiveDataObserve")
//    private fun bindUI() = launch(Dispatchers.Main) {
//        val futureWeatherEntries = viewModel.weatherEntries.await()
//        val weatherLocation = viewModel.weatherLocation.await()
//
//        weatherLocation.observe(this@FutureListWeatherFragment, Observer { location ->
//            if (location == null) return@Observer
//            updateLocation(location.name)
//        })
//
//
//        futureWeatherEntries.observe(this@FutureListWeatherFragment, Observer { weatherEntries ->
//            if (weatherEntries == null) return@Observer
//
////            group_loading.visibility = View.GONE
//
//            updateDateToNextWeek()
//            initRecyclerView(weatherEntries.toFutureWeatherItem())
//        })
//    }
//
//    private fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItem(): List<FutureWeatherItem> {
//        return this.map {
//            FutureWeatherItem(it)
//        }
//    }
//
//
//    private fun updateLocation(location: String) {
//
//        (activity as AppCompatActivity).supportActionBar?.title = location
//    }
//
//    private fun updateDateToNextWeek() {
//        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Next Week"
//    }
//
//
//    private fun initRecyclerView(items: List<FutureWeatherItem>) {
//        val groupAdapter = GroupAdapter<ViewHolder>().apply {
//            addAll(items)
//        }
//
////        recyclerView.setHasFixedSize(true)
////        recyclerView?.layoutManager= LinearLayoutManager(this.context)
////        recyclerView?.adapter=groupAdapter
////        recyclerView.apply {
//            recyclerView.layoutManager= LinearLayoutManager(this.context)
//            recyclerView.adapter=groupAdapter
////        }
//
//        groupAdapter.setOnItemClickListener { item, view ->
//            Toast.makeText(this.context, "clicked", Toast.LENGTH_SHORT).show()
////            (item as? FutureWeatherItem)?.let {
////                showWeatherDetail(it.weatherEntry.date, view)
////            }
//        }
//
//    }
////    private fun showWeatherDetail(date: LocalDate, view: View) {
////        val dateString = LocalDateConverter.dateToString(date)!!
////        val actionDetail = FutureListWeatherFragment.actionDetail(dateString)
////        Navigation.findNavController(view).navigate(actionDetail)
////    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
