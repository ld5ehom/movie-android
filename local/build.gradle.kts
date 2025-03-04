plugins {
    id("module.android")
}
android {
    namespace = "com.ld5ehom.movie.local"
}
dependencies {
    implementation(projects.data)
    implementation(projects.common)

    implementation(libs.gson)
    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)
}
