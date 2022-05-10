package com.thanushaw.university_locations_219.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thanushaw.university_locations_219.R
import com.thanushaw.university_locations_219.apis.University
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class RecyclerViewAdapter(private val uniList: List<University>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        private val clickSubject = PublishSubject.create<String>()
        // create new views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view
            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_design, parent, false)

            return ViewHolder(view)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val uni = uniList[position]

            holder.name.text = uni.name
            holder.coordinats.text = uni.coordinates.toString()
        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return uniList.size
        }

        // Holds the views for adding it to image and text
        inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val name: TextView = itemView.findViewById(R.id.textView_name)
            val coordinats: TextView = itemView.findViewById(R.id.textView_coordinates)
            init {
                itemView.setOnClickListener {
                    clickSubject.onNext(name.text as String)
                }
            }
        }
    val clickEvent : Observable<String> = clickSubject
    }
