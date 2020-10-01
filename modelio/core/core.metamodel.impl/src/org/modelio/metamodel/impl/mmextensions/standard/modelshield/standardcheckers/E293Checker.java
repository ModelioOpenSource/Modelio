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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.generic.DepCardinalityChecker;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;

/**
 * E210:
 * <ul>
 * <li>desc = An AssociationEnd must have an opposite.</li>
 * <li>what = The ''{0}'' association role does not have an opposite role.</li>
 * </ul>
 */
@objid ("672376df-49ee-4601-9496-4b32cd0cff02")
public class E293Checker extends DepCardinalityChecker {
    @objid ("06511460-c693-446a-a3fa-d55617620760")
    private static final String ERRORID = "E293";

    @objid ("2da6d91c-be44-4f79-9853-b6f7c9aed016")
    private static final String DEPNAME = "Opposite";

    @objid ("3f3df38c-8e8d-48fd-9d24-e421ab8beb8c")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=create, metaclass=Association, feature=null
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Create, null);
        
        // trigger=create, metaclass=Association, feature=Connection
        plan.registerChecker(this, smMetamodel.getMClass(AssociationEnd.class), TriggerType.Update, DEPNAME);
    }

    @objid ("ca7880cf-4ab2-422b-a056-4e39f34dfa17")
    public E293Checker() {
        super(ERRORID, DEPNAME);
    }

    @objid ("c2a3bd5c-6133-4d4e-8dbe-7965f2941834")
    @Override
    protected ModelError createError(MObject object, MDependency dep, int currentCard) {
        AssociationEnd role = (AssociationEnd) object;
        
        Classifier src = role.getSource();
        Classifier target = role.getTarget();
        
        StringBuilder label = new StringBuilder();
        getLabel(label , src);
        label.append('.');
        getLabel(label , role);
        label.append(": ");
        getLabel(label, target);
        
        List<Object> objects = new ArrayList<>();
        objects.add(label.toString());
        objects.add(currentCard);
        return new ModelError(ERRORID, object, objects);
    }

    @objid ("b0664aeb-4ddc-494e-8401-7897fd2a967a")
    private static void getLabel(StringBuilder label, MObject obj) {
        if (obj == null) {
            label.append("(none)");
        } else try {
            // Append name
            label.append(obj.getName());
            
            // Append deleted/shell status
            boolean deleted = obj.isDeleted();
            boolean shell = obj.isShell();
            if (deleted || shell) {
                label.append('(');
                if (deleted) label.append("deleted");
                if (deleted && shell) label.append(", ");
                if (shell) label.append("shell");
                label.append(')');
            }
        } catch (RuntimeException e) {
            label.append("<"+e.toString()+">");
        }
    }

}
