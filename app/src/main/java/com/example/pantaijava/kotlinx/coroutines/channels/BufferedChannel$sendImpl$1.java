package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function4;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
public final class BufferedChannel$sendImpl$1 implements Function4 {
    public static final BufferedChannel$sendImpl$1 INSTANCE = new BufferedChannel$sendImpl$1();

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((ChannelSegment) obj, ((Number) obj2).intValue(), obj3, ((Number) obj4).longValue());
    }

    public final Void invoke(ChannelSegment<E> channelSegment, int i, E e, long j) {
        throw new IllegalStateException("unexpected".toString());
    }
}
