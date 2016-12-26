package org.peace

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.result.DependencyResult
import org.gradle.api.artifacts.result.ResolutionResult
import org.gradle.api.artifacts.result.ResolvedDependencyResult

public class MyPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    println('Hello Groovy')

    project.afterEvaluate {
      ResolutionResult resolutionResult = project.configurations.getByName('compile').
          getIncoming().
          getResolutionResult()

      //      resolutionResult.allDependencies.each { DependencyResult dr ->
      //        println "name: ${dr.getRequested().displayName}"
      //        println "from id: ${dr.getFrom().id}"
      //      }
      //
      //      println '----------------'

      ((ResolvedDependencyResult) resolutionResult.allDependencies.find {
        DependencyResult dr -> return dr.getRequested().displayName.contains('retrofit')
      }).getSelected().
          getDependencies().
          each { DependencyResult dr2 -> println(dr2.requested.displayName)
          }

      printSep()

      ((ResolvedDependencyResult) resolutionResult.allDependencies.find {
        DependencyResult dr -> return dr.getRequested().displayName.contains('pay-warlock')
      }).getSelected().
          getDependents().
          each { ResolvedDependencyResult rdr -> println(rdr.requested.displayName)
          }

      printSep()

      getAllDependencies(resolutionResult.allDependencies.find {
        DependencyResult dr -> return dr.getRequested().displayName.contains('pay-core')
      }).each { DependencyResult dr -> println(dr.requested.displayName)
      }
    }
  }

  private Set<DependencyResult> getAllDependencies(DependencyResult dr) {
    Set<DependencyResult> results = new HashSet<>()
    Set<DependencyResult> tmpResults = ((ResolvedDependencyResult) dr).getSelected().
        getDependencies()
    if (tmpResults != null && tmpResults.size() > 0) {
      results.addAll(tmpResults)
      tmpResults.each { DependencyResult dr2 -> results.addAll(getAllDependencies(dr2))
      }
    }
    return results
  }

  private void printSep() {
    println '---------------------------------'
  }
}
