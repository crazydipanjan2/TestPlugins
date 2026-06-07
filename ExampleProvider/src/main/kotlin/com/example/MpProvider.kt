package com.Mxplayer

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.Qualities
import org.jsoup.Jsoup

class MXPlayerProvider : MainAPI() {
    override var mainUrl = "https://www.mxplayer.in"
    override var name = "MX Player IN"
    override val hasMainPage = true
    override var lang = "hi"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // Cloudstream App ke Home Page par kya dikhega
    override suspend fun getMainPage(page: Int, request: HomePageRequest): HomePageResponse? {
        val items = ArrayList<SearchResponse>()
        
        // Demo item jo aapko app me turant test karne ke liye dikhega
        items.add(
            MovieSearchResponse(
                "MX Player Test Video",
                "https://www.mxplayer.in/detail/movie/test-video",
                this.name,
                TvType.Movie,
                "https://www.mxplayer.in/images/mx-logo.png"
            )
        )
        return newHomePageResponse("Trending", items)
    }

    // Jab user item par click karega toh load hone wala data
    override suspend fun load(url: String): LoadResponse? {
        return newMovieLoadResponse("MX Player Test Video", url, TvType.Movie, url) {
            this.posterUrl = "https://www.mxplayer.in/images/mx-logo.png"
        }
    }

    // Video play karne ke liye streaming link provider
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // Yeh ek open-source direct testing MP4 video url hai
        val testingVideoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

        callback.invoke(
            ExtractorLink(
                this.name,
                this.name,
                testingVideoUrl,
                referer = mainUrl,
                quality = Qualities.P720.value,
                isM3u8 = false
            )
        )
        return true
    }
}
