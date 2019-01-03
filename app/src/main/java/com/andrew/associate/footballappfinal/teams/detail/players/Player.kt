package com.andrew.associate.footballappfinal.teams.detail.players

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
    @SerializedName("idTeam")
    var IdTeam: String? = null,

    @SerializedName("strPlayer")
    var strPlayer: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strPosition")
    var strPosition: String? = null,

    @SerializedName("strWeight")
    var strWeight: String? = null,

    @SerializedName("strHeight")
    var strHeight: String? = null,

    @SerializedName("strCutout")
    var strCutOut: String? = null,

    @SerializedName("strFanart1")
    var strFanArt1: String? = null
): Parcelable