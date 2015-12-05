package processors;

import annotations.MyAnnotation;
import com.google.auto.service.AutoService;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by peacepassion on 15/12/5.
 */
@AutoService(Processor.class) public class MyProcessor extends AbstractProcessor {
  ProcessingEnvironment processingEnv;

  @Override public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.processingEnv = processingEnv;
    out("start to do APT");
  }

  @Override public Set<String> getSupportedAnnotationTypes() {
    Set<String> sat = new HashSet<String>();
    sat.add(MyAnnotation.class.getCanonicalName());
    return sat;
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    parseEnv(roundEnv);
    return false;
  }

  private void parseEnv(RoundEnvironment roundEnv) {
    Set<? extends Element> sat = roundEnv.getElementsAnnotatedWith(MyAnnotation.class);
    for (Element element : sat) {
      out("target element: " + element);
      out("type: " + element.asType());
      out("kind: " + element.getKind());
      MyAnnotation annotation = element.getAnnotation(MyAnnotation.class);
      out("annotation value: " + annotation.value());
    }
  }

  void out(Object obj) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, obj.toString());
  }
}
