/*
 * Copyright 2011 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of version 3 of the GNU Lesser General Public
 * License as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307  USA
 */
package com.igormaznitsa.jcpreprocessor.expression.operators;

import com.igormaznitsa.jcpreprocessor.expression.ExpressionItemPriority;
import com.igormaznitsa.jcpreprocessor.expression.Value;

/**
 * The class implements the OR operator handler
 * 
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public final class OperatorOR extends AbstractOperator {

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public String getReference() {
        return "It makes OR operation over two operands, bitwise OR over numeric operands and logical OR over boolean ones";
    }

    @Override
    public String getKeyword() {
        return "||";
    }

    public Value executeIntInt(final Value arg1, final Value arg2) {
        return Value.valueOf(Long.valueOf(arg1.asLong().longValue() | arg2.asLong().longValue()));
    }
    
    public Value executeBoolBool(final Value arg1, final Value arg2) {
        return Value.valueOf(Boolean.valueOf(arg1.asBoolean().booleanValue() || arg2.asBoolean().booleanValue()));
    }

     public ExpressionItemPriority getExpressionItemPriority() {
        return ExpressionItemPriority.LOGICAL;
    }
}
