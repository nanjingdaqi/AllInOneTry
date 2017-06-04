package org.peace.allinone;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.util.Log;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

import java.util.List;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;
    DimenUtil.init(this);
    ResourceUtil.init(this);

    JobScheduler js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    JobInfo ji = new JobInfo.Builder(1, new ComponentName(this, A.class))
            .setPeriodic(10  * 60 * 1000)
            .setPersisted(true)
            .build();

    JobInfo ji2 = new JobInfo.Builder(2, new ComponentName(this, B.class))
            .setPeriodic(10  * 60 * 1000)
            .setPersisted(true)
            .build();

    js.schedule(ji);

    boolean isP = isPending();
    Log.e(A.TAG, "isP: " + isP);
    if (!isP) {
      js.schedule(ji2);
    }

    JobInfo ji3 = new JobInfo.Builder(3, new ComponentName(this, C.class))
            .setPeriodic(10  * 60 * 1000)
            .setPersisted(true)
            .build();
    js.schedule(ji3);
  }

  boolean isPending() {
    JobScheduler js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    List<JobInfo> jis = js.getAllPendingJobs();
    if (jis != null && jis.size() > 0) {
      for (JobInfo ji : jis) {
        if (ji.getId() == 2) {
          return true;
        }
      }
    }
    return false;
  }
}
