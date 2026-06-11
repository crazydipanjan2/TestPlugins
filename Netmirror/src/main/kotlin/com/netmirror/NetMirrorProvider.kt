package com.netmirror

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class NetMirrorProvider : MainAPI() {
    override var mainUrl = "https://netmirror.one"
    override var name = "NetMirror"
    override val hasMainPage = true
    override var lang = "en"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse? {
        val items = listOf(
            newMovieSearchResponse(
                "NetMirror Test Video",
                "https://netmirror.one/test-video",
                TvType.Movie,
            ) {
                this.posterUrl = "https://netmirror.one/favicon.ico"
            }
        )
        return newHomePageResponse(listOf(HomePageList("Trending", items)), false)
    }

    override suspend fun load(url: String): LoadResponse? {
        return newMovieLoadResponse("NetMirror Test Video", url, TvType.Movie, url) {
            this.posterUrl = "https://netmirror.one/favicon.ico"
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val testingVideoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

        callback.invoke(
            newExtractorLink(
                this.name,
                this.name,
                testingVideoUrl
            ) {
                this.referer = mainUrl
                this.quality = Qualities.P720.value
            }
        )
        return true
    }
}
