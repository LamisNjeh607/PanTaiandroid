package javax.jmdns.impl.constants;

public enum DNSState {
    PROBING_1("probing 1", StateClass.probing),
    PROBING_2("probing 2", StateClass.probing),
    PROBING_3("probing 3", StateClass.probing),
    ANNOUNCING_1("announcing 1", StateClass.announcing),
    ANNOUNCING_2("announcing 2", StateClass.announcing),
    ANNOUNCED("announced", StateClass.announced),
    CANCELING_1("canceling 1", StateClass.canceling),
    CANCELING_2("canceling 2", StateClass.canceling),
    CANCELING_3("canceling 3", StateClass.canceling),
    CANCELED("canceled", StateClass.canceled),
    CLOSING("closing", StateClass.closing),
    CLOSED("closed", StateClass.closed);
    
    private final String _name;
    private final StateClass _state;

    private enum StateClass {
        probing,
        announcing,
        announced,
        canceling,
        canceled,
        closing,
        closed
    }

    private DNSState(String str, StateClass stateClass) {
        this._name = str;
        this._state = stateClass;
    }

    public final String toString() {
        return this._name;
    }

    /* renamed from: javax.jmdns.impl.constants.DNSState$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSState = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                javax.jmdns.impl.constants.DNSState[] r0 = javax.jmdns.impl.constants.DNSState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$constants$DNSState = r0
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.PROBING_1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.PROBING_2     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.PROBING_3     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.ANNOUNCING_1     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.ANNOUNCING_2     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0049 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.ANNOUNCED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0054 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CANCELING_1     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0060 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CANCELING_2     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x006c }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CANCELING_3     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0078 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CANCELED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0084 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CLOSING     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$javax$jmdns$impl$constants$DNSState     // Catch:{ NoSuchFieldError -> 0x0090 }
                javax.jmdns.impl.constants.DNSState r1 = javax.jmdns.impl.constants.DNSState.CLOSED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.constants.DNSState.AnonymousClass1.<clinit>():void");
        }
    }

    public final DNSState advance() {
        switch (AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSState[ordinal()]) {
            case 1:
                return PROBING_2;
            case 2:
                return PROBING_3;
            case 3:
                return ANNOUNCING_1;
            case 4:
                return ANNOUNCING_2;
            case 5:
                return ANNOUNCED;
            case 6:
                return ANNOUNCED;
            case 7:
                return CANCELING_2;
            case 8:
                return CANCELING_3;
            case 9:
                return CANCELED;
            case 10:
                return CANCELED;
            case 11:
                return CLOSED;
            case 12:
                return CLOSED;
            default:
                return this;
        }
    }

    public final DNSState revert() {
        switch (AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSState[ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return PROBING_1;
            case 7:
            case 8:
            case 9:
                return CANCELING_1;
            case 10:
                return CANCELED;
            case 11:
                return CLOSING;
            case 12:
                return CLOSED;
            default:
                return this;
        }
    }

    public final boolean isProbing() {
        return this._state == StateClass.probing;
    }

    public final boolean isAnnouncing() {
        return this._state == StateClass.announcing;
    }

    public final boolean isAnnounced() {
        return this._state == StateClass.announced;
    }

    public final boolean isCanceling() {
        return this._state == StateClass.canceling;
    }

    public final boolean isCanceled() {
        return this._state == StateClass.canceled;
    }

    public final boolean isClosing() {
        return this._state == StateClass.closing;
    }

    public final boolean isClosed() {
        return this._state == StateClass.closed;
    }
}
