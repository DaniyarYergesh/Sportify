package com.example.sportify.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportify.data.Service
import com.example.sportify.databinding.FragmentNotificationsBinding
import com.example.sportify.entity.SportEvent
import com.example.sportify.presentation.home.adapter.PopularEventAdapter
import com.example.sportify.presentation.home.adapter.PopularEventViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularEvents: FirebaseRecyclerOptions<SportEvent>
    private lateinit var adapterEvents: FirebaseRecyclerAdapter<SportEvent, PopularEventViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        notificationsViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
        loadDataRV2()
    }

    private fun loadData() {
        popularEvents =
            FirebaseRecyclerOptions.Builder<SportEvent>()
                .setQuery(Service.getEventsDataRef(), SportEvent::class.java)
                .build()
        adapterEvents = PopularEventAdapter(popularEvents, {})
        adapterEvents.startListening()
        binding.popularEventsRv.adapter = adapterEvents
    }

    private fun loadDataRV2() {
        popularEvents =
            FirebaseRecyclerOptions.Builder<SportEvent>()
                .setQuery(Service.getEventsDataRef(), SportEvent::class.java)
                .build()
        adapterEvents = PopularEventAdapter(popularEvents, {})
        adapterEvents.startListening()
        binding.popularOrganizersRv.adapter = adapterEvents
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}