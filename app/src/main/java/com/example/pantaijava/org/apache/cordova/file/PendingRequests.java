package org.apache.cordova.file;

import android.util.SparseArray;
import org.apache.cordova.CallbackContext;

class PendingRequests {
    /* access modifiers changed from: private */
    public int currentReqId = 0;
    private SparseArray<Request> requests = new SparseArray<>();

    PendingRequests() {
    }

    public synchronized int createRequest(String str, int i, CallbackContext callbackContext) {
        Request request;
        request = new Request(str, i, callbackContext);
        this.requests.put(request.requestCode, request);
        return request.requestCode;
    }

    public synchronized Request getAndRemove(int i) {
        Request request;
        request = this.requests.get(i);
        this.requests.remove(i);
        return request;
    }

    public class Request {
        private int action;
        private CallbackContext callbackContext;
        private String rawArgs;
        /* access modifiers changed from: private */
        public int requestCode;

        private Request(PendingRequests pendingRequests, String str, int i, CallbackContext callbackContext2) {
            this.rawArgs = str;
            this.action = i;
            this.callbackContext = callbackContext2;
            int r2 = pendingRequests.currentReqId;
            pendingRequests.currentReqId = r2 + 1;
            this.requestCode = r2;
        }

        public int getAction() {
            return this.action;
        }

        public String getRawArgs() {
            return this.rawArgs;
        }

        public CallbackContext getCallbackContext() {
            return this.callbackContext;
        }
    }
}
