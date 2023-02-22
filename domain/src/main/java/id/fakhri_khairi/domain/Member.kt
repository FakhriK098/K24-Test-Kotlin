package id.fakhri_khairi.domain

data class Member(
    val id: Int,
    val name: String,
    val dateOfBirth: String,
    val address: String,
    val genre: String,
    val username: String,
    val password: String,
)
