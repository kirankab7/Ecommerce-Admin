package com.grafocrate.ecommerceapp_admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.grafocrate.ecommerceapp_admin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private val categories = listOf(
        Category("Vegetables", R.drawable.ic_vegetables),
        Category("Fruits", R.drawable.ic_fruits),
        Category("Dairy", R.drawable.ic_dairy),
        Category("Toiletries", R.drawable.ic_toiletries),
        Category("Cereals", R.drawable.ic_cereals),
        Category("Pulses", R.drawable.ic_pulses),
        Category("Instant Foods", R.drawable.ic_instant_foods),
        Category("Vessels", R.drawable.ic_vessels),
        Category("Oils", R.drawable.ic_oils),
        Category("Drinks", R.drawable.ic_drinks)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
    }

    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter(categories)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = categoryAdapter
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search text change
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}