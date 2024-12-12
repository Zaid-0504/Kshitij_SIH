package com.example.kshitijsih

data class BeachDataResponse(
	val hours: List<HoursItem?>? = null,
	val meta: Meta? = null
)

data class AirTemperature(
	val dwd: Any? = null,
	val noaa: Any? = null,
	val sg: Any? = null,
	val smhi: Any? = null
)

data class Meta(
	val requestCount: Int? = null,
	val cost: Int? = null,
	val lng: Any? = null,
	val start: String? = null,
	val end: String? = null,
	val params: List<String?>? = null,
	val dailyQuota: Int? = null,
	val lat: Any? = null
)

data class HoursItem(
	val airTemperature: AirTemperature? = null,
	val time: String? = null
)

