package tk.spop.meta;

import java.util.*;

public class ProcessorCache {

    public final Map<Class<?>, Class<?>> processorMap = new HashMap<>();

    public final Map<Class<?>, Set<Class<?>>> implementsMap = new HashMap<>();

}
