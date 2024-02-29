package com.example.expedro2ev

import android.os.Parcel
import android.os.Parcelable

class VideoJuegos(private var nombre : String?, private var valoracion : Float, private var empresa : String?, private var fecha : Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeFloat(valoracion)
        parcel.writeString(empresa)
        parcel.writeInt(fecha)
    }

    fun getNombre() : String?{
        return nombre
    }
    fun getValoracion() : Float{
        return valoracion
    }
    fun getEmpresa() : String?{
        return empresa
    }
    fun getFecha() : Int{
        return fecha
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "VideoJuegos(nombre=$nombre, valoracion=$valoracion, empresa=$empresa, fecha=$fecha)"
    }

    companion object CREATOR : Parcelable.Creator<VideoJuegos> {
        override fun createFromParcel(parcel: Parcel): VideoJuegos {
            return VideoJuegos(parcel)
        }

        override fun newArray(size: Int): Array<VideoJuegos?> {
            return arrayOfNulls(size)
        }
    }

}