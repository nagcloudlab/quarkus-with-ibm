package org.acme;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

@LogEvent
@Interceptor
public class LogEventInterceptor {

    @AroundInvoke
    public Object logEvent(InvocationContext invocationContext) throws Exception {
        System.out.println("LOG :: before");
        Object obj = invocationContext.proceed();
        System.out.println("LOG :: after");
        return obj;
    }

}
