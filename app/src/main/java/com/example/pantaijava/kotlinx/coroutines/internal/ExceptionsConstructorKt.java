package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a!\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00042\u0006\u0010\t\u001a\u0002H\bH\u0000¢\u0006\u0002\u0010\n\u001a7\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\u0002`\f\"\b\b\u0000\u0010\b*\u00020\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\b0\u000eH\u0002¢\u0006\u0002\u0010\u000f\u001a3\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\u0002`\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0002¢\u0006\u0002\u0010\u0012\u001a\u0018\u0010\u0013\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0014\u001a\u00020\u0001H\u0002\u001a\u001b\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0001H\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0002\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¨\u0006\u0017"}, d2 = {"throwableFields", "", "Ctor", "Lkotlin/Function1;", "", "ctorCache", "Lkotlinx/coroutines/internal/CtorCache;", "tryCopyException", "E", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "createConstructor", "Lkotlinx/coroutines/internal/Ctor;", "clz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lkotlin/jvm/functions/Function1;", "safeCtor", "block", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "fieldsCountOrDefault", "defaultValue", "fieldsCount", "accumulator", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: ExceptionsConstructor.kt */
public final class ExceptionsConstructorKt {
    private static final CtorCache ctorCache;
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);

    static {
        CtorCache ctorCache2;
        try {
            if (FastServiceLoaderKt.getANDROID_DETECTED()) {
                ctorCache2 = WeakMapCtorCache.INSTANCE;
            } else {
                ctorCache2 = ClassValueCtorCache.INSTANCE;
            }
        } catch (Throwable unused) {
            ctorCache2 = WeakMapCtorCache.INSTANCE;
        }
        ctorCache = ctorCache2;
    }

    public static final <E extends Throwable> E tryCopyException(E e) {
        E e2;
        if (!(e instanceof CopyableThrowable)) {
            return (Throwable) ctorCache.get(e.getClass()).invoke(e);
        }
        try {
            Result.Companion companion = Result.Companion;
            e2 = Result.m190constructorimpl(((CopyableThrowable) e).createCopy());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            e2 = Result.m190constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m196isFailureimpl(e2)) {
            e2 = null;
        }
        return (Throwable) e2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0106, code lost:
        r12 = (kotlin.jvm.functions.Function1) r5.getFirst();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E extends java.lang.Throwable> kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable> createConstructor(java.lang.Class<E> r12) {
        /*
            kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1 r0 = kotlinx.coroutines.internal.ExceptionsConstructorKt$createConstructor$nullResult$1.INSTANCE
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            int r1 = throwableFields
            r2 = 0
            int r3 = fieldsCountOrDefault(r12, r2)
            if (r1 == r3) goto L_0x000e
            return r0
        L_0x000e:
            java.lang.reflect.Constructor[] r12 = r12.getConstructors()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r3 = r12.length
            r1.<init>(r3)
            java.util.Collection r1 = (java.util.Collection) r1
            int r3 = r12.length
            r4 = r2
        L_0x001c:
            r5 = 0
            if (r4 >= r3) goto L_0x00c0
            r6 = r12[r4]
            java.lang.Class[] r7 = r6.getParameterTypes()
            int r8 = r7.length
            if (r8 == 0) goto L_0x00a8
            r9 = 2
            r10 = -1
            r11 = 1
            if (r8 == r11) goto L_0x0069
            if (r8 == r9) goto L_0x0039
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x0039:
            r8 = r7[r2]
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 == 0) goto L_0x0060
            r7 = r7[r11]
            java.lang.Class<java.lang.Throwable> r8 = java.lang.Throwable.class
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x0060
            kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda1 r5 = new kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda1
            r5.<init>(r6)
            kotlin.jvm.functions.Function1 r5 = safeCtor(r5)
            r6 = 3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x0060:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x0069:
            r7 = r7[r2]
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0085
            kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda2 r5 = new kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda2
            r5.<init>(r6)
            kotlin.jvm.functions.Function1 r5 = safeCtor(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r9)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x0085:
            java.lang.Class<java.lang.Throwable> r8 = java.lang.Throwable.class
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x009f
            kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda3 r5 = new kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda3
            r5.<init>(r6)
            kotlin.jvm.functions.Function1 r5 = safeCtor(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r11)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x009f:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            goto L_0x00b9
        L_0x00a8:
            kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda4 r5 = new kotlinx.coroutines.internal.ExceptionsConstructorKt$$ExternalSyntheticLambda4
            r5.<init>(r6)
            kotlin.jvm.functions.Function1 r5 = safeCtor(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
        L_0x00b9:
            r1.add(r5)
            int r4 = r4 + 1
            goto L_0x001c
        L_0x00c0:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r12 = r1.iterator()
            boolean r1 = r12.hasNext()
            if (r1 != 0) goto L_0x00cf
            goto L_0x0102
        L_0x00cf:
            java.lang.Object r5 = r12.next()
            boolean r1 = r12.hasNext()
            if (r1 != 0) goto L_0x00da
            goto L_0x0102
        L_0x00da:
            r1 = r5
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r1.getSecond()
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
        L_0x00e7:
            java.lang.Object r2 = r12.next()
            r3 = r2
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r3 = r3.getSecond()
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            if (r1 >= r3) goto L_0x00fc
            r5 = r2
            r1 = r3
        L_0x00fc:
            boolean r2 = r12.hasNext()
            if (r2 != 0) goto L_0x00e7
        L_0x0102:
            kotlin.Pair r5 = (kotlin.Pair) r5
            if (r5 == 0) goto L_0x010f
            java.lang.Object r12 = r5.getFirst()
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            if (r12 == 0) goto L_0x010f
            r0 = r12
        L_0x010f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor(java.lang.Class):kotlin.jvm.functions.Function1");
    }

    /* access modifiers changed from: private */
    public static final Throwable createConstructor$lambda$7$lambda$1(Constructor constructor, Throwable th) {
        Object newInstance = constructor.newInstance(new Object[]{th.getMessage(), th});
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        return (Throwable) newInstance;
    }

    /* access modifiers changed from: private */
    public static final Throwable createConstructor$lambda$7$lambda$3(Constructor constructor, Throwable th) {
        Object newInstance = constructor.newInstance(new Object[]{th.getMessage()});
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        Throwable th2 = (Throwable) newInstance;
        th2.initCause(th);
        return th2;
    }

    /* access modifiers changed from: private */
    public static final Throwable createConstructor$lambda$7$lambda$4(Constructor constructor, Throwable th) {
        Object newInstance = constructor.newInstance(new Object[]{th});
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        return (Throwable) newInstance;
    }

    /* access modifiers changed from: private */
    public static final Throwable createConstructor$lambda$7$lambda$6(Constructor constructor, Throwable th) {
        Object newInstance = constructor.newInstance(new Object[0]);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Throwable");
        Throwable th2 = (Throwable) newInstance;
        th2.initCause(th);
        return th2;
    }

    private static final Function1<Throwable, Throwable> safeCtor(Function1<? super Throwable, ? extends Throwable> function1) {
        return new ExceptionsConstructorKt$$ExternalSyntheticLambda0(function1);
    }

    /* access modifiers changed from: private */
    public static final Throwable safeCtor$lambda$9(Function1 function1, Throwable th) {
        Object obj;
        Throwable th2 = null;
        try {
            Result.Companion companion = Result.Companion;
            Throwable th3 = (Throwable) function1.invoke(th);
            if (!Intrinsics.areEqual((Object) th.getMessage(), (Object) th3.getMessage()) && !Intrinsics.areEqual((Object) th3.getMessage(), (Object) th.toString())) {
                th3 = null;
            }
            obj = Result.m190constructorimpl(th3);
        } catch (Throwable th4) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m190constructorimpl(ResultKt.createFailure(th4));
        }
        if (!Result.m196isFailureimpl(obj)) {
            th2 = obj;
        }
        return th2;
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int i) {
        Integer num;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.Companion;
            num = Result.m190constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, (Object) null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            num = Result.m190constructorimpl(ResultKt.createFailure(th));
        }
        Integer valueOf = Integer.valueOf(i);
        if (Result.m196isFailureimpl(num)) {
            num = valueOf;
        }
        return ((Number) num).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class<?> cls, int i) {
        Class<? super Object> superclass;
        do {
            Field[] declaredFields = r5.getDeclaredFields();
            int length = declaredFields.length;
            int i2 = 0;
            Class<? super Object> cls2 = cls;
            for (int i3 = 0; i3 < length; i3++) {
                if (!Modifier.isStatic(declaredFields[i3].getModifiers())) {
                    i2++;
                }
            }
            i += i2;
            superclass = cls2.getSuperclass();
            cls2 = superclass;
        } while (superclass != null);
        return i;
    }
}
