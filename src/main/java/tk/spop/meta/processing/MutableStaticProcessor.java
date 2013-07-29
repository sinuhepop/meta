package tk.spop.meta.processing;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import lombok.val;

/**
 * Raises a warning for each mutable static field (not final and immutable).
 */
@SupportedAnnotationTypes("*")
public class MutableStaticProcessor extends EnhancedProcessor {

    @Override
    protected void process(Element element) {
        val fields = super.

    }

    private void processField(Element element) {
        val mods = element.getModifiers();
        if (mods.contains(Modifier.STATIC)) {
            if (!mods.contains(Modifier.FINAL)) {
                // TODO: Look wether is persistent or immutable
                warn("Mutable static fields can lead to undesired effects.", element);
            }
        }
    }

}
