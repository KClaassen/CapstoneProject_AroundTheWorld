package com.example.android.capstoneproject_aroundtheworld.adapter

import android.animation.LayoutTransition
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.android.capstoneproject_aroundtheworld.R
import com.example.android.capstoneproject_aroundtheworld.databinding.ItemTripAddImageBinding
import com.example.android.capstoneproject_aroundtheworld.databinding.ItemTripViewImageBinding
import com.example.android.capstoneproject_aroundtheworld.models.TripImage
import kotlinx.android.synthetic.main.item_trip_add_image.view.*
import kotlinx.android.synthetic.main.item_trip_view_image.view.*

class ImageListAdapter(val context: Context, val clicklistener: ImageListListener, val item: ArrayList<TripImage>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val IMAGE_ADD = 1
        private const val IMAGE_VIEW = 2
    }

    var images: List<String> = listOf()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): RecyclerView.ViewHolder {
        if (viewType == IMAGE_ADD) {
             return ImageAddViewHolder(
                     LayoutInflater.from(context).inflate(
                             R.layout.item_trip_add_image,
                             parent, false))
        } else {
             return ImageViewViewHolder(
                     LayoutInflater.from(context).inflate(
                             R.layout.item_trip_view_image,
                             parent, false))
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageAddViewHolder) {
            holder.addItem.setImageResource(R.drawable.outline_add_24)
        } else if (holder is ImageViewViewHolder) {
            // Add image from Camera or Gallery
            //holder.viewItem.setImageResource()
        }

    }

    class ImageAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val addItem = itemView.trip_detail_add_image

//        fun bindView(position: Int) {
//
//        }
    }

    class ImageViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val viewItem = itemView.trip_detail_view_image

//        fun bindView(position: Int) {
//
//        }
    }


    override fun getItemCount(): Int {
        return images.size
    }

    override fun getItemViewType(position: Int): Int {
        return item[position].viewType
    }

    class ImageListListener(val clickListener: () -> Unit) {
        fun onClick() = clickListener()
    }

}