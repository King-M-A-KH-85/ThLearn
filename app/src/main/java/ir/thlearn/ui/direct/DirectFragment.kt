package ir.thlearn.ui.direct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ir.thlearn.R
import ir.thlearn.databinding.MainFragmentBinding

class DirectFragment : Fragment() {

    companion object {
        fun newInstance() = DirectFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: DirectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DirectViewModel::class.java]
        // TODO: Use the ViewModel

        setHasOptionsMenu(true)

        val navController = Navigation.findNavController(requireActivity(), R.id.container);
        navController.navigateUp();
        navController.navigate(R.id.action_mainFragment_to_profileFragment2);

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.add(1, 1, 1, "sdfdf")
    }
}