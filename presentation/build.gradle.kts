plugins {
    id("module.android")
    id("kotlin-parcelize")
}
android {
    namespace = "com.ld5ehom.movie.presentaion"
}
dependencies {
    implementation(projects.common)
    implementation(projects.domain)
    implementation(projects.component)
    implementation(projects.dataResource)

    implementation(libs.androidx.viewmodel)
}
