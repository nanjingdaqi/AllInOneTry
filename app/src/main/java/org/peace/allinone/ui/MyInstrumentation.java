package org.peace.allinone.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import me.ele.commons.AppLogger;

public class MyInstrumentation extends Instrumentation implements InvocationHandler {

  Activity oldObj;

  @Override public Activity newActivity(ClassLoader cl, String className, Intent intent)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    Activity obj = super.newActivity(cl, className, intent);

    if (obj.getClass() == MainActivity.class) {
      oldObj = obj;
      List<Class<?>> infs = Utils.getAllInterfaces(obj.getClass());
      MyProxy.newProxyInstance(obj.getClass().getClassLoader(),
          infs.toArray(new Class[infs.size()]), this);
    }

    return obj;
  }

  @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    AppLogger.e("invoking: " + method.getName());
    if (method.getName().equals("attach")) {
      Object ret = orgInvoke(method, args);

      Field field = Utils.getDeclaredField(oldObj.getClass(), "mWindow");
      List<Class<?>> infs = Utils.getAllInterfaces(field.getType());
      MyProxy.newProxyInstance(oldObj.getClass().getClass().getClassLoader(),
          infs.toArray(new Class[infs.size()]), this);

      return ret;
    }
    return orgInvoke(method, args);
  }

  public Object orgInvoke(Method method, Object[] args) throws Throwable {
    return method.invoke(oldObj, args);
  }
}
