package su.leff.network

import retrofit2.Callback

class PosterLoader{
    /**
     * Функция реализует загрузку постеров.
     * @param callback - callback для обработки ответа.
     */
    fun loadPosters(callback: Callback<List<PosterResponse>>){
        val call = api.signUp()
        call.enqueue(callback)
    }
}