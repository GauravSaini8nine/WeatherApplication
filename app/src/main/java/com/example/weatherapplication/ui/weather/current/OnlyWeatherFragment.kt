package com.example.weatherapplication.ui.weather.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.weatherapplication.Data.Internal.glide.GlideApp
import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
import com.example.weatherapplication.R
import com.example.weatherapplication.ui.base.ScopedFragment
import com.example.weatherapplication.ui.weather.future.list.FutureWeatherItem
import com.resocoder.forecastmvvm.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_future_weather.*
import kotlinx.android.synthetic.main.only_weather_fragment.*
import kotlinx.android.synthetic.main.weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class OnlyWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: OnlyWeatherViewModelFactory by instance()



    private lateinit var viewModel: OnlyWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.only_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =ViewModelProvider(this,viewModelFactory)
            .get(OnlyWeatherViewModel::class.java)


        bindUI()

    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun bindUI()= launch{
        val currentWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()
//        ///////

        val futureWeatherEntries= viewModel.weatherEntries.await()
//        val weatherLocation = viewModel.weatherLocation.await()
//        weatherLocation.observe(this@OnlyWeatherFragment, Observer {location->
//            if (location== null) return@Observer
//            updateLoaction(location.name)

        futureWeatherEntries.observe(this@OnlyWeatherFragment, Observer {weatherEntries->
            if (weatherEntries==null) return@Observer

            initRecyclerView(weatherEntries.toFutureWeatherItems())
////            group_loading.visibility= View.GONE
//            maintain.visibility= View.GONE
////            textView.text= it.toString()
//
////            UpdatedateToday()
//            updateTemperatures(it.avgTemperature, it.feelslike)
//            updateCloud(it.cloud)
//            updateCondition(it.conditionText)
//            updateGust(it.gust)
//            updateHumidity(it.humidity)
//            updatePrecipitation(it.totalPrecipitation)
//            updatePressure(it.pressure)
//            updateUv(it.uv)
//            updateVisibility(it.avgVisibilityDistance)
//            updateWind(it.windDirection, it.maxWindSpeed)
////            updateConditionT(it.conditionText)
//
//
//            GlideApp.with(this@OnlyWeatherFragment)
//                .load("http://${it.conditionIconUrl}")
//                .into(imageView_condition_icon)
//
//
//
//
        })





        /////////

        weatherLocation.observe(this@OnlyWeatherFragment, Observer {location->
            if (location== null) return@Observer
            updateLoaction(location.name)
        })

        currentWeather.observe(this@OnlyWeatherFragment, Observer {
            if (it==null) return@Observer
//            group_loading.visibility= View.GONE
            maintain.visibility= View.GONE
//            textView.text= it.toString()

//            UpdatedateToday()
            updateTemperatures(it.avgTemperature, it.feelslike)
            updateCloud(it.cloud)
            updateCondition(it.conditionText)
            updateGust(it.gust)
            updateHumidity(it.humidity)
            updatePrecipitation(it.totalPrecipitation)
            updatePressure(it.pressure)
            updateUv(it.uv)
            updateVisibility(it.avgVisibilityDistance)
            updateWind(it.windDirection, it.maxWindSpeed)


//            updateConditionT(it.conditionText)


            GlideApp.with(this@OnlyWeatherFragment)
                .load("http://${it.conditionIconUrl}")
                .into(imageView_condition_icon)




        })










    }
    //////////////////////////////////////


//    private fun updateLoaction(location:String){
//        textView.text= location
//    }

//    private fun updateDataToNextWeek(){
//    (activity as? AppComatActivity)?.supportActionBar?.subtitle= "Next Week"}

    private fun List<UnitSpecificSimpleFutureWeatherEntry>.toFutureWeatherItems():List<FutureWeatherItem>{
        return this.map {
            FutureWeatherItem(it)

        }
    }




//    private fun initRecyclerView(items:List<FutureWeatherItem>){
//        val groupAdapter= GroupAdapter<ViewHolder>().apply {
//            addAll(items)
//        }
//        future_recycler_view.apply {
//            = LinearLayoutManager(this@OnlyWeatherFragment.context) }
//
//    }

    private fun initRecyclerView(items: List<FutureWeatherItem>) {
        val groupAdapter = GroupAdapter<com.xwray.groupie.kotlinandroidextensions.ViewHolder>().apply {
            addAll(items)
        }

        future_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@OnlyWeatherFragment.context)

            adapter = groupAdapter
        }
        future_recycler_view.layoutManager = LinearLayoutManager(this.context, HORIZONTAL, false)
        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(this.context, "clicked", Toast.LENGTH_SHORT).show()
//            (item as? FutureWeatherItem)?.let {
//                showWeatherDetail(it.weatherEntry.date, view)
//            }
        }
    }






//////////////////////////////////////////////////
    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial
    }


    private fun updateLoaction(location:String){
        textView.text= location
    }



//    private fun UpdatedateToday(){
//        wind_Speed.text= "today"
//    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C" , "°F")
        avg_Temperature.text = "$temperature$unitAbbreviation"
        feelsLike_temp.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

//    private fun updateConditionT(condition: String) {
//        textView_condition.text = condition
//        if(textView_condition.text== "Mist") {
//        GlideApp.with(this@OnlyWeatherFragment)
//            .load("https://cdn.pixabay.com/photo/2016/11/08/05/18/hot-air-ballon-1807521_640.jpg")
//            .into(back)
//    }
//
//    }



    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        textView_precipitation.text = "$precipitationVolume $unitAbbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph" , "Mph")
        wind_Speed.text = "$windSpeed $unitAbbreviation"
//        wind_Direction.text=  "$windDirection"
    }

    private fun updateVisibility(visibilityDistance: Double) {

        visibility.text = "$visibilityDistance "
    }
    private fun updateGust(gust: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph" , "Mph")
        textView_gust.text= "$gust $unitAbbreviation"
    }
    private fun updatePressure(pressure: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("Mb" , "in")
        textView_pressure.text = "$pressure $unitAbbreviation"
    }
    private fun updateCloud(cloud: Int) {
        clouds.text = "$cloud"
    }
    private fun updateHumidity(humidity: Int) {
        textView_humidity.text = "$humidity"
    }
    private fun updateUv(uv: Double) {
        textView_Uv.text = "$uv"
    }



//    private fun minMaxTemperature(minTemp:Double) {
////        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
////        else "°F"
//        icon_only.text = "$minTemp"
//    }

//    private fun updateVisibility(visibilityDistance: Double) {
//        val unitAbbreviation = if(viewModel.isMetric)"km" else "mi."
//        visibility.text = "Visibility: $visibilityDistance $unitAbbreviation"
//    }











//    private fun bindFuture()=launch (Dispatchers.Main){ }

}