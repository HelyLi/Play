# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/SimonXu/Dev/Tools/android-sdk-macosx/tools/proguard/proguard-android.txt
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
-dontskipnonpubliclibraryclassmembers
-dontoptimize
-optimizationpasses 5 # 指定代码的压缩级别
-dontusemixedcaseclassnames  # 指定代码的压缩级别
-dontpreverify  # 混淆时是否做预校验
-verbose    # 混淆时是否记录日志
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,Signature

-keepattributes *Annotation* # 假如项目中有用到注解 应用加入

# 保持哪些类不被混淆
-keep class android.** {*; }
-keep public class * extends android.view
-keep public class * extends android.app.AppCompatActivity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.pm
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.v4.app.Fragment

-keep class * extends java.lang.annotation.Annotation { *; }

# Rxjava rules
-dontwarn rx.internal.util.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

# PushSDK
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

-dontwarn com.sina.weibo.**
-keep class com.sina.weibo.** {*;}

-dontwarn com.tencent.**
-keep class com.tencent.** {*;}

-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties

## New rules for EventBus 3.0.x ##
# http://greenrobot.org/eventbus/documentation/proguard/

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep class org.greenrobot.eventbus.util.** { *; }
-keep class org.greenrobot.greendao.** { *;}
-keep class org.greenrobot.greendao.async.** { *;}
-keep class org.greenrobot.greendao.identityscope.** { *;}
-keep class org.greenrobot.greendao.internal.** { *;}
-keep class org.greenrobot.greendao.query.** { *;}

-dontwarn master.flame.danmaku.**
-keep class master.flame.danmaku.** {*;}

-dontwarn android.support.v4.**
-keep class android.support.v4.** {*;}

-dontwarn com.google.gson.**
-keep class com.google.gson.** {*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}


#rx
-dontwarn rx.**
-keep class rx.**{*;}
-keep interface rx.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keepclassmembers class ** {
    public void on*(**);
}
-keepclassmembers class * {
    public <init>(org.json.JSONObject);
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

#保持自定义控件类不被混淆
-keepclassmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
#保持自定义控件类不被混淆
-keepclassmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    *;
}

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}


-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-keepclassmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keepclassmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Also keep - Serialization code. Keep all fields and methods that are used for
# serialization.
-keepclassmembers class * extends java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

-keepattributes Exceptions,InnerClasses
-keep public class com.alipay.android.app.** {
    public <fields>;
    public <methods>;
}

# Keep names - Native method names. Keep all native class/method names.
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}

-keepclasseswithmembers,allowshrinking class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keepclasseswithmembers,allowshrinking class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}


-ignorewarning
-keep public class * extends android.widget.TextView


-keepattributes *Annotation*
-keepattributes *JavascriptInterface*


#--------------alipay-------------
-keep class com.ta.utdid2.** {
    public <fields>;
    public <methods>;
}
-keep class com.ut.device.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.android.app.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.sdk.** {
    public <fields>;
    public <methods>;
}
-keep class com.alipay.mobilesecuritysdk.** {
    public <fields>;
    public <methods>;
}
-keep class HttpUtils.** {
    public <fields>;
    public <methods>;
}

#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

-keep class com.ksy.recordlib.** { *;}
-keep class com.ksyun.media.player.** {*;}
-keep class com.ksy.statlibrary.** {*;}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


#微信
-keep class com.tencent.mm.sdk.** {
   *;
}

#webview
-keepclassmembers class * extends android.webkit.WebChromeClient{
   		public void openFileChooser(...);
}

#支付宝
#-libraryjars libs/alipaySdk-20160825.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
#-keep class com.yunva.changke.thrid.qq.QQTokenInfo.** { *; }
#-keep class com.yunva.changke.thrid.qq.QQUserInfo.** { *; }
#-keep class com.yunva.changke.thrid.wechat.WechatTokenInfo.** { *; }
#-keep class com.yunva.changke.thrid.wechat.WechatUserInfo.** { *; }
#-keep class com.yunva.changke.thrid.weibo.model.WeiboUserInfo.** { *; }
#-keep class com.yunva.changke.pay.OrderReq.** { *; }
#-keep class com.yunva.changke.pay.OrderResp.** { *; }

#-keep class com.yunva.changke.network.protocol.push.message.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for Gson  ----------
#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

-dontwarn com.hhly.lyygame.data.net.**
-keep public class com.hhly.lyygame.data.net.**{*;}

-keep class com.chad.library.adapter.** {
   *;
}

#友盟统计SDK ------ start  ------------
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.hhly.lyygame.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# -------------- end ------------

#支付宝SDK  -----start ----------
-libraryjars libs/alipaySDK-20150602.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
# ---------------- end  -------------