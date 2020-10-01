/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.platform.module.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.e4.core.commands.ExpressionContext;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.emf.ecore.EObject;

/**
 * Expression used to determine whether a MUIElement provided by a module should be visible or not. Calls the method annotated
 * {@link IsVisible} (if any) on the passed object. Default return value is {@link EvaluationResult#TRUE} if no such method is
 * found.
 * 
 * @author fpoyer
 */
@objid ("17e936a5-13bd-11e2-825e-001ec947c8cc")
public class IsVisibleExpression extends Expression {
    @objid ("7fb784ae-ee30-4c21-a795-11ea4d5f2bd0")
    private final MUIElement item;

    @objid ("17e936a7-13bd-11e2-825e-001ec947c8cc")
    private final Object object;

    @objid ("17e936a9-13bd-11e2-825e-001ec947c8cc")
    public IsVisibleExpression(Object object, MUIElement item) {
        this.object = object;
        this.item = item;
    }

    @objid ("17e936ac-13bd-11e2-825e-001ec947c8cc")
    @Override
    public EvaluationResult evaluate(IEvaluationContext context) {
        // Get the "closest" context:
        // Usually the Eclipse context is readily accessible through the evaluation context
        // In case it is not, try to find an element that can provide it somewhere in the composition stack.
        IEclipseContext generalContext = null;
        if (context instanceof ExpressionContext) {
            generalContext = ((ExpressionContext) context).eclipseContext;
        } else {
            EObject eObject = (EObject) this.item;
            while (eObject != null && !(eObject instanceof MContext)) {
                eObject = eObject.eContainer();
            }
            if (eObject != null) {
                generalContext = ((MContext) eObject).getContext();
            }
        }
        if (generalContext != null) {
            // This local context is used to avoid polluting the general context with temporary values
            final IEclipseContext localContext = EclipseContextFactory.create("tmp-staticContext");
            try {
                // Call the method annotated @IsVisible (if any, otherwise default value is returned: Boolean.TRUE)
                Boolean isVisible = (Boolean) ContextInjectionFactory.invoke(this.object, IsVisible.class, generalContext,
                        localContext, Boolean.TRUE);
                return EvaluationResult.valueOf(isVisible.booleanValue());
            } finally {
                localContext.dispose();
            }
        } else {
            return EvaluationResult.TRUE;
        }
    }

}
