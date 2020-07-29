package su.leff.mts_test.ui.postertable

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import su.leff.mts_test.R
import su.leff.mts_test.viewmodel.PosterViewModel

class PosterActivity : AppCompatActivity() {

    private lateinit var posterViewModel: PosterViewModel
    private lateinit var adapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupAdapter()
        setupListeners()
    }

    /**
     * Функция устанавливает слушатели.
     */
    private fun setupListeners(){
        switchYear.setOnCheckedChangeListener { _, b ->
            if (b) {
                posterViewModel.filterByYear(2020)
            } else {
                posterViewModel.resetFilter()
            }
        }
    }

    /**
     * Функция инициализирует ViewModel.
     */
    private fun setupViewModel(){
        posterViewModel = ViewModelProvider(this).get(PosterViewModel::class.java)

        posterViewModel.filmsToShow.observe(this, Observer {
            adapter.setList(it)
        })
    }

    /**
     * Функция инициализирует адаптер.
     */
    private fun setupAdapter(){

        /**
         * Функция вычисляет количество колонок для показа постеров.
         * В портретной - 3, в альбомной - 5.
         */
        fun calculateColumnNumber(): Int {
            return when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    5
                }
                else -> {
                    3
                }
            }
        }

        val columnNumber = calculateColumnNumber()

        val gridLayoutManager = GridLayoutManager(this, columnNumber)
        adapter =
            PosterAdapter(
                columnNumber,
                resources.displayMetrics.widthPixels
            )

        rvFilms.layoutManager =
            gridLayoutManager
        rvFilms.adapter = adapter
    }
}