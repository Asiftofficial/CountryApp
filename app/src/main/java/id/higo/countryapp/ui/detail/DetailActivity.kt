package id.higo.countryapp.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import id.higo.core.domain.model.Country
import id.higo.countryapp.R
import id.higo.countryapp.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Country>(EXTRA_DATA)
        showDetailCountry(data)
    }

    private fun showDetailCountry(country: Country?) {

        country?.let {

            val uri = Uri.parse(country.flag)
            GlideToVectorYou.justLoadImage(this, uri, binding.imgDetail)

            binding.tvNameDetail.text = getString(R.string.name,country.countryName)
            binding.tvCapitalDetail.text = getString(R.string.capital,country.capital)
            binding.tvRegionDetail.text = getString(R.string.region,country.region)
            binding.tvSubregionDetail.text = getString(R.string.subregion,country.subRegion)
            binding.tvCallCodeDetail.text = getString(R.string.callingcode,country.callingCode)
            binding.tvLanguageDetail.text = getString(R.string.language,country.language)
            binding.tvDomainDetail.text = getString(R.string.topLevelDomain,country.topLevelDomain)
            binding.tvCurCodeDetail.text = getString(R.string.curCode,country.currenciesCode)
            binding.tvCurNameDetail.text = getString(R.string.curName,country.currenciesName)
            binding.tvCurSymbolDetail.text = getString(R.string.curSymbol,country.currenciesSymbol)
            binding.tvTimeZoneDetail.text = getString(R.string.timeZone,country.timeZone)
            binding.tvLatitudeDetail.text = getString(R.string.latitude, country.latitude.toString())
            binding.tvLongitudeDetail.text = getString(R.string.longitude,country.longitude.toString())
            binding.tvPopulationDetail.text = getString(R.string.population,country.population.toString())
            binding.tvAreaDetail.text = getString(R.string.area,country.area.toString())

            var favoriteState = country.isFavorite
            setFavoriteState(favoriteState)
            binding.fab.setOnClickListener {
                favoriteState = !favoriteState
                detailViewModel.setFavorite(country,favoriteState)
                setFavoriteState(favoriteState)
            }


        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite))
        }else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_favorite_no))
        }
    }
}