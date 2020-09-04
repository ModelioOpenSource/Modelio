/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E280:
 * <ul>
 * <li>desc = A stereotype must not have cycle on its 'Parent' stereotype relation.</li>
 * <li>what = The ''{0}'' stereotype is part of a cycle due to its 'Parent' relation: %2</li>
 * </ul>
 */
@objid ("006ad1e6-e20e-1f69-b3fb-001ec947cd2a")
public class E280Checker implements IChecker {
    @objid ("007fbea8-51cf-1f6b-b3fb-001ec947cd2a")
    private static final String ERRORID = "E280";

    @objid ("00984860-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void check(MObject object, final IErrorReport report) {
        Stereotype currentStereotype = (Stereotype) object;
        List<Object> cycle = detectCycle(currentStereotype);
        
        if (cycle != null) {
            // Post the audit entry
            report.addEntry(new ModelError(ERRORID, (MObject) cycle.get(0), cycle));
        }
    }

    @objid ("00984a2c-e472-1f69-b3fb-001ec947cd2a")
    @Override
    public void register(final IModelShieldRegistry plan, MMetamodel smMetamodel) {
        // trigger=*, metaclass=Stereotype, feature=Parent
        plan.registerChecker(this, smMetamodel.getMClass(Stereotype.class), TriggerType.Update, "Parent");
    }

    @objid ("00802e74-51cf-1f6b-b3fb-001ec947cd2a")
    private List<Object> detectCycle(final Stereotype source) {
        List<Object> collector = new ArrayList<>();
        return detectCycle(source, collector);
    }

    @objid ("00805e1c-51cf-1f6b-b3fb-001ec947cd2a")
    private List<Object> detectCycle(final Stereotype stereotype, final List<Object> collector) {
        collector.add(stereotype);
        Stereotype parent = stereotype.getParent();
        
        // If there is no PARENT we are done...
        if (parent != null) {
            if (((MObject) parent).isValid()) {
                if (collector.contains(parent)) {
                    // There is a cycle here : build the exact cycle to fill the
                    // diagnostic
                    collector.add(parent);
                    return collector.subList(collector.indexOf(parent), collector.size() - 1);
                } else {
                    // There is no cycle up to this one, recursive call of
                    // detectCycle
                    return detectCycle(parent, collector);
                }
            }
        }
        return null;
    }

}
