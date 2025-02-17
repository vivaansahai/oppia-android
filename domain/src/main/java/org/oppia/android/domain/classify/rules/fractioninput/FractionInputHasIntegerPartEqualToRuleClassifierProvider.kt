package org.oppia.android.domain.classify.rules.fractioninput

import org.oppia.android.app.model.Fraction
import org.oppia.android.app.model.InteractionObject
import org.oppia.android.app.model.WrittenTranslationContext
import org.oppia.android.domain.classify.RuleClassifier
import org.oppia.android.domain.classify.rules.GenericRuleClassifier
import org.oppia.android.domain.classify.rules.RuleClassifierProvider
import javax.inject.Inject

/**
 * Provider for a classifier that determines whether a fraction has an integer part equal to the specified value per the
 * fraction input interaction.
 *
 * https://github.com/oppia/oppia/blob/37285a/extensions/interactions/FractionInput/directives/fraction-input-rules.service.ts#L48
 */
// TODO(#1580): Re-restrict access using Bazel visibilities
class FractionInputHasIntegerPartEqualToRuleClassifierProvider @Inject constructor(
  private val classifierFactory: GenericRuleClassifier.Factory
) : RuleClassifierProvider, GenericRuleClassifier.MultiTypeSingleInputMatcher<Fraction, Int> {

  override fun createRuleClassifier(): RuleClassifier {
    return classifierFactory.createMultiTypeSingleInputClassifier(
      InteractionObject.ObjectTypeCase.FRACTION,
      InteractionObject.ObjectTypeCase.NON_NEGATIVE_INT,
      "x",
      this
    )
  }

  override fun matches(
    answer: Fraction,
    input: Int,
    writtenTranslationContext: WrittenTranslationContext
  ): Boolean {
    return answer.wholeNumber == input
  }
}
