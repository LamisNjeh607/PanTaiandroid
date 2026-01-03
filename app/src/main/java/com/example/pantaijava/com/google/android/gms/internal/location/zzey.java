package com.google.android.gms.internal.location;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzey extends zzex {
    static final zzex zza = new zzey(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzey(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    public final Object get(int i) {
        zzer.zzc(i, this.zzc, "index");
        return Objects.requireNonNull(this.zzb[i]);
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzb() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int zzd() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int zzg(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }
}
