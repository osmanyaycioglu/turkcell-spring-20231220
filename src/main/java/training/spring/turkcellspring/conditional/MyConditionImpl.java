package training.spring.turkcellspring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyConditionImpl implements Condition {

    @Override
    public boolean matches(final ConditionContext context,
                           final AnnotatedTypeMetadata metadata) {
        MergedAnnotations             annotationsLoc                 = metadata.getAnnotations();
        MergedAnnotation<MyCondition> myConditionMergedAnnotationLoc = annotationsLoc.get(MyCondition.class);

        int valueLoc = myConditionMergedAnnotationLoc.getInt("value");

        String propertyLoc = System.getProperty("bean.limit");
        int    limit       = 9;
        if (propertyLoc != null) {
            limit = Integer.parseInt(propertyLoc);
        }

        if (valueLoc > limit) {
            return true;
        }
        return false;
    }

}
