# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/conradomaderblanco/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepattributes EnclosingMethod


###############################################################################
## Gson models
###############################################################################

# Application classes that will be serialized/deserialized over Gson
-keep class ar.com.wolox.android.example.model.** { *; }

# PrettyTime
-keep class org.ocpsoft.prettytime.i18n.**