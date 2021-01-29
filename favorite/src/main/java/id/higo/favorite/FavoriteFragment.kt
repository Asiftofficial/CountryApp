package id.higo.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.higo.core.adapter.CountryAdapter
import id.higo.countryapp.R
import id.higo.countryapp.ui.detail.DetailActivity
import id.higo.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity != null){

            val countryAdapter = CountryAdapter()

            countryAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, Class.forName("id.higo.countryapp.ui.detail.DetailActivity"))
                intent.putExtra(DetailActivity.EXTRA_DATA,selectedData)
                startActivity(intent)
            }
            binding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.favorite.observe(viewLifecycleOwner, { favoriteList ->
                if (favoriteList != null) {
                    binding.progressBar.visibility = View.GONE
                    countryAdapter.setData(favoriteList)
                    if (favoriteList.isEmpty()) binding.viewNoData.root.visibility = View.VISIBLE
                }else {
                    binding.progressBar.visibility = View.GONE
                    binding.viewNoData.root.visibility = View.VISIBLE
                }
            })

            with(binding.rvFavorite) {
                layoutManager = GridLayoutManager(context,3)
                setHasFixedSize(true)
                adapter = countryAdapter
            }
        }

    }
}