plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.hpm.saborapp"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.hpm.saborapp"

        minSdk = 33
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }
}

dependencies {

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Lista de recetas
    implementation(
        "androidx.recyclerview:recyclerview:1.4.0"
    )

    // Tarjetas
    implementation(
        "androidx.cardview:cardview:1.0.0"
    )

    // Media3 / ExoPlayer
    implementation(
        "androidx.media3:media3-common:1.9.2"
    )

    implementation(
        "androidx.media3:media3-exoplayer:1.9.2"
    )

    implementation(
        "androidx.media3:media3-ui:1.9.2"
    )

    // Pruebas
    testImplementation(
        libs.junit
    )

    androidTestImplementation(
        libs.androidx.junit
    )

    androidTestImplementation(
        libs.androidx.espresso.core
    )
}