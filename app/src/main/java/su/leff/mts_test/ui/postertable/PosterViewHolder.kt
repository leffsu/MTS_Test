package su.leff.mts_test.ui.postertable

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.viewholder_film.view.*

class PosterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    /**
     * Функция для показа постера.
     * @param poster - постер для показа.
     * @param columnNumber - количество колонок.
     * @param screenWidth - ширина экрана.
     */
    fun bind(poster: Poster, columnNumber: Int, screenWidth: Int) {
        Glide.with(view.context).load(poster.pictureUrl).into(view.imgPicture)
        view.imgPicture.layoutParams.width = screenWidth / columnNumber
    }
}