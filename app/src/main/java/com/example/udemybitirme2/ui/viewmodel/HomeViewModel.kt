package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemybitirme2.data.entity.FavoriYemekler
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception


@HiltViewModel
class HomeViewModel @Inject constructor(var yRepo:YemeklerRepository) : ViewModel () {


    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var favoriYemeklerListesi = MutableLiveData<List<FavoriYemekler>>()

    init {
        yemekleriYukle()
        favoriYemekleriYukle()
    }
    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yemeklerListesi.value = yRepo.yemekleriYukle()
            }catch (e:Exception){

            }
        }
    }

    fun favoriYemekleriYukle(){

        CoroutineScope(Dispatchers.Main).launch {
            favoriYemeklerListesi.value = yRepo.favoriYemekleriYukle()
        }
    }

    fun favYemekEkle(yemek_id: Int,yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.favYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat)
        }
    }

    fun sil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.favSil(yemek_id)
        }
    }






}