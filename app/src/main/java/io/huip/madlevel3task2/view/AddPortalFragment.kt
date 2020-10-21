package io.huip.madlevel3task2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import io.huip.madlevel3task2.R
import kotlinx.android.synthetic.main.fragment_add_portal.*
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.setFragmentResult
import io.huip.madlevel3task2.model.Portal

const val BUNDLE_REMINDER_KEY = "bundle_reminder"
const val REQ_REMINDER_KEY = "req_reminder"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    private val portalAdapter = PortalAdapter(arrayListOf())

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPortal.setOnClickListener{
            addToPortalList()
        }

    }

    /**
     * Uses Bundle to pass arguments back to PortalFragment
     */
    private fun addToPortalList() {
        val portalTitle = etTitle.text.toString()
        val portalUrl = etURL.text.toString()
        val newPortal = Portal(portalTitle, portalUrl)

        //set the data as fragmentResult, we are listening for REQ_REMINDER_KEY in RemindersFragment!
        setFragmentResult(REQ_REMINDER_KEY, bundleOf(Pair(BUNDLE_REMINDER_KEY, newPortal)))

        findNavController().popBackStack() // Remove current fragment from stack.
    }

}