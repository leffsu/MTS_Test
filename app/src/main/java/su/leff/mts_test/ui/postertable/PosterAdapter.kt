package su.leff.mts_test.ui.postertable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import su.leff.mts_test.R

/**
 * Адаптер для показа постеров.
 * @param columnNumber - количество колонок.
 * @param screenWidth - пара ширины и высоты экрана, чтобы картинки сами могли свой размер вычислить.
 */
class PosterAdapter(private val columnNumber: Int, private val screenWidth: Int) :
    RecyclerView.Adapter<PosterViewHolder>() {

    // Нужна ссылка на RecyclerView для плавного скролла вверх.
    lateinit var recyclerView: RecyclerView

    private val filmList = ArrayList<Poster>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder =
        PosterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_film,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(filmList[position], columnNumber, screenWidth)
    }

    /**
     * Функция меняет список элементов.
     * @param list - новый список.
     */
    fun setList(list: List<Poster>) {
        val diffResult = DiffUtil.calculateDiff(PosterDiffCallback(filmList, list))
        filmList.clear()
        filmList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
        // Плавный скролл вверх.
        recyclerView.smoothScrollToPosition(0)
    }
}