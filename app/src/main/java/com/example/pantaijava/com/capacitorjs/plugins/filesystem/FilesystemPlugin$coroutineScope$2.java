package com.example.pantaijava.com.capacitorjs.plugins.filesystem;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/CoroutineScope;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilesystemPlugin.kt */
final class FilesystemPlugin$coroutineScope$2 extends Lambda implements Function0<CoroutineScope> {
    public static final FilesystemPlugin$coroutineScope$2 INSTANCE = new FilesystemPlugin$coroutineScope$2();

    FilesystemPlugin$coroutineScope$2() {
        super(0);
    }

    public final CoroutineScope invoke() {
        return CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
    }
}
