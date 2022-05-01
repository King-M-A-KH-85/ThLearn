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
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-keepattributes Signature
-keepattributes *Annotation*
-renamesourcefileattribute FuckYou
-obfuscationdictionary dictionary.txt
-packageobfuscationdictionary dictionary.txt
-classobfuscationdictionary dictionary.txt

-ignorewarnings
-dontwarn
-dontnote

-dontwarn sun.misc.**
-dontwarn android.arch.**
-dontwarn android.lifecycle.**
-keep class android.arch.** { *; }
-keep class android.lifecycle.** { *; }
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-dontwarn androidx.arch.**
-dontwarn androidx.lifecycle.**
-keep class androidx.arch.** { *; }
-keep class androidx.lifecycle.** { *; }

-keep class org.eclipse.lsp4j.** { *; }
-keep class com.itsaky.lsp.** { *; }

-keepclasseswithmembernames,includedescriptorclasses class * { native <methods>; }
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule { <init>(...); }
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder { *** rewind(); }

-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#You can specify any path and filename.
#-printconfiguration ~/tmp/full-r8-config.txt
