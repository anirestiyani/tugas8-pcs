package resti.example.appmemori.ui.resep

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import resti.example.appmemori.R
import resti.example.appmemori.databinding.FragmentResepBinding
import resti.example.appmemori.ui.home.MainActivity

class ResepFragment : Fragment() {
    private val parent: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentResepBinding
    private val viewModel: ResepViewModel by lazy { ResepViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResepBinding.inflate(inflater, container, false).apply {
            viewModel = this@ResepFragment.viewModel
            lifecycleOwner = this@ResepFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()
    }


    private fun init() {
        binding.recyclerView.adapter = ResepAdaptor(parent)
        viewModel.listResep()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.listResep()
        }
    }
    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner){
        binding.swipeRefresh.isRefreshing = it
        }

        viewModel.actionState.observe(viewLifecycleOwner){
            if (it.isConsumed){
                Log.i("ActionState", "isConsumed")
            } else if (!it.isSuccess){
                Toast.makeText(parent, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}