package javax.jmdns.impl;

import java.net.InetAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface NameRegister {

    public enum NameType {
        HOST,
        SERVICE
    }

    boolean checkName(InetAddress inetAddress, String str, NameType nameType);

    String incrementName(InetAddress inetAddress, String str, NameType nameType);

    void register(InetAddress inetAddress, String str, NameType nameType);

    public static abstract class BaseRegister implements NameRegister {
        /* access modifiers changed from: protected */
        public String incrementNameWithDash(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int indexOf = str.indexOf(".local.");
            int lastIndexOf = str.lastIndexOf(45);
            int i = 1;
            if (lastIndexOf < 0) {
                sb.append(str.substring(0, indexOf));
            } else {
                try {
                    int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1, indexOf)) + 1;
                    sb.append(str.substring(0, lastIndexOf));
                    i = parseInt;
                } catch (Exception unused) {
                    sb.append(str.substring(0, indexOf));
                }
            }
            sb.append('-');
            sb.append(i);
            sb.append(".local.");
            return sb.toString();
        }

        /* access modifiers changed from: protected */
        public String incrementNameWithParentesis(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int lastIndexOf = str.lastIndexOf(40);
            int lastIndexOf2 = str.lastIndexOf(41);
            if (lastIndexOf < 0 || lastIndexOf >= lastIndexOf2) {
                sb.append(str);
                sb.append(" (2)");
            } else {
                try {
                    sb.append(str.substring(0, lastIndexOf));
                    sb.append('(');
                    sb.append(Integer.parseInt(str.substring(lastIndexOf + 1, lastIndexOf2)) + 1);
                    sb.append(')');
                } catch (NumberFormatException unused) {
                    sb.setLength(0);
                    sb.append(str);
                    sb.append(" (2)");
                }
            }
            return sb.toString();
        }
    }

    /* renamed from: javax.jmdns.impl.NameRegister$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$NameRegister$NameType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                javax.jmdns.impl.NameRegister$NameType[] r0 = javax.jmdns.impl.NameRegister.NameType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$jmdns$impl$NameRegister$NameType = r0
                javax.jmdns.impl.NameRegister$NameType r1 = javax.jmdns.impl.NameRegister.NameType.HOST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$jmdns$impl$NameRegister$NameType     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.jmdns.impl.NameRegister$NameType r1 = javax.jmdns.impl.NameRegister.NameType.SERVICE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.NameRegister.AnonymousClass1.<clinit>():void");
        }
    }

    public static class UniqueNamePerInterface extends BaseRegister {
        private final ConcurrentMap<InetAddress, String> _hostNames = new ConcurrentHashMap();
        private final ConcurrentMap<InetAddress, Set<String>> _serviceNames = new ConcurrentHashMap();

        public void register(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
        }

        public boolean checkName(InetAddress inetAddress, String str, NameType nameType) {
            Set set;
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i == 1) {
                String str2 = (String) this._hostNames.get(inetAddress);
                if (str2 == null || !str2.equals(str)) {
                    return false;
                }
                return true;
            } else if (i == 2 && (set = (Set) this._serviceNames.get(inetAddress)) != null && set.contains(str)) {
                return true;
            } else {
                return false;
            }
        }

        public String incrementName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i == 1) {
                return incrementNameWithDash(str);
            }
            if (i != 2) {
                return str;
            }
            return incrementNameWithParentesis(str);
        }
    }

    public static class UniqueNameAcrossInterface extends BaseRegister {
        public void register(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
        }

        public boolean checkName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            return false;
        }

        public String incrementName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i == 1) {
                return incrementNameWithDash(str);
            }
            if (i != 2) {
                return str;
            }
            return incrementNameWithParentesis(str);
        }
    }

    public static class Factory {
        private static volatile NameRegister _register;

        public static void setRegistry(NameRegister nameRegister) throws IllegalStateException {
            if (_register != null) {
                throw new IllegalStateException("The register can only be set once.");
            } else if (nameRegister != null) {
                _register = nameRegister;
            }
        }

        public static NameRegister getRegistry() {
            if (_register == null) {
                _register = new UniqueNamePerInterface();
            }
            return _register;
        }
    }
}
