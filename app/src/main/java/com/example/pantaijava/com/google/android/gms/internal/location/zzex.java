package com.google.android.gms.internal.location;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzex extends zzeu implements List, RandomAccess {
    private static final zzfa zza = new zzev(zzey.zza, 0);

    zzex() {
    }

    public static zzex zzi() {
        return zzey.zza;
    }

    public static zzex zzj(Collection collection) {
        if (collection instanceof zzeu) {
            zzex zze = ((zzeu) collection).zze();
            if (!zze.zzf()) {
                return zze;
            }
            Object[] array = zze.toArray();
            return zzk(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        int i = 0;
        while (i < length) {
            if (array2[i] != null) {
                i++;
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(i).length() + 9);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return zzk(array2, length);
    }

    static zzex zzk(Object[] objArr, int i) {
        if (i == 0) {
            return zzey.zza;
        }
        return new zzey(objArr, i);
    }

    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    int i = 0;
                    while (i < size) {
                        if (zzeq.zza(get(i), list.get(i))) {
                            i++;
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzeq.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    public final zzez zza() {
        return listIterator(0);
    }

    @Deprecated
    public final zzex zze() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public int zzg(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    /* renamed from: zzh */
    public zzex subList(int i, int i2) {
        zzer.zze(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return zzey.zza;
        }
        return new zzew(this, i, i3);
    }

    /* renamed from: zzl */
    public final zzfa listIterator(int i) {
        zzer.zzd(i, size(), "index");
        if (isEmpty()) {
            return zza;
        }
        return new zzev(this, i);
    }
}
