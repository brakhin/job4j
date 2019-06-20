package ru.bgbrakhi.multithread.nonblocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class NonBlockingCash {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base base) {
        data.put(base.id, base);
    }

    public void update(final Base base) {
        base.version++;
        data.computeIfPresent(
                base.id,
                (key, value) -> {
                    if (value.version != base.version) {
                        throw new OptimisticException("Throw Exception in Thread");
                    }
                    return base;
                }
        );
    }

    public void delete(Base base) {
        data.remove(base.id, base);
    }
}
