package javax.jmdns.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNSCache extends ConcurrentHashMap<String, List<DNSEntry>> {
    private static Logger logger = LoggerFactory.getLogger(DNSCache.class.getName());
    private static final long serialVersionUID = 3024739453186759259L;

    public DNSCache() {
        this(1024);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DNSCache(DNSCache dNSCache) {
        this(dNSCache != null ? dNSCache.size() : 1024);
        if (dNSCache != null) {
            putAll(dNSCache);
        }
    }

    public DNSCache(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        return new DNSCache(this);
    }

    public Collection<DNSEntry> allValues() {
        ArrayList arrayList = new ArrayList();
        for (List list : values()) {
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public Collection<? extends DNSEntry> getDNSEntryList(String str) {
        ArrayList arrayList;
        Collection<? extends DNSEntry> _getDNSEntryList = _getDNSEntryList(str);
        if (_getDNSEntryList == null) {
            return Collections.emptyList();
        }
        synchronized (_getDNSEntryList) {
            arrayList = new ArrayList(_getDNSEntryList);
        }
        return arrayList;
    }

    private Collection<? extends DNSEntry> _getDNSEntryList(String str) {
        return (Collection) get(str != null ? str.toLowerCase() : null);
    }

    public DNSEntry getDNSEntry(DNSEntry dNSEntry) {
        Collection<? extends DNSEntry> _getDNSEntryList;
        DNSEntry dNSEntry2 = null;
        if (!(dNSEntry == null || (_getDNSEntryList = _getDNSEntryList(dNSEntry.getKey())) == null)) {
            synchronized (_getDNSEntryList) {
                Iterator<? extends DNSEntry> it = _getDNSEntryList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DNSEntry dNSEntry3 = (DNSEntry) it.next();
                    if (dNSEntry3.isSameEntry(dNSEntry)) {
                        dNSEntry2 = dNSEntry3;
                        break;
                    }
                }
            }
        }
        return dNSEntry2;
    }

    public DNSEntry getDNSEntry(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        Collection<? extends DNSEntry> _getDNSEntryList = _getDNSEntryList(str);
        DNSEntry dNSEntry = null;
        if (_getDNSEntryList != null) {
            synchronized (_getDNSEntryList) {
                Iterator<? extends DNSEntry> it = _getDNSEntryList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DNSEntry dNSEntry2 = (DNSEntry) it.next();
                    if (dNSEntry2.matchRecordType(dNSRecordType) && dNSEntry2.matchRecordClass(dNSRecordClass)) {
                        dNSEntry = dNSEntry2;
                        break;
                    }
                }
            }
        }
        return dNSEntry;
    }

    public Collection<? extends DNSEntry> getDNSEntryList(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        ArrayList arrayList;
        Collection<? extends DNSEntry> _getDNSEntryList = _getDNSEntryList(str);
        if (_getDNSEntryList == null) {
            return Collections.emptyList();
        }
        synchronized (_getDNSEntryList) {
            arrayList = new ArrayList(_getDNSEntryList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                DNSEntry dNSEntry = (DNSEntry) it.next();
                if (!dNSEntry.matchRecordType(dNSRecordType) || !dNSEntry.matchRecordClass(dNSRecordClass)) {
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public boolean addDNSEntry(DNSEntry dNSEntry) {
        if (dNSEntry == null) {
            return false;
        }
        List list = (List) get(dNSEntry.getKey());
        if (list == null) {
            putIfAbsent(dNSEntry.getKey(), new ArrayList());
            list = (List) get(dNSEntry.getKey());
        }
        synchronized (list) {
            list.add(dNSEntry);
        }
        return true;
    }

    public boolean removeDNSEntry(DNSEntry dNSEntry) {
        List list;
        if (dNSEntry == null || (list = (List) get(dNSEntry.getKey())) == null) {
            return false;
        }
        synchronized (list) {
            list.remove(dNSEntry);
        }
        return false;
    }

    public boolean replaceDNSEntry(DNSEntry dNSEntry, DNSEntry dNSEntry2) {
        if (dNSEntry == null || dNSEntry2 == null || !dNSEntry.getKey().equals(dNSEntry2.getKey())) {
            return false;
        }
        List list = (List) get(dNSEntry.getKey());
        if (list == null) {
            putIfAbsent(dNSEntry.getKey(), new ArrayList());
            list = (List) get(dNSEntry.getKey());
        }
        synchronized (list) {
            list.remove(dNSEntry2);
            list.add(dNSEntry);
        }
        return true;
    }

    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder(2000);
        sb.append("\n\t---- cache ----");
        for (Map.Entry entry : entrySet()) {
            sb.append("\n\n\t\tname '").append((String) entry.getKey()).append('\'');
            List<DNSEntry> list = (List) entry.getValue();
            if (list == null || list.isEmpty()) {
                sb.append(" : no entries");
            } else {
                synchronized (list) {
                    for (DNSEntry dNSEntry : list) {
                        sb.append("\n\t\t\t").append(dNSEntry.toString());
                    }
                }
            }
        }
        return sb.toString();
    }

    public void logCachedContent() {
        if (logger.isTraceEnabled()) {
            logger.trace("Cached DNSEntries: {}", (Object) toString());
        }
    }
}
