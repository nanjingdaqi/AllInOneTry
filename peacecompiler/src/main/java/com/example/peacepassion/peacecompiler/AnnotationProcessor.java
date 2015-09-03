package com.example.peacepassion.peacecompiler;

import com.google.auto.service.AutoService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by peacepassion on 15/9/3.
 */
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public void init(ProcessingEnvironment processingEnvironment) {

    }

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        return false;
    }


}
