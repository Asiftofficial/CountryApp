package id.higo.core.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import id.higo.core.databinding.ItemListCountryBinding
import id.higo.core.domain.model.Country
import okhttp3.OkHttpClient


class CountryAdapter: RecyclerView.Adapter<CountryAdapter.ListViewHolder>() {

    private var dataList = ArrayList<Country>()
    var onItemClick: ((Country) -> Unit)? = null

    fun setData(data: List<Country>?) {
        if (data == null) return
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                id.higo.core.R.layout.item_list_country,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCountryBinding.bind(itemView)

        fun bind(data: Country) {
            with(binding) {


                val uri = Uri.parse(data.flag)

                val requestBuilder = GlideToVectorYou
                    .init()
                    .with(itemView.context)
                    .requestBuilder

                requestBuilder
                    .load(uri)
                    .apply(RequestOptions()
                        .centerCrop())
                    .into(imgHome)


                tvTitle.text = data.countryName
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }
    }
}