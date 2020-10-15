package com.example.bookstore;

import androidx.annotation.NonNull;

import com.example.bookstore.Repository.Repository;
import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {

    @NonNull
    private static List<Request> getFetchRequests() {
        final List<Request> requests = new ArrayList<>();
        for (String url : Repository.getItemsUrlArray()) {
            final Request request = new Request(url, DirectoryBuilder.getFilePath(url));
            request.setAutoRetryMaxAttempts(10);
            request.setPriority(Priority.HIGH);
            requests.add(request);
        }
        return requests;
    }

    @NonNull
    public static List<Request> getFetchRequestWithGroupId(final int groupId) {
        final List<Request> requests = getFetchRequests();
        for (Request request : requests) { ;
            request.setGroupId(groupId);
        }
        return requests;
    }
}
