package ch.rhj.junit;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

import java.util.Optional;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DisabledIfCICondition implements ExecutionCondition {

	private static final ConditionEvaluationResult ENABLED_BY_DEFAULT = //
			enabled("@DisabledIfCI is not present");

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

		Optional<DisabledIfCI> optional = findAnnotation(context.getElement(), DisabledIfCI.class);

		if (!optional.isPresent())
			return ENABLED_BY_DEFAULT;

		String userName = System.getProperty("user.name");

		if ("travis".equals(userName))
			return disabled("[user.name] is 'travis'");

		return enabled("[user.name] is not 'travis'");
	}
}
