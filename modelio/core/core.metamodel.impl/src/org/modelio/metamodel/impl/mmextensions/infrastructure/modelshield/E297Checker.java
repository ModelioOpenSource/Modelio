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
package org.modelio.metamodel.impl.mmextensions.infrastructure.modelshield;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.api.ModelError;
import org.modelio.vcore.smkernel.mapi.modelshield.api.TriggerType;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.IChecker;

/**
 * E297: No abstract stereotype must be applied on an element.
 * 
 * <ul>
 * <li>desc = A abstract stereotype may not be applied on any elements.</li>
 * <li>what = The ''{0}'' abstract stereotype is applied on the ''{1}'' element.</li>
 * </ul>
 * </p>
 * @author cma
 * @since Modelio Valkyrie 3.8
 */
@objid ("cf6ccfba-06a6-43cf-9c92-b7700dc72ad0")
final class E297Checker implements IChecker {
    @objid ("997193dc-9b51-4b22-826b-c3b69fc2648e")
    private static final String ruleId = "E297";

    @objid ("15458414-c88a-4203-a497-d1a48fb17f01")
    @Override
    public void register(IModelShieldRegistry plan, MMetamodel metamodel) {
        plan.registerChecker(this , metamodel.getMClass(ModelElement.class), TriggerType.Update, "Extension");
    }

    @objid ("33a4711f-2b0b-4d40-96ff-8fbf9914d10c")
    @Override
    public void check(MObject object, IErrorReport report) {
        ModelElement el = (ModelElement) object;
        for (Stereotype stereotype : el.getExtension()) {
            if (stereotype.isIsAbstract()) {
                report.addEntry(new ModelError(ruleId, el, stereotype));
            }
        }
        
    }

}
