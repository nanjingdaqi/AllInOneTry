package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.haha.perflib.ClassObj;
import com.squareup.haha.perflib.HprofParser;
import com.squareup.haha.perflib.Instance;
import com.squareup.haha.perflib.Snapshot;
import com.squareup.haha.perflib.io.HprofBuffer;
import com.squareup.haha.perflib.io.MemoryMappedFileBuffer;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.HeapAnalyzer;
import com.squareup.leakcanary.LeakTrace;
import com.squareup.leakcanary.internal.HeapAnalyzerService;
import org.peace.allinone.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  static String T = "peace";

  @OnClick(R.id.start_btn) public void onClick(View v) {
      try {
          File file = new File("/sdcard/test.hprof");
          HprofBuffer buffer = new MemoryMappedFileBuffer(file);
          HprofParser parser = new HprofParser(buffer);
          Snapshot snapshot = parser.parse();
//          ClassObj classObj = snapshot.findClass("android.app.Activity");
//          List<ClassObj> classes = snapshot.findAllDescendantClasses("android.app.Activity");
//          classObj.getInstanceSize();

          ClassObj classObj = snapshot.findClass("com.android.settings.SubSettings");
          List<Instance> instances = classObj.getInstancesList();

          Class cls = Class.forName("com.squareup.leakcanary.ShortestPathFinder");
          Constructor ctr = cls.getDeclaredConstructor(ExcludedRefs.class);
          ctr.setAccessible(true);
          Object obj = ctr.newInstance(AndroidExcludedRefs.createAppDefaults().build());
          Method findPathMethod = obj.getClass().getDeclaredMethod("findPath", Snapshot.class, Instance.class);
          findPathMethod.setAccessible(true);
          Object result = findPathMethod.invoke(obj, snapshot, instances.get(0));

          Class leakNodeCls = Class.forName("com.squareup.leakcanary.LeakNode");
          Method buildLeakTraceMethod = HeapAnalyzer.class.getDeclaredMethod("buildLeakTrace", leakNodeCls);
          buildLeakTraceMethod.setAccessible(true);
          Field resultNode = result.getClass().getDeclaredField("leakingNode");
          resultNode.setAccessible(true);
          LeakTrace trace = (LeakTrace) buildLeakTraceMethod.invoke(new HeapAnalyzer(AndroidExcludedRefs.createAndroidDefaults().build()), resultNode.get(result));

          classObj = snapshot.findClass("android.app.Activity");
          instances = classObj.getInstancesList();

          snapshot.getReachableInstances();
      } catch (Exception e) {
          e.printStackTrace();
          Log.e(T, "foo", e);
      }
  }
}
