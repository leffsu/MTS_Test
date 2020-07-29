package su.leff.mts_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import su.leff.mts_test.ui.postertable.Poster
import su.leff.network.PosterLoader
import su.leff.network.PosterResponse

class PosterViewModel : ViewModel() {

    private val filmLoader = PosterLoader()

    private val allFilms = MutableLiveData<List<Poster>>()
    val filmsToShow = MutableLiveData<List<Poster>>()

    init {

        filmLoader.loadPosters(object : Callback<List<PosterResponse>> {
            override fun onFailure(call: Call<List<PosterResponse>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(
                call: Call<List<PosterResponse>>,
                response: Response<List<PosterResponse>>
            ) {
                GlobalScope.launch {
                    response.body()?.let { films ->
                        val filmsList = ArrayList<Poster>()
                        films.forEach { filmsList.add(Poster(it.id, it.year, it.poster)) }
                        insertFilms(filmsList)
                    }
                }
            }
        })
    }

    private fun insertFilms(posters: List<Poster>) {
        allFilms.postValue(posters)
        filmsToShow.postValue(posters)
    }

    fun resetFilter() {
        GlobalScope.launch {
            filmsToShow.postValue(allFilms.value)
        }
    }

    fun filterByYear(year: Int) {
        GlobalScope.launch {
            allFilms.value?.let { films ->
                filmsToShow.postValue(films.filter { it.year == year })
            }
        }
    }
}