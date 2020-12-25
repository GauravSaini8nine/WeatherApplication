package com.example.weatherapplication.ui.weather.future.list

import com.example.weatherapplication.Data.Internal.glide.GlideApp
import com.example.weatherapplication.Data.db.unitLocalized.Future.UnitSpecificSimpleFutureWeatherEntry
import com.example.weatherapplication.R
import com.resocoder.forecastmvvm.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_con.text = weatherEntry.conditionText
            updateDate()
            updateTemperature()
            updateConditionImage()
            minMaxTemperature()
            UV()
            humidity()
            chanceOfShow()
            chanceOfRain()
            Wind()
        }
    }

    override fun getLayout() = R.layout.item_future_weather

    private fun ViewHolder.updateDate() {
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }

    private fun ViewHolder.updateTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        textView_temp.text = "${weatherEntry.avgTemperature}$unitAbbreviation"
    }

    private fun ViewHolder.updateConditionImage() {
        GlideApp.with(this.containerView)
            .load("http:" + weatherEntry.conditionIconUrl)
            .into(imageView_condition_i)
    }

    private fun ViewHolder.UV() {

        uvF.text = "${weatherEntry.uv}"
    }
    private fun ViewHolder.humidity() {

        humidityF.text =
            "${weatherEntry.avghumidity}"
    }
    private fun ViewHolder.chanceOfShow() {

        snow.text =
            "${weatherEntry.dailyChanceOfSnow}"
    }
    private fun ViewHolder.chanceOfRain() {

        rain.text =
            "${weatherEntry.dailyChanceOfRain}"
    }
    private fun ViewHolder.Wind() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "Kph"
        else "Mph"
        speedWF.text =
            "${weatherEntry.maxwind}$unitAbbreviation"
    }
    private fun ViewHolder.minMaxTemperature() {
        val unitAbbreviation = if (weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        maxTemp.text ="${weatherEntry.maxtemp}$unitAbbreviation | ${weatherEntry.mintemp} $unitAbbreviation"
    }


}