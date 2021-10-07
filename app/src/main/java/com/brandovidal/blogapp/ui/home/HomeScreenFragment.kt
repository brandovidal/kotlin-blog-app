package com.brandovidal.blogapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.brandovidal.blogapp.R
import com.brandovidal.blogapp.core.Resource
import com.brandovidal.blogapp.data.remote.home.HomeScreenDataSource
import com.brandovidal.blogapp.databinding.FragmentHomeScreenBinding
import com.brandovidal.blogapp.domain.home.HomeScreenRepoImpl
import com.brandovidal.blogapp.presentation.HomeScreenViewModel
import com.brandovidal.blogapp.presentation.HomeScreenViewModelFactory
import com.brandovidal.blogapp.ui.home.adapter.HomeScreenAdapter

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(
        HomeScreenRepoImpl(
        HomeScreenDataSource()
    )
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPost().observe(viewLifecycleOwner, { result ->
            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Ocurri un error:  ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

//        val postList = listOf(
//            Post("https://avatars.githubusercontent.com/u/65886822?v=4", "Brando Vidal", Timestamp.now(), "https://i.pinimg.com/originals/72/a4/da/72a4daa7ab57cdf086fbb85287c1a7b2.jpg"),
//            Post("https://i.etsystatic.com/22360457/r/il/f4d7a0/2328291555/il_570xN.2328291555_tjo4.jpg", "Doom", Timestamp.now(), "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2021/06/doom-eternal-2390613.jpg"),
//            Post("https://ih1.redbubble.net/image.491172038.6356/flat,750x1000,075,f.u1.jpg", "Resident Evil", Timestamp.now(), "https://fondosmil.com/fondo/54695.jpg"),
//        )
//        binding.rvHome.adapter = HomeScreenAdapter(postList)
    }
}