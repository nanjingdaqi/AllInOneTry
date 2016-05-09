package me.ele.gradle

import com.android.build.gradle.api.ApkVariant
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

public class SmallUtil {

  public static void hookMainAppProject(Project project) {
    project.android.applicationVariants.all { ApkVariant variant ->
      def javac = variant.javaCompiler
      javac.doLast {
        File appDBFile = Utils.findAppDBFile(project)

        List<File> dbFiles = new LinkedList<>()
        project.rootProject.subprojects.each { Project sub ->
          if (sub.name.equals(project.name)) {
            return
          }

          File dbFile = Utils.findAppDBFile(sub)
          if (dbFile == null) {
            return
          }
          dbFiles.add(dbFile)
        }

        Utils.updateAppDBFile(appDBFile, dbFiles)


        // generate new EWebView$$Database.class
        project.task(type: JavaCompile, 'eWebViewJavaC') { JavaCompile jc ->
          jc.source appDBFile.absolutePath
          jc.destinationDir javac.destinationDir
          jc.classpath = javac.classpath.plus(project.files(javac.destinationDir))
          jc.sourceCompatibility javac.sourceCompatibility
          jc.targetCompatibility javac.targetCompatibility
        }
        project.eWebViewJavaC.execute()
      }
    }
  }
}