package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();
    private static final LruCache<ProviderCacheKey, ProviderInfo> sProviderCache = new LruCache<>(2);

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, List<FontRequest> list, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        Trace.beginSection("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                FontRequest fontRequest = list.get(i);
                ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
                if (provider == null) {
                    FontsContractCompat.FontInfo[] fontInfoArr = null;
                    return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
                }
                arrayList.add(query(context, fontRequest, provider.authority, cancellationSignal));
            }
            FontsContractCompat.FontFamilyResult create = FontsContractCompat.FontFamilyResult.create(0, (List<FontsContractCompat.FontInfo[]>) arrayList);
            Trace.endSection();
            return create;
        } finally {
            Trace.endSection();
        }
    }

    private static class ProviderCacheKey {
        String mAuthority;
        List<List<byte[]>> mCertificates;
        String mPackageName;

        ProviderCacheKey(String str, String str2, List<List<byte[]>> list) {
            this.mAuthority = str;
            this.mPackageName = str2;
            this.mCertificates = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProviderCacheKey)) {
                return false;
            }
            ProviderCacheKey providerCacheKey = (ProviderCacheKey) obj;
            if (!Objects.equals(this.mAuthority, providerCacheKey.mAuthority) || !Objects.equals(this.mPackageName, providerCacheKey.mPackageName) || !Objects.equals(this.mCertificates, providerCacheKey.mCertificates)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.mAuthority, this.mPackageName, this.mCertificates});
        }
    }

    static void clearProviderCache() {
        sProviderCache.evictAll();
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        Trace.beginSection("FontProvider.getProvider");
        try {
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            ProviderCacheKey providerCacheKey = new ProviderCacheKey(fontRequest.getProviderAuthority(), fontRequest.getProviderPackage(), certificates);
            ProviderInfo providerInfo = sProviderCache.get(providerCacheKey);
            if (providerInfo != null) {
                return providerInfo;
            }
            String providerAuthority = fontRequest.getProviderAuthority();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
            if (resolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
            } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
                List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
                Collections.sort(convertToByteArrayList, sByteArrayComparator);
                for (int i = 0; i < certificates.size(); i++) {
                    ArrayList arrayList = new ArrayList(certificates.get(i));
                    Collections.sort(arrayList, sByteArrayComparator);
                    if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                        sProviderCache.put(providerCacheKey, resolveContentProvider);
                        Trace.endSection();
                        return resolveContentProvider;
                    }
                }
                Trace.endSection();
                return null;
            } else {
                throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
            }
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0123 A[SYNTHETIC, Splitter:B:55:0x0123] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r21, androidx.core.provider.FontRequest r22, java.lang.String r23, android.os.CancellationSignal r24) {
        /*
            r0 = r23
            java.lang.String r1 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.lang.String r7 = "content"
            java.lang.String r8 = "FontProvider.query"
            androidx.tracing.Trace.beginSection(r8)
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x012a }
            r8.<init>()     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r9 = new android.net.Uri$Builder     // Catch:{ all -> 0x012a }
            r9.<init>()     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r9 = r9.scheme(r7)     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r9 = r9.authority(r0)     // Catch:{ all -> 0x012a }
            android.net.Uri r9 = r9.build()     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r10 = new android.net.Uri$Builder     // Catch:{ all -> 0x012a }
            r10.<init>()     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r7 = r10.scheme(r7)     // Catch:{ all -> 0x012a }
            android.net.Uri$Builder r0 = r7.authority(r0)     // Catch:{ all -> 0x012a }
            java.lang.String r7 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r7)     // Catch:{ all -> 0x012a }
            android.net.Uri r0 = r0.build()     // Catch:{ all -> 0x012a }
            r7 = r21
            androidx.core.provider.FontProvider$ContentQueryWrapper r7 = androidx.core.provider.FontProvider.ContentQueryWrapper.make(r7, r9)     // Catch:{ all -> 0x012a }
            r10 = 7
            r17 = 0
            java.lang.String[] r12 = new java.lang.String[r10]     // Catch:{ all -> 0x0120 }
            r15 = 0
            r12[r15] = r6     // Catch:{ all -> 0x0120 }
            r14 = 1
            r12[r14] = r5     // Catch:{ all -> 0x0120 }
            r10 = 2
            r12[r10] = r4     // Catch:{ all -> 0x0120 }
            java.lang.String r10 = "font_variation_settings"
            r11 = 3
            r12[r11] = r10     // Catch:{ all -> 0x0120 }
            r10 = 4
            r12[r10] = r3     // Catch:{ all -> 0x0120 }
            r10 = 5
            r12[r10] = r2     // Catch:{ all -> 0x0120 }
            r10 = 6
            r12[r10] = r1     // Catch:{ all -> 0x0120 }
            java.lang.String r10 = "ContentQueryWrapper.query"
            androidx.tracing.Trace.beginSection(r10)     // Catch:{ all -> 0x0120 }
            java.lang.String r13 = "query = ?"
            java.lang.String[] r11 = new java.lang.String[r14]     // Catch:{ all -> 0x011b }
            java.lang.String r10 = r22.getQuery()     // Catch:{ all -> 0x011b }
            r11[r15] = r10     // Catch:{ all -> 0x011b }
            r16 = 0
            r10 = r7
            r18 = r11
            r11 = r9
            r19 = r8
            r8 = r14
            r14 = r18
            r15 = r16
            r16 = r24
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x011b }
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0117 }
            if (r10 == 0) goto L_0x0100
            int r11 = r10.getCount()     // Catch:{ all -> 0x0117 }
            if (r11 <= 0) goto L_0x0100
            int r1 = r10.getColumnIndex(r1)     // Catch:{ all -> 0x0117 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x0117 }
            r11.<init>()     // Catch:{ all -> 0x0117 }
            int r6 = r10.getColumnIndex(r6)     // Catch:{ all -> 0x0117 }
            int r5 = r10.getColumnIndex(r5)     // Catch:{ all -> 0x0117 }
            int r4 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x0117 }
            int r3 = r10.getColumnIndex(r3)     // Catch:{ all -> 0x0117 }
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x0117 }
        L_0x00ae:
            boolean r12 = r10.moveToNext()     // Catch:{ all -> 0x0117 }
            if (r12 == 0) goto L_0x00fe
            r12 = -1
            if (r1 == r12) goto L_0x00bc
            int r15 = r10.getInt(r1)     // Catch:{ all -> 0x0117 }
            goto L_0x00bd
        L_0x00bc:
            r15 = 0
        L_0x00bd:
            if (r4 == r12) goto L_0x00c4
            int r13 = r10.getInt(r4)     // Catch:{ all -> 0x0117 }
            goto L_0x00c5
        L_0x00c4:
            r13 = 0
        L_0x00c5:
            if (r5 != r12) goto L_0x00d1
            r14 = r13
            long r12 = r10.getLong(r6)     // Catch:{ all -> 0x0117 }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r9, r12)     // Catch:{ all -> 0x0117 }
            goto L_0x00da
        L_0x00d1:
            r14 = r13
            long r12 = r10.getLong(r5)     // Catch:{ all -> 0x0117 }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r12)     // Catch:{ all -> 0x0117 }
        L_0x00da:
            r13 = -1
            if (r3 == r13) goto L_0x00e2
            int r16 = r10.getInt(r3)     // Catch:{ all -> 0x0117 }
            goto L_0x00e4
        L_0x00e2:
            r16 = 400(0x190, float:5.6E-43)
        L_0x00e4:
            r20 = r16
            if (r2 == r13) goto L_0x00f2
            int r13 = r10.getInt(r2)     // Catch:{ all -> 0x0117 }
            if (r13 != r8) goto L_0x00f2
            r13 = r8
            r8 = r20
            goto L_0x00f5
        L_0x00f2:
            r8 = r20
            r13 = 0
        L_0x00f5:
            androidx.core.provider.FontsContractCompat$FontInfo r8 = androidx.core.provider.FontsContractCompat.FontInfo.create(r12, r14, r8, r13, r15)     // Catch:{ all -> 0x0117 }
            r11.add(r8)     // Catch:{ all -> 0x0117 }
            r8 = 1
            goto L_0x00ae
        L_0x00fe:
            r8 = r11
            goto L_0x0102
        L_0x0100:
            r8 = r19
        L_0x0102:
            if (r10 == 0) goto L_0x0107
            r10.close()     // Catch:{ all -> 0x012a }
        L_0x0107:
            r7.close()     // Catch:{ all -> 0x012a }
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]     // Catch:{ all -> 0x012a }
            java.lang.Object[] r0 = r8.toArray(r0)     // Catch:{ all -> 0x012a }
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0     // Catch:{ all -> 0x012a }
            androidx.tracing.Trace.endSection()
            return r0
        L_0x0117:
            r0 = move-exception
            r17 = r10
            goto L_0x0121
        L_0x011b:
            r0 = move-exception
            androidx.tracing.Trace.endSection()     // Catch:{ all -> 0x0120 }
            throw r0     // Catch:{ all -> 0x0120 }
        L_0x0120:
            r0 = move-exception
        L_0x0121:
            if (r17 == 0) goto L_0x0126
            r17.close()     // Catch:{ all -> 0x012a }
        L_0x0126:
            r7.close()     // Catch:{ all -> 0x012a }
            throw r0     // Catch:{ all -> 0x012a }
        L_0x012a:
            r0 = move-exception
            androidx.tracing.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return b - b2;
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    private interface ContentQueryWrapper {
        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        static ContentQueryWrapper make(Context context, Uri uri) {
            return new ContentQueryWrapperApi24Impl(context, uri);
        }
    }

    private static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }

    private static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }
    }
}
