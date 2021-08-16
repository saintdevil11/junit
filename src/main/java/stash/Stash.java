package stash;

import io.qameta.allure.Allure;

public final class Stash {
    private static final ThreadLocal<ThreadStash> stashes = new ThreadLocal<>();

    private static ThreadStash getStash() {
        ThreadStash current = stashes.get();
        if (current == null) {
            ThreadStash stash = new ThreadStash();
            stashes.set(stash);
            return stash;
        }
        return current;
    }

    /**
     * Очищение Stash
     */

    public static void clearStash() {
        stashes.set(null);
    }

    public static void put(String key, Object value) {
        getStash().put(key, value);
        //Следующие 2 строки нужны только для того чтобы в аллюр отчетность попадали шаги из каждого стеша при потоках больше 1
        Allure.addAttachment("Запоминаем переменную" + key,
                (value != null) ? value.toString() : "null");
    }

    public static Object get(String key) {
        //Следующие 2 строки нужны только для того чтобы в аллюр отчетность попадали шаги из каждого стеша при потоках больше 1
        Allure.addAttachment("используем значение переменной" + key,
                (getOrDefault(key, null) != null) ? getOrDefault(key, null).toString() : "null");
        return getOrDefault(key, null);
    }

    public static void clear() {
        getStash().clear();
    }

    public static Object getOrDefault(String key, Object defaultValue) {
        return getStash().getOrDefault(key, defaultValue);
    }
}
