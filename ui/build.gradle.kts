plugins {
    id("module.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.ld5ehom.movie.ui"
}

dependencies {
    implementation(projects.common)
    implementation(projects.design)
    implementation(projects.component)
    implementation(projects.dataResource)
    implementation(projects.presentation)


    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.glide)
    kapt(libs.glide.compiler)

    implementation("kr.co.prnd:readmore-textview:1.0.0")
}
