package com.example.sportify.presentation.home

import PopularOrganizersAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sportify.data.Service
import com.example.sportify.databinding.FragmentHomeBinding
import com.example.sportify.entity.SportEvent
import com.example.sportify.entity.User
import com.example.sportify.presentation.home.adapter.MyViewHolder
import com.example.sportify.presentation.home.adapter.PopularEventAdapter
import com.example.sportify.presentation.home.adapter.PopularOrganizers
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var adapterOrganizers: PopularOrganizersAdapter
    private var dataList = mutableListOf<PopularOrganizers>()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var popularEvents: FirebaseRecyclerOptions<SportEvent>
    private lateinit var adapterEvents: FirebaseRecyclerAdapter<SportEvent, MyViewHolder>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val userList: MutableList<User> = mutableListOf()

        Service.getUsersDataRef().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val user = snapshot.getValue(User::class.java)
                    user?.let {
                        userList.add(it)
                    }
                }

                updateData(mapUsersToPopularOrganizers(userList))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибок, если не удалось получить данные из базы данных
            }
        })

        adapterOrganizers = PopularOrganizersAdapter(dataList)

        binding.popularOrganizersRv.adapter = adapterOrganizers

        loadData()
    }

    private fun updateData(newDataList: List<PopularOrganizers>) {
        dataList.clear()
        dataList.addAll(newDataList)
        adapterOrganizers.notifyDataSetChanged()
    }

    private fun loadData() {
        popularEvents = FirebaseRecyclerOptions.Builder<SportEvent>().setQuery(Service.getEventsDataRef(), SportEvent::class.java).build()
        adapterEvents = PopularEventAdapter(popularEvents)
        adapterEvents.startListening()
        binding.popularEventsRv.adapter = adapterEvents
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mapUsersToPopularOrganizers(users: List<User>): List<PopularOrganizers> {
        val popularOrganizersList = mutableListOf<PopularOrganizers>()
        for (user in users) {
            // Создаем объект PopularOrganizers на основе данных из объекта User
            val organizer = PopularOrganizers(
                organizerName = user.fullName,
                organizerStatus = user.userName,
                category = "Default Category" // Здесь вы можете установить значение по умолчанию или использовать другие поля из объекта User
            )
            popularOrganizersList.add(organizer)
        }
        return popularOrganizersList
    }

}