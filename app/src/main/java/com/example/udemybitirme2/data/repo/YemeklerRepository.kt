package com.example.udemybitirme2.data.repo

import com.example.udemybitirme2.data.datasource.YemeklerDataSource
import com.example.udemybitirme2.data.entity.SepetYemekler
import com.example.udemybitirme2.data.entity.Yemekler

class YemeklerRepository(var yDso:YemeklerDataSource) {



    suspend fun sepetYemeksil(sepet_yemek_id:Int,kullanici_adi: String) = yDso.sepetYemekSil(sepet_yemek_id,kullanici_adi)
    suspend fun yemekleriYukle() : List<Yemekler> = yDso.yemekleriListele()

    suspend fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) = yDso.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sepetYukle(kullanici_adi: String): List<SepetYemekler> = yDso.sepetYukle(kullanici_adi)
}