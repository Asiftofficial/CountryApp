package id.higo.countryapp.ui.maps

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import id.higo.core.domain.model.Country
import id.higo.countryapp.R
import id.higo.countryapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {

    companion object{
        private const val ICON_ID = "ICON_ID"
        const val EXTRA_DATA = "extra_data"
    }


    private lateinit var mapbox: Mapbox
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapbox = Mapbox.getInstance(applicationContext,getString(R.string.mapbox_access_token))
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val data = intent.getParcelableExtra<Country>(EXTRA_DATA)

        if (data?.latitude?.toInt() != 0 && data?.longitude?.toInt() != 0){
            binding.mapView.onCreate(savedInstanceState)
            binding.mapView.getMapAsync { mapboxMap ->
                this.mapboxMap = mapboxMap
                mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                    symbolManager = SymbolManager(binding.mapView,mapboxMap,style)
                    symbolManager.iconAllowOverlap = true

                    style.addImage(ICON_ID,BitmapFactory.decodeResource(resources,R.drawable.mapbox_marker_icon_default))

                    val country = LatLng(data!!.latitude, data.longitude)
                    symbolManager.create(
                        SymbolOptions()
                            .withLatLng(LatLng(country.latitude,country.longitude))
                            .withIconImage(ICON_ID)
                            .withIconSize(1.5f)
                    )

                    mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(country,8.0))
                }
            }
        }else{
            binding.viewNoDataMaps.root.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}