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

package org.modelio.diagram.editor.statik.elements.templatebinding;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;

/**
 * Service class to get the label of a template binding.
 * <p>
 * Can be used on the template binding link or in a label in the bound node.
 */
@objid ("36dc318e-55b7-11e2-877f-002564c97630")
public class TemplateBindingSymbolProvider {
    /**
     * Get the symbol of the given template binding.
     * 
     * @param binding a template binding.
     * @return the symbol.
     */
    @objid ("36dc3190-55b7-11e2-877f-002564c97630")
    public static String get(final TemplateBinding binding) {
        final StringBuilder s = new StringBuilder(30);
        
        appendSymbol(binding.getBoundElement(), s);
        appendSymbol(binding.getBoundOperation(), s);
        
        s.append("<");
        boolean first = true;
        for (TemplateParameterSubstitution p : binding.getParameterSubstitution()) {
            if (first)
                first = false;
            else
                s.append(", ");
        
            appendSymbol(p, s);
        }
        s.append(">");
        return s.toString();
    }

    @objid ("36dc3199-55b7-11e2-877f-002564c97630")
    private static void appendSymbol(final TemplateParameterSubstitution p, final StringBuilder s) {
        final TemplateParameter formal = p.getFormalParameter();
        if (formal == null)
            s.append("?");
        else
            s.append(formal.getName());
        
        s.append("->");
        
        if (p.getActual() != null) {
            s.append(p.getActual().getName());
        } else if (p.getValue().isEmpty()) {
            s.append("?");
        } else {
            s.append(p.getValue());
        }
    }

    @objid ("36dc31a1-55b7-11e2-877f-002564c97630")
    private static void appendSymbol(final Operation op, final StringBuilder s) {
        if (op != null) {
            s.append(op.getName());
            s.append("(");
            boolean first = true;
            for (Parameter p : op.getIO()) {
                if (first)
                    first = false;
                else
                    s.append(", ");
        
                final GeneralClass type = p.getType();
                if (type == null)
                    s.append("undefined");
                else
                    s.append(type.getName());
            }
            s.append(")");
        
            final Parameter ret = op.getReturn();
            if (ret != null) {
                s.append(":");
                final GeneralClass type = ret.getType();
                if (type == null)
                    s.append("undefined");
                else
                    s.append(type.getName());
            }
        }
    }

    @objid ("36dc31a9-55b7-11e2-877f-002564c97630")
    private static void appendSymbol(final NameSpace ns, final StringBuilder s) {
        if (ns != null)
            s.append(ns.getName());
    }

}
