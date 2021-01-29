package id.higo.countryapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import id.higo.core.adapter.CountryAdapter
import id.higo.core.data.Resource
import id.higo.core.domain.model.Country
import id.higo.countryapp.databinding.FragmentSearchBinding
import id.higo.countryapp.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), AdapterView.OnItemClickListener {

    val countryList = ArrayList<Country?>()
    val adapterFlag = CountryAdapter()
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            adapterFlag.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA,selectedData)
                startActivity(intent)
            }

            with(binding.rvSearch){
                layoutManager = GridLayoutManager(context,3)
                setHasFixedSize(true)
                adapter = adapterFlag
            }

            binding.edCountry.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let {
                        searchViewModel.search(s.toString()).observe(viewLifecycleOwner, { result ->
                            val countryName = ArrayList<String?>()
                            when (result) {
                                is Resource.Success -> {
                                    countryList.clear()
                                    result.data?.map {
                                        countryName.add(it.countryName)
                                        countryList.add(it)
                                    }

                                    val adapter = context?.let { it1 -> ArrayAdapter(it1,android.R.layout.select_dialog_item,countryName) }
                                    adapter?.notifyDataSetChanged()
                                    binding.edCountry.setAdapter(adapter)

                                }
                            }

                        })
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

            binding.edCountry.onItemClickListener = this
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val result = ArrayList<Country>()
        val country = countryList.get(position)
        if (country != null) {
            result.add(country)
        }
        adapterFlag.setData(result)
    }
}