package com.divinkas.app.productmanager.bean.backend

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("first_name")
    @Expose
    var firstName:String,

    @SerializedName("last_name")
    @Expose
    var lastName:String,

    @SerializedName("avatar")
    @Expose
    var avatar:String
) : Serializable