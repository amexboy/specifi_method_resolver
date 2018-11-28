package com.amanu.classresolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.TreeSet;

public class DefaultResolver<T, R> implements Resolver<T, R> {

    Object handler;

    public DefaultResolver() {
    }

    public DefaultResolver(Object handler) {
        this.handler = handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public R resolve(T target) {

        if (null == handler) {
            throw new RuntimeException("Handler is not provided");
        }

        Class<?> handlerClass = handler.getClass();
        Class<?> targetClass = target.getClass();

        TreeSet<Method> compatibleMethods = getCompatibleMethods(handlerClass, targetClass);

        if (compatibleMethods.size() == 0) {
            throw new RuntimeException("There is no method in the handler class that is capable of handling the target");
        }

        //The most compatible method is the first method in the list
        Method chosenMethod = compatibleMethods.pollFirst();

        return invoke(target, chosenMethod);
    }

    private R invoke(T target, Method chosenMethod) {
        try {
            //noinspection unchecked
            return (R) chosenMethod.invoke(handler, target);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("The target method cannot be accessed! Check the access modifier!", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("The target method is incompatible! (May be a bug, please report it)", e);
        } catch (Exception e) {
            throw new RuntimeException("The target method threw an exception!", e);
        }
    }

    private TreeSet<Method> getCompatibleMethods(Class<?> handlerClass, Class<?> targetClass) {
        TreeSet<Method> methods = new TreeSet<>(new MethodComparator(targetClass));

        for (Method method : handlerClass.getDeclaredMethods()) {
            Handler annotation = method.getAnnotation(Handler.class);
            if (null == annotation) {
                continue;
            }

            if (method.getParameterTypes()[0].isAssignableFrom(targetClass)) {
                methods.add(method);
            }
        }

        return methods;
    }

    private class MethodComparator implements Comparator<Method> {

        private Class<?> target;

        MethodComparator(Class<?> target) {

            this.target = target;
        }

        @Override
        public int compare(Method method, Method t1) {
            return Integer.compare(
                    depth(method.getParameterTypes()[0]),
                    depth(t1.getParameterTypes()[0])
            );
        }

        private int depth(Class<?> parameterType) {
            if (target.equals(parameterType)) {
                return 0;
            } else if (parameterType.isAssignableFrom(target)) {
                int i = depthOfFrom(target, parameterType);

                return i;
            }

            return Integer.MAX_VALUE;
        }

        private int depthOfFrom(Class<?> target, Class<?> parameterType) {
            if (null != target && parameterType.isAssignableFrom(target)) {
                return 1 + depthOfFrom(target.getSuperclass(), parameterType);
            }

            return 0;
        }


    }
}
