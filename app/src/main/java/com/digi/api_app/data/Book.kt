package com.digi.api_app.data

data class Volume(
    var totalItems: Int? = null,
    var items: List<Book> = arrayListOf()
)

data class Book(
    var volumeInfo: VolumeInfo? = VolumeInfo(),
    var saleInfo: SaleInfo? = SaleInfo(),
)

data class VolumeInfo(
    var title: String? = null,
    var authors: List<String> = arrayListOf(),
    var publisher: String? = null,
    var publishedDate: String? = null,
    var description: String? = null,
    var pageCount: Int? = null,
    var printType: String? = null,
    var categories: List<String> = arrayListOf(),
    var averageRating: Int? = null,
    var ratingsCount: Int? = null,
    var maturityRating: String? = null,
    var allowAnonLogging: Boolean? = null,
    var contentVersion: String? = null,
    var imageLinks: ImageLinks? = ImageLinks(),
    var language: String? = null,
    var previewLink: String? = null,
    var infoLink: String? = null,
    var canonicalVolumeLink: String? = null
)

data class ImageLinks(
    var smallThumbnail: String? = null,
    var thumbnail: String? = null
)

data class SaleInfo(
    var country: String? = null,
    var saleability: String? = null,
    var isEbook: Boolean? = null,
    var retailPrice: RetailPrice? = RetailPrice(),
    var buyLink: String? = null,
)

data class RetailPrice(
    var amountInMicros: Int? = null,
    var currencyCode: String? = null
)