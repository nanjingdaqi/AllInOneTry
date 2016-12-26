package org.peace

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.result.DependencyResult
import org.gradle.api.artifacts.result.ResolutionResult

public class MyPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    println('Hello Groovy')

    project.afterEvaluate {
      ResolutionResult resolutionResult = project.configurations.getByName('compile').
          getIncoming().
          getResolutionResult()

      resolutionResult.allDependencies.each { DependencyResult dr ->
        println "name: ${dr.getRequested().displayName}"
        println "from id: ${dr.getFrom().id}"
      }
    }
  }
}
