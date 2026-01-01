package androidx.core.provider;

import android.content.Context;
import android.graphics.Typeface;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat$$ExternalSyntheticBackport0;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

class FontRequestWorker {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
    static final Object LOCK = new Object();
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = new SimpleArrayMap<>();
    static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);

    private FontRequestWorker() {
    }

    static void resetTypefaceCache() {
        sTypefaceCache.evictAll();
    }

    static Typeface requestFontSync(final Context context, final FontRequest fontRequest, CallbackWrapper callbackWrapper, final int i, int i2) {
        final String createCacheId = createCacheId(TypefaceCompat$$ExternalSyntheticBackport0.m(new Object[]{fontRequest}), i);
        Typeface typeface = sTypefaceCache.get(createCacheId);
        if (typeface != null) {
            callbackWrapper.onTypefaceResult(new TypefaceResult(typeface));
            return typeface;
        } else if (i2 == -1) {
            TypefaceResult fontSync = getFontSync(createCacheId, context, TypefaceCompat$$ExternalSyntheticBackport0.m(new Object[]{fontRequest}), i);
            callbackWrapper.onTypefaceResult(fontSync);
            return fontSync.mTypeface;
        } else {
            try {
                TypefaceResult typefaceResult = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, new Callable<TypefaceResult>() {
                    public TypefaceResult call() {
                        return FontRequestWorker.getFontSync(createCacheId, context, TypefaceCompat$$ExternalSyntheticBackport0.m(new Object[]{fontRequest}), i);
                    }
                }, i2);
                callbackWrapper.onTypefaceResult(typefaceResult);
                return typefaceResult.mTypeface;
            } catch (InterruptedException unused) {
                callbackWrapper.onTypefaceResult(new TypefaceResult(-3));
                return null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        r9 = new androidx.core.provider.FontRequestWorker.AnonymousClass3();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r8 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r8 = DEFAULT_EXECUTOR_SERVICE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        androidx.core.provider.RequestExecutor.execute(r8, r9, new androidx.core.provider.FontRequestWorker.AnonymousClass4());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Typeface requestFontAsync(final android.content.Context r5, final java.util.List<androidx.core.provider.FontRequest> r6, final int r7, java.util.concurrent.Executor r8, final androidx.core.provider.CallbackWrapper r9) {
        /*
            java.lang.String r0 = createCacheId(r6, r7)
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r1 = sTypefaceCache
            java.lang.Object r1 = r1.get(r0)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L_0x0017
            androidx.core.provider.FontRequestWorker$TypefaceResult r5 = new androidx.core.provider.FontRequestWorker$TypefaceResult
            r5.<init>((android.graphics.Typeface) r1)
            r9.onTypefaceResult(r5)
            return r1
        L_0x0017:
            androidx.core.provider.FontRequestWorker$2 r1 = new androidx.core.provider.FontRequestWorker$2
            r1.<init>()
            java.lang.Object r9 = LOCK
            monitor-enter(r9)
            androidx.collection.SimpleArrayMap<java.lang.String, java.util.ArrayList<androidx.core.util.Consumer<androidx.core.provider.FontRequestWorker$TypefaceResult>>> r2 = PENDING_REPLIES     // Catch:{ all -> 0x004d }
            java.lang.Object r3 = r2.get(r0)     // Catch:{ all -> 0x004d }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x004d }
            r4 = 0
            if (r3 == 0) goto L_0x002f
            r3.add(r1)     // Catch:{ all -> 0x004d }
            monitor-exit(r9)     // Catch:{ all -> 0x004d }
            return r4
        L_0x002f:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x004d }
            r3.<init>()     // Catch:{ all -> 0x004d }
            r3.add(r1)     // Catch:{ all -> 0x004d }
            r2.put(r0, r3)     // Catch:{ all -> 0x004d }
            monitor-exit(r9)     // Catch:{ all -> 0x004d }
            androidx.core.provider.FontRequestWorker$3 r9 = new androidx.core.provider.FontRequestWorker$3
            r9.<init>(r0, r5, r6, r7)
            if (r8 != 0) goto L_0x0044
            java.util.concurrent.ExecutorService r8 = DEFAULT_EXECUTOR_SERVICE
        L_0x0044:
            androidx.core.provider.FontRequestWorker$4 r5 = new androidx.core.provider.FontRequestWorker$4
            r5.<init>(r0)
            androidx.core.provider.RequestExecutor.execute(r8, r9, r5)
            return r4
        L_0x004d:
            r5 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x004d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.requestFontAsync(android.content.Context, java.util.List, int, java.util.concurrent.Executor, androidx.core.provider.CallbackWrapper):android.graphics.Typeface");
    }

    private static String createCacheId(List<FontRequest> list, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            sb.append(list.get(i2).getId()).append("-").append(i);
            if (i2 < list.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:31|32|33|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r4 = new androidx.core.provider.FontRequestWorker.TypefaceResult(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        androidx.tracing.Trace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006a, code lost:
        return r4;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0061 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontRequestWorker.TypefaceResult getFontSync(java.lang.String r4, android.content.Context r5, java.util.List<androidx.core.provider.FontRequest> r6, int r7) {
        /*
            java.lang.String r0 = "getFontSync"
            androidx.tracing.Trace.beginSection(r0)
            androidx.collection.LruCache<java.lang.String, android.graphics.Typeface> r0 = sTypefaceCache     // Catch:{ all -> 0x006b }
            java.lang.Object r1 = r0.get(r4)     // Catch:{ all -> 0x006b }
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0018
            androidx.core.provider.FontRequestWorker$TypefaceResult r4 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x006b }
            r4.<init>((android.graphics.Typeface) r1)     // Catch:{ all -> 0x006b }
            androidx.tracing.Trace.endSection()
            return r4
        L_0x0018:
            r1 = 0
            androidx.core.provider.FontsContractCompat$FontFamilyResult r6 = androidx.core.provider.FontProvider.getFontFamilyResult(r5, r6, r1)     // Catch:{ NameNotFoundException -> 0x0061 }
            int r2 = getFontFamilyResultStatus(r6)     // Catch:{ all -> 0x006b }
            if (r2 == 0) goto L_0x002c
            androidx.core.provider.FontRequestWorker$TypefaceResult r4 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x006b }
            r4.<init>((int) r2)     // Catch:{ all -> 0x006b }
            androidx.tracing.Trace.endSection()
            return r4
        L_0x002c:
            boolean r2 = r6.hasFallback()     // Catch:{ all -> 0x006b }
            if (r2 == 0) goto L_0x0041
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x006b }
            r3 = 29
            if (r2 < r3) goto L_0x0041
            java.util.List r6 = r6.getFontsWithFallbacks()     // Catch:{ all -> 0x006b }
            android.graphics.Typeface r5 = androidx.core.graphics.TypefaceCompat.createFromFontInfoWithFallback(r5, r1, r6, r7)     // Catch:{ all -> 0x006b }
            goto L_0x0049
        L_0x0041:
            androidx.core.provider.FontsContractCompat$FontInfo[] r6 = r6.getFonts()     // Catch:{ all -> 0x006b }
            android.graphics.Typeface r5 = androidx.core.graphics.TypefaceCompat.createFromFontInfo(r5, r1, r6, r7)     // Catch:{ all -> 0x006b }
        L_0x0049:
            if (r5 == 0) goto L_0x0057
            r0.put(r4, r5)     // Catch:{ all -> 0x006b }
            androidx.core.provider.FontRequestWorker$TypefaceResult r4 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x006b }
            r4.<init>((android.graphics.Typeface) r5)     // Catch:{ all -> 0x006b }
            androidx.tracing.Trace.endSection()
            return r4
        L_0x0057:
            androidx.core.provider.FontRequestWorker$TypefaceResult r4 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x006b }
            r5 = -3
            r4.<init>((int) r5)     // Catch:{ all -> 0x006b }
            androidx.tracing.Trace.endSection()
            return r4
        L_0x0061:
            androidx.core.provider.FontRequestWorker$TypefaceResult r4 = new androidx.core.provider.FontRequestWorker$TypefaceResult     // Catch:{ all -> 0x006b }
            r5 = -1
            r4.<init>((int) r5)     // Catch:{ all -> 0x006b }
            androidx.tracing.Trace.endSection()
            return r4
        L_0x006b:
            r4 = move-exception
            androidx.tracing.Trace.endSection()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.getFontSync(java.lang.String, android.content.Context, java.util.List, int):androidx.core.provider.FontRequestWorker$TypefaceResult");
    }

    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() == 0) {
            FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
            if (!(fonts == null || fonts.length == 0)) {
                int length = fonts.length;
                i = 0;
                int i2 = 0;
                while (i2 < length) {
                    int resultCode = fonts[i2].getResultCode();
                    if (resultCode == 0) {
                        i2++;
                    } else if (resultCode < 0) {
                        return -3;
                    } else {
                        return resultCode;
                    }
                }
            }
            return i;
        } else if (fontFamilyResult.getStatusCode() != 1) {
            return -3;
        } else {
            return -2;
        }
    }

    static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(int i) {
            this.mTypeface = null;
            this.mResult = i;
        }

        TypefaceResult(Typeface typeface) {
            this.mTypeface = typeface;
            this.mResult = 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isSuccess() {
            return this.mResult == 0;
        }
    }
}
