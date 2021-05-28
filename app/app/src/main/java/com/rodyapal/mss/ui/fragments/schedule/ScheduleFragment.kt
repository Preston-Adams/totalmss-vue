package com.rodyapal.mss.ui.fragments.schedule

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodyapal.mss.R
import com.rodyapal.mss.data.model.one.Lesson
import com.rodyapal.mss.databinding.ScheduleFragmentBinding
import com.rodyapal.mss.utils.capital
import com.rodyapal.mss.viewmodels.ScheduleViewModel
import java.util.*

class ScheduleFragment : Fragment() {

	private lateinit var scheduleViewModel: ScheduleViewModel

	private var _binding: ScheduleFragmentBinding? = null
	private val binding get() = _binding!!

	private val args: ScheduleFragmentArgs by navArgs()

	private val scheduleListController: ScheduleListController by lazy {
		ScheduleListController()
	}

	private var fragmentTitle: String = "Schedule"
		set(value) {
			field = value
			activity?.let { activity -> (activity as AppCompatActivity).supportActionBar?.title = fragmentTitle }!!
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		scheduleViewModel = ViewModelProvider(requireActivity()).get(ScheduleViewModel::class.java)
	}

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View {
		_binding = ScheduleFragmentBinding.inflate(inflater, container, false)
		setHasOptionsMenu(true)
		setUpRecyclerView()
		getSchedule()
		return binding.root
	}

	private fun getSchedule() {
		if (args.groupName == null) {
			Toast.makeText(requireContext(), "Something gone wrong...", Toast.LENGTH_SHORT).show()
			findNavController().navigateUp()
		} else args.groupName?.let { name ->
			scheduleListController.isLoading = true
			scheduleViewModel.getDataForGroup(name)
			scheduleViewModel.group.observe(viewLifecycleOwner) { group ->
				with(scheduleViewModel) {
					val data: List<Lesson> = getTimetableForWeek(group)
					scheduleListController.data = data
					fragmentTitle = getDayFromSchedule(group.schedule).capital()
				}
			}
		}
	}

	private fun setUpRecyclerView() {
		binding.schRvSchedule.setController(scheduleListController)
		binding.schRvSchedule.layoutManager =
				LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.schedule_fragment_menu, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.sch_menu_refresh_data -> args.groupName?.let { name ->
				scheduleListController.isLoading = true
				scheduleViewModel.refreshDataForGroup(name)
			}
		}
		return true
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}