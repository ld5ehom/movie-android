plugins {
    id("module.android")
}

android {
    namespace = "com.ld5ehom.movie.common"
}

dependencies {
    implementation(projects.component)
    implementation(libs.gson)
}
