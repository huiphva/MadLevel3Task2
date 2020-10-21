package io.huip.madlevel3task2.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.huip.madlevel3task2.R
import io.huip.madlevel3task2.model.Portal
import kotlinx.android.synthetic.main.fragment_portal.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalFragment : Fragment() {

    private lateinit var portalAdapter: PortalAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var portals: ArrayList<Portal> = arrayListOf()

    /**
     * Launches the URL in Chrome
     */
    private fun portalClicked(portal: Portal) {
        val builder = CustomTabsIntent.Builder()
        builder.build().launchUrl(context, Uri.parse(portal.url))
    }

    private fun initRv() {
        portalAdapter = PortalAdapter(portals)
        viewManager = GridLayoutManager(activity, 2)

        rvPortal.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = portalAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated PF", "CREATING PORTAL FRAGMENT")

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_PortalFragment_to_AddPortalFragment)
        }

        initRv()
        observeAddPortal()
    }

    private fun observeAddPortal() {
        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_REMINDER_KEY)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }


}