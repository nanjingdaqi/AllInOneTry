package me.ele.gradle

import com.android.build.gradle.api.ApkVariant
import com.android.build.gradle.api.LibraryVariant
import groovy.io.FileType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

public class EWebViewPlugin implements Plugin<Project> {

  public static final String EWebViewDBTextFileName = 'EWebViewDataBase.txt'

  @Override
  void apply(Project project) {
    boolean smallApplied = project.rootProject.plugins.findPlugin('net.wequick.small') != null
    println 'small applied: ' + smallApplied
    if (project.plugins.findPlugin('com.android.application')) {
      hookAppProject(project, smallApplied)
      if (smallApplied && project.name.equals("app")) {
        SmallUtil.hookMainAppProject(project)
      }
    } else if (project.plugins.findPlugin('com.android.library')) {
      hookLibraryProject(project)
    }
  }

  private void hookAppProject(Project project, boolean smallApplied) {
    project.android.applicationVariants.all { ApkVariant variant ->
      def javac = variant.javaCompiler
      javac.doLast {
        File appDBFile = Utils.findAppDBFile(project)

        List<File> libDBFiles = Utils.findAarDBFiles(project)
        if (libDBFiles.size() == 0) {
          return
        }

        Utils.updateAppDBFile(appDBFile, libDBFiles)

        // delete produced EWebView$$Database.class
        javac.destinationDir.eachFileRecurse(FileType.FILES) { File f ->
          if (f.name.contains('EWebView$$Database')) {
            f.delete()
          }
        }

        // in small project, db information will be collected and compiled at app aR step
        if (smallApplied) {
          return;
        }

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

  private void hookLibraryProject(Project project) {
    project.afterEvaluate {
      if (!project.hasProperty('android')) {
        return
      }

      project.android.libraryVariants.all { LibraryVariant variant ->
        // lib only run release as app dependency
        if (!variant.buildType.name.equals('release')) {
          return
        }

        def javac = variant.javaCompiler
        javac.doLast {
          File dbFile = Utils.findAppDBFile(project)
          if (dbFile == null) {
            return;
          }

          // save generated db file into the generated aar
          project.bundleRelease.doFirst {
            File bundleDir = new File(project.buildDir, 'intermediates/bundles/release')
            File dbTxtFile = new File(bundleDir, EWebViewDBTextFileName)
            if (dbTxtFile.exists()) {
              dbTxtFile.delete()
            }
            dbFile.renameTo(dbTxtFile.absolutePath)
          }

          // delete library EWebView$$Database.class
          variant.javaCompiler.destinationDir.eachFileRecurse(FileType.FILES) { File f ->
            if (f.name.contains('EWebView$$Database')) {
              f.delete()
            }
          }
        }
      }

      project.compileReleaseJavaWithJavac.doLast {}
    }
  }
}
