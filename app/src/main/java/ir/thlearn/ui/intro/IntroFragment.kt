package ir.thlearn.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import ir.thlearn.R
import ir.thlearn.databinding.FragmentIntroBinding
import ir.thlearn.ui.intro.adapter.PagerAdapter
import ir.thlearn.ui.intro.models.Page

class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private var pager: ViewPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentIntroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(requireActivity(), R.id.intro_container);

        pager = binding.introPager
        val pages: ArrayList<Page> = ArrayList()

        pages.add(Page("test", "test description", R.drawable.ic_baseline_arrow_back_24))
        pages.add(Page("test", "test description", R.drawable.ic_baseline_arrow_back_24))
        pages.add(Page("test", "test description", R.drawable.ic_baseline_arrow_back_24))
        pages.add(Page("test", "test description", R.drawable.ic_baseline_arrow_back_24))

        pager!!.adapter = PagerAdapter(pages, object : PagerAdapter.Listener {
            override fun buttonClick() {
                navController.navigateUp()
                navController.navigate(R.id.action_introFragment_to_fragment_register)
            }
        })
    }
}
