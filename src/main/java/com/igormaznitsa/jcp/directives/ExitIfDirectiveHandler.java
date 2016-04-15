/* 
 * Copyright 2014 Igor Maznitsa (http://www.igormaznitsa.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.jcp.directives;

import static com.igormaznitsa.meta.common.utils.Assertions.assertNotNull;
import javax.annotation.Nonnull;

import com.igormaznitsa.jcp.context.PreprocessingState;
import com.igormaznitsa.jcp.containers.PreprocessingFlag;
import com.igormaznitsa.jcp.context.PreprocessorContext;
import com.igormaznitsa.jcp.expression.Expression;
import com.igormaznitsa.jcp.expression.Value;
import com.igormaznitsa.jcp.expression.ValueType;

/**
 * The class implements the //#exitif directive handler
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class ExitIfDirectiveHandler extends AbstractDirectiveHandler {

  @Override
  @Nonnull
  public String getName() {
    return "exitif";
  }

  @Override
  @Nonnull
  public String getReference() {
    return "return to previous one in include stack if flag is true";
  }

  @Override
  @Nonnull
  public DirectiveArgumentType getArgumentType() {
    return DirectiveArgumentType.BOOLEAN;
  }

  @Override
  @Nonnull
  public AfterDirectiveProcessingBehaviour execute(@Nonnull final String string, @Nonnull final PreprocessorContext context) {
    final PreprocessingState state = assertNotNull(context.getPreprocessingState());
    AfterDirectiveProcessingBehaviour result = AfterDirectiveProcessingBehaviour.PROCESSED;

    // To end processing the file processing immediatly if the value is true
    final Value condition = Expression.evalExpression(string, context);
    if (condition.getType() != ValueType.BOOLEAN) {
      throw context.makeException(getFullName() + " needs boolean argument", null);
    }
    if (((Boolean) condition.getValue())) {
      state.getPreprocessingFlags().add(PreprocessingFlag.END_PROCESSING);
      result = AfterDirectiveProcessingBehaviour.READ_NEXT_LINE;
      if (context.isVerbose()){
        context.logForVerbose("Detected "+getFullName()+" with active flag");
      }
    }
    return result;
  }

}
