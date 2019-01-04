package com.andrew.associate.footballappfinal.match.detail

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("strTeamBadge")
    val badgeTeam: String? = ""
)