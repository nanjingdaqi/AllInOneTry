package me.ele.gradle

import groovy.io.FileType
import org.gradle.api.Project

import java.util.regex.Pattern

public class Utils {

  private static String pkg;
  private static Set<String> imports;
  private static String classDef1;
  private static String override;
  private static String methodDef1;
  private static String newMap;
  private static Set<String> entryInsert;
  private static String ret;
  private static String methodDef2;
  private static String classDef2;

  public static File findAppDBFile(Project project) {
    File dbFile;
    File aptDir = new File(project.buildDir, 'generated/source/apt')
    if (!aptDir.exists()) {
      return null
    }
    aptDir.eachFileRecurse(FileType.FILES) { File f ->
      if (f.name.equals('EWebView$$Database.java')) {
        dbFile = f
        return
      }
    }
    return dbFile
  }

  public static List<File> findAarDBFiles(Project project) {
    List<File> libDBFiles = new LinkedList<>()
    File aarDir = new File(project.buildDir, 'intermediates/exploded-aar')
    aarDir.eachFileRecurse(FileType.FILES) {File f ->
      if (f.name.equals(EWebViewPlugin.EWebViewDBTextFileName)) {
        libDBFiles.add(f)
      }
    }
    return libDBFiles
  }

  public static void updateAppDBFile(File appDBFile, List libDBFiles) {
    collectSimpleContents(appDBFile)
    collectImportContents(appDBFile, libDBFiles)
    collectEntryInsert(appDBFile, libDBFiles)

    appDBFile.delete()
    appDBFile.createNewFile()

    PrintWriter pw = new PrintWriter(appDBFile)
    pw.println(pkg)
    pw.println()
    imports.each { line -> pw.println(line)
    }
    pw.println()

    pw.println(classDef1)
    pw.println(override)
    pw.println(methodDef1)
    pw.println(newMap)

    entryInsert.each { line -> pw.println(line)
    }

    pw.println(ret)
    pw.println(methodDef2)
    pw.println(classDef2)

    pw.flush()
    pw.close()
  }

  private static void collectSimpleContents(File appDBFile) {
    Pattern p0 = Pattern.compile('''^package .*''')
    Pattern p1 = Pattern.compile('''public class EWebView\\$\\$Database implements Database.*''')
    Pattern p2 = Pattern.compile(''' +@Override *''')
    Pattern p3 = Pattern.compile(
        ''' +public Map<String, Class> getSchemeHandlerClasses\\(\\) \\{.*''')
    Pattern p4 = Pattern.compile(
        ''' +Map<String, Class> classes = new HashMap<String, Class>\\(\\);.*''')
    Pattern p5 = Pattern.compile(''' +return classes;.*''')
    Pattern p6 = Pattern.compile(''' +}''')
    Pattern p7 = Pattern.compile('''}''')
    appDBFile.eachLine { line ->
      if (p0.matcher(line).matches()) {
        pkg = line
      } else if (p1.matcher(line).matches()) {
        classDef1 = line
      } else if (p2.matcher(line).matches()) {
        override = line
      } else if (p3.matcher(line).matches()) {
        methodDef1 = line
      } else if (p4.matcher(line).matches()) {
        newMap = line
      } else if (p5.matcher(line).matches()) {
        ret = line
      } else if (p6.matcher(line).matches()) {
        methodDef2 = line
      } else if (p7.matcher(line).matches()) {
        classDef2 = line
      }
    }
  }

  private static void collectImportContents(File appDBFile, List libDBFiles) {
    imports = new LinkedHashSet<>()
    Pattern p = Pattern.compile('''^import .*''')
    appDBFile.eachLine { line ->
      if (p.matcher(line).matches()) {
        imports.add(line)
      }
    }

    libDBFiles.each { File dbFile ->
      dbFile.eachLine { line ->
        if (p.matcher(line).matches()) {
          imports.add(line)
        }
      }
    }
  }

  private static void collectEntryInsert(File appDbFile, List libDBFiles) {
    entryInsert = new LinkedHashSet<>()
    Pattern p = Pattern.compile(''' +classes.put\\(.*''')
    appDbFile.eachLine { line ->
      if (p.matcher(line).matches()) {
        entryInsert.add(line)
      }
    }

    libDBFiles.each { File dbFile ->
      dbFile.eachLine { line ->
        if (p.matcher(line).matches()) {
          entryInsert.add(line)
        }
      }
    }
  }
}
