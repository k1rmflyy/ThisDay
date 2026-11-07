package com.gorbkostya.thisday.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OnThisDayResponseDto(
    val selected: List<OnThisDayItemDto> = emptyList(),
    val events: List<OnThisDayItemDto> = emptyList(),
    val births: List<OnThisDayItemDto> = emptyList(),
    val holidays: List<OnThisDayItemDto> = emptyList()
)


@Serializable
data class OnThisDayItemDto(
    val text: String,
    val year: Int? = null,
    val pages: List<PageDTO> = emptyList()
)


@Serializable
data class PageDTO(
    val titles: TitlesDto,
    val description: String? = null,
    val extract: String? = null,
    val thumbnail: ImageDto? = null,
    @SerialName("content_urls") val contentUrls: ContentUrlsDto,
)



@Serializable
data class TitlesDto(
    val normalized: String,
)

@Serializable
data class ImageDto(
    val source: String,
)

@Serializable
data class ContentUrlsDto(
    val mobile: UrlDto,
)

@Serializable
data class UrlDto(
    val page: String,
)