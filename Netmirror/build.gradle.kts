// use an integer for version numbers
version = 1

android {
    namespace = "com.netmirror"
}

cloudstream {
    language = "en"
    description = "NetMirror Plugin"
    authors = listOf("Jules")

    /**
     * Status int as the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta only
     * */
    status = 1 // will be 3 if unspecified
    tvTypes = listOf(
        "Movie",
        "TvSeries"
    )

    iconUrl = "https://www.google.com/s2/favicons?domain=https://netmirror.one&sz=%size%"

    isCrossPlatform = false
}
