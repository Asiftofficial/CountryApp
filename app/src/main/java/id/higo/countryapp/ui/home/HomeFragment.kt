package id.higo.countryapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.higo.core.adapter.CountryAdapter
import id.higo.core.data.Resource
import id.higo.countryapp.R
import id.higo.countryapp.databinding.FragmentHomeBinding
import id.higo.countryapp.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val countryAdapter = CountryAdapter()

            countryAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity,DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA,selectedData)
                startActivity(intent)
            }

            homeViewModel.country.observe(viewLifecycleOwner, { countryList ->
                if (countryList != null) {
                    when(countryList) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.rvHome.visibility = View.VISIBLE
                            countryAdapter.setData(countryList.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.tvError.text = countryList.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvHome) {
                layoutManager = GridLayoutManager(context,3)
                setHasFixedSize(true)
                adapter = countryAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}