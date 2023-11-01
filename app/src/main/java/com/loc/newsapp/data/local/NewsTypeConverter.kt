package com.loc.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.loc.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }
    @TypeConverter
    fun stringToSource(string: String): Source {
        return string.split(",").let {sourceArray ->
            Source(sourceArray[0],sourceArray[1])
        }
    }
}