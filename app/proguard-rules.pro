# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-keep class com.example.MyMissingClass { *; }

# Mantén las clases que usan Kotlin Serialization
-keep,allowobfuscation @kotlinx.serialization.Serializable class *
-keepclassmembers class * {
    *** Companion;
}
-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}

# Evita la eliminación de metadatos necesarios
-keepnames class kotlinx.serialization.** { *; }
-keepnames class * { @kotlinx.serialization.Serializable *; }

-keep class com.google.firebase.** { *; }
-keepnames class com.google.firebase.**
-keepclassmembers class * {
    @com.google.firebase.** *;
}

# Mantener la clase Carta y sus propiedades
-keepclassmembers class org.rulsoft.ap.nb22.domain.carta.model.Carta {
    <fields>;
}

# Opcionalmente, mantener todas las clases en el paquete "domain.carta.model"
-keep class org.rulsoft.ap.nb22.domain.carta.model.** { *; }