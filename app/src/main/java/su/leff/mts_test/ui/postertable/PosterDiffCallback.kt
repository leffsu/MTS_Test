package su.leff.mts_test.ui.postertable

import androidx.recyclerview.widget.DiffUtil

/**
 * Класс для красивой и быстрой подмены элементов в RecyclerView.
 */
class PosterDiffCallback(private val oldList: List<Poster>, private val newList: List<Poster>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pictureUrl == newList[newItemPosition].pictureUrl
    }
}