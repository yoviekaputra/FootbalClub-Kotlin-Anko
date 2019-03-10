package com.yeputra.footballclub.presenter

import com.yeputra.footballclub.R
import com.yeputra.footballclub.base.BasePresenter
import com.yeputra.footballclub.base.IBaseView
import com.yeputra.footballclub.model.Event
import com.yeputra.footballclub.model.Events
import com.yeputra.footballclub.repository.ApiRespository
import com.yeputra.footballclub.utils.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by yovi.putra
 *    on 09/Mar/2019 10:54
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class LeaguePresenter(val v: IBaseView): BasePresenter(v) {

    private val api: ApiRespository by lazy {
        RestClient
            .get()
            .create(ApiRespository::class.java)
    }

    fun getLastMatch(leagueId: String){
        v.showProgressbar()
        api.getLastMatch(leagueId).enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(context.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

    fun getNextMatch(leagueId: String){
        v.showProgressbar()
        api.getNextMatch(leagueId).enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(context.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

    fun getDetail(eventId: String) {
        v.showProgressbar()
        api.getDetail(eventId).enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                v.hideProgressbar()
                v.onPresenterFailed(context.getString(R.string.no_internet_connection))
            }
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                v.onPresenterSuccess(response.body())
                v.hideProgressbar()
            }
        })
    }

}