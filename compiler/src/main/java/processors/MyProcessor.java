package processors;

import annotations.MyAnnotation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
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
    int i = 0;
    for (Element element : sat) {
      out("target element: " + element);
      out("type: " + element.asType());
      out("kind: " + element.getKind());
      MyAnnotation annotation = element.getAnnotation(MyAnnotation.class);
      out("annotation value: " + annotation.value());
      String value = annotation.value();
      FieldSpec fieldSpec =
          FieldSpec.builder(String.class, "first_apt_field" + i, Modifier.PUBLIC)
              .initializer("$S", value)
              .build();
      TypeSpec.Builder builder = TypeSpec.classBuilder("MyAPT_RESULT" + i)
          .addModifiers(Modifier.PUBLIC)
          .addField(fieldSpec);
      TypeSpec typeSpec = builder.build();
      try {
        JavaFile.builder("peaceapt", typeSpec).build().writeTo(processingEnv.getFiler());
        ++i;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  void out(Object obj) {
    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, obj.toString());
  }
}
