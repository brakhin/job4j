package ru.bgbrakhi.multithread.nonblocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class NonBlockingCash {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base base) {
        data.put(base.getId(), base);
    }

    public void update(final Base base) {
        data.computeIfPresent(
                base.getId(),
                (key, value) -> {
                    Base baseTemp = new Base(base.getId());
                    baseTemp.setVersion(base.getVersion() + 1);
                    if (value.getVersion() != baseTemp.getVersion() - 1) {
                        throw new OptimisticException("Throw Exception in Thread");
                    }
                    return baseTemp;
                }
        );
    }

    public void delete(Base base) {
        data.remove(base.getId(), base);
    }

    public static void main(String[] args) {
        Base base = new Base(1);
        NonBlockingCash nbc = new NonBlockingCash();
        nbc.add(base);
        nbc.update(base);
    }
}
