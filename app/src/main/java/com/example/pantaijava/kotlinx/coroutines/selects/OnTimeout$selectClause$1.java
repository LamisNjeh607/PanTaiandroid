package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import net.becvert.cordova.ZeroConf;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: OnTimeout.kt */
/* synthetic */ class OnTimeout$selectClause$1 extends FunctionReferenceImpl implements Function3<OnTimeout, SelectInstance<?>, Object, Unit> {
    public static final OnTimeout$selectClause$1 INSTANCE = new OnTimeout$selectClause$1();

    OnTimeout$selectClause$1() {
        super(3, OnTimeout.class, ZeroConf.ACTION_REGISTER, "register(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((OnTimeout) obj, (SelectInstance<?>) (SelectInstance) obj2, obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(OnTimeout onTimeout, SelectInstance<?> selectInstance, Object obj) {
        onTimeout.register(selectInstance, obj);
    }
}
