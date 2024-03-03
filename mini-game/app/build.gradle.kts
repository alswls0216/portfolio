plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.ty_project"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.ty_project"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\assets")

            }
        }
    }

    buildFeatures {
//        dataBinding true
        viewBinding = true
    }

}

dependencies {



    // Dependency on local binaries
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

//###LHJ-----------------------------------------------------------------------


    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // jetpack navigation component
    implementation ("androidx.navigation:navigation-fragment:2.3.0")
    implementation ("androidx.navigation:navigation-ui:2.3.0")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.3.0")

    //UI
    //implementation ("com.google.android.material:material:1.0.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

//    implementation 'com.google.android.gms:play-services-auth:17.0.0'
//    implementation 'com.jaredrummler:material-spinner:1.3.1'
    implementation ("com.airbnb.android:lottie:2.7.0")
    implementation ("com.github.bumptech.glide:glide:4.11.0")

    // Reactive Streams (convert Observable to LiveData)

/////
    implementation ("android.arch.lifecycle:reactivestreams:1.6.1")

    // Room

    //RxJava
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.15")
    implementation ("androidx.room:room-runtime:2.2.6")
    annotationProcessor ("androidx.room:room-compiler:2.2.6")
    implementation ("androidx.room:room-rxjava2:2.2.6")

    // logger
    implementation ("com.jakewharton.timber:timber:4.5.1")

    //------------------------------------


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("com.github.scottyab:showhidepasswordedittext:0.8")
    //implementation ("com.androidx.support:recyclerview-v7:28.0.0")
//###LHJ-----------------------------------------------------------------------

    //roulette
    //implementation("com.github.jitpack:android-example:1.0.1")
    implementation("com.github.mmoamenn:LuckyWheel_Android:0.3.0")


}