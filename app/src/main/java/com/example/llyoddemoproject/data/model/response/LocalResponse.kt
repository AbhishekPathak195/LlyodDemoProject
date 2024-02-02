package com.example.llyoddemoproject.data.model.response

import com.example.llyoddemoproject.domain.model.Images


data class LocalResponse(
    val status: Boolean?,
    val total_photos: Int?,
    val photos: List<Images>?,
) {
    companion object {
        fun mock() = LocalResponse(
            status = true,
            total_photos = 3,
            photos = listOf(
                Images(
                    url = "https://api.slingacademy.com/public/sample-photos/1.jpeg",
                    title = "Defense the travel audience hand",
                    user = 28,
                    id = 1,
                    description = "Leader structure safe or black late wife newspaper her pick central forget single likely."
                ),
                Images(
                    url = "https://api.slingacademy.com/public/sample-photos/4.jpeg",
                    title = "Table husband",
                    user = 22,
                    id = 4,
                    description = "Skill drug college contain herself future seat human yes approach how then maybe public summer play commercial anything woman include million body measure government clearly question quickly parent."
                ),
                Images(
                    url = "https://api.slingacademy.com/public/sample-photos/9.jpeg",
                    title = "Per nature research",
                    user = 2,
                    id = 9,
                    description = "Nature focus wonder behind magazine pattern degree far without tree consider."
                ),
            )
        )
    }
}