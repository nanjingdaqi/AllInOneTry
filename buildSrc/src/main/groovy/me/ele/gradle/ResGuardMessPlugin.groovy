package me.ele.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

public class ResGuardMessPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    project.afterEvaluate {
      println(project.andResGuard.whiteList.toString())
      project.andResGuard.whiteList.add('R.layout.activity_main')
      println(project.andResGuard.whiteList.toString())
    }
  }
}
