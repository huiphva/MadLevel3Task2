package io.huip.madlevel3task2.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import io.huip.madlevel3task2.R
import io.huip.madlevel3task2.model.Portal
import kotlinx.android.synthetic.main.portal_list_item.view.*

class PortalAdapter(val portals: List<Portal>): RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal: Portal) {
            itemView.tvTitle.text = portal.title
            itemView.tvURL.text = portal.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portal_list_item, parent, false)
        )
    }

    override fun getItemCount() = portals.size


    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])

        // Adds click listener to RecyclerView item.
        holder.itemView.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            builder.build().launchUrl(holder.itemView.context, Uri.parse(portals[position].url))
        }
    }

}