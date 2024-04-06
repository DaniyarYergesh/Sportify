package com.example.sportify.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sportify.data.Service
import com.example.sportify.databinding.EventDetailsBottomSheetBinding
import com.example.sportify.entity.SportEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class EventDetailsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: EventDetailsBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var item: SportEvent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        item = arguments?.getSerializable("item") as SportEvent
        _binding = EventDetailsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            createButton.setOnClickListener {
                Service.subscribeToEvent(item) {

                    Toast.makeText(
                        requireContext(),
                        "You have joined",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            eventName.text = item.eventName
            eventLevel.text = item.level
            eventLocation.text = item.location
            eventPrice.text = item.price
            eventPlayersNumber.text = buildString {
                append(item.participantsNumber)
                append("/")
                append(item.maxParticipants)
            }
            eventDuration.text = item.duration
            eventTime.text = buildString {
                append(item.time)
                append(item.date)
            }
        }
    }

    companion object {
        fun newInstance(item: SportEvent): EventDetailsBottomSheet {
            val fragment = EventDetailsBottomSheet()
            val args = Bundle()
            // Передача данных в аргументы фрагмента
            args.putSerializable("item", item)
            fragment.arguments = args
            return fragment
        }
    }
}
