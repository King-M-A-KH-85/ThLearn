package ir.thlearn.ui.intro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.thlearn.databinding.IntroPageBinding
import ir.thlearn.ui.intro.models.Page

class PagerAdapter(private val pages: ArrayList<Page>, private val listener: Listener) :
    RecyclerView.Adapter<PagerAdapter.Holder>() {
    private lateinit var binding: IntroPageBinding

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = IntroPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.pageTitile.text = pages[position].name
        holder.view.pageBody.text = pages[position].description
        holder.view.pageImage.setImageResource(pages[position].image)

        if (position == pages.size - 1)
            holder.view.pageButton.visibility = View.VISIBLE

        holder.view.pageButton.setOnClickListener {
            listener.buttonClick()
        }
    }

    class Holder(val view: IntroPageBinding) : RecyclerView.ViewHolder(view.root)

    interface Listener {
        fun buttonClick()
    }
}