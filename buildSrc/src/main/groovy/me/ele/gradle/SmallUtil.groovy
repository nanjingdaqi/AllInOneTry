package me.ele.gradle

import com.android.build.gradle.api.ApkVariant
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.compile.JavaCompile

public class SmallUtil {

  public static void hookMainAppProject(Project project) {
    project.android.applicationVariants.all { ApkVariant variant ->
      def javac = variant.javaCompiler
      javac.doLast {
        File appDBFile = Utils.findAppDBFile(project)
        FileCollection classpath = javac.classpath.plus(project.files(javac.destinationDir))

        List<File> dbFiles = new LinkedList<>()
        project.rootProject.subprojects.each { Project sub ->
          if (sub.name.equals(project.name)) {
            return
          }

          File dbFile = Utils.findAppDBFile(sub)
          if (dbFile == null) {
            return
          }

          classpath = classpath.plus(project.files(new File(sub.buildDir, 'intermediates/classes/release')))
          dbFiles.add(dbFile)
        }

        if (dbFiles.size() == 0) {
          return
        }

        // app project may have no db file
        if (appDBFile == null) {
          appDBFile.mkdirs()
          appDBFile.createNewFile()
        }
        Utils.updateAppDBFile(appDBFile, dbFiles)

        // generate new EWebView$$Database.class
        project.task(type: JavaCompile, 'eWebViewJavaC') { JavaCompile jc ->
          jc.source appDBFile.absolutePath
          jc.destinationDir javac.destinationDir
          jc.classpath = classpath
          jc.sourceCompatibility javac.sourceCompatibility
          jc.targetCompatibility javac.targetCompatibility
        }
        project.eWebViewJavaC.execute()
      }
    }
  }
}