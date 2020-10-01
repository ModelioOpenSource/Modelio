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

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link BpmnDataAssociation}
 */
@objid ("7e974158-1eb2-11e2-8009-002564c97630")
public class BpmnDataAssociationCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("c019359c-883f-40eb-85ac-8406b8ac17f4")
    public BpmnDataAssociationCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

    @objid ("7e974166-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(final MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass) {
        return canSource(linkMetaclass, fromMetaclass)
                && canTarget(linkMetaclass, toMetaclass)
                // Either the source or the target must be a BpmnItemAwareElement, but not both
                && (BpmnItemAwareElement.class.isAssignableFrom(fromMetaclass.getJavaInterface()) ^ BpmnItemAwareElement.class.isAssignableFrom(toMetaclass.getJavaInterface()));
    }

    @objid ("97d3a7a1-0bb7-4312-98c3-2c6c2a11133c")
    @Override
    public boolean canLink(final MClass linkMetaclass, final MObject fromElement, final MObject toElement) {
        if (!canLink(linkMetaclass, fromElement.getMClass(), toElement.getMClass())) {
            return false;
        }
        return isSameContext(fromElement, toElement);
    }

    @objid ("7e97415d-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(MClass linkMetaclass, MClass fromMetaclass) {
        Class<? extends MObject> fromJmc = fromMetaclass.getJavaInterface();
        if (BpmnActivity.class.isAssignableFrom(fromJmc)) {
            return true;
        } else if (BpmnItemAwareElement.class.isAssignableFrom(fromJmc) && !BpmnDataOutput.class.isAssignableFrom(fromJmc)) {
            return true;
        } else if (BpmnCatchEvent.class.isAssignableFrom(fromJmc)) {
            return true;
        }
        return false;
    }

    @objid ("0656ed26-75fb-4c47-968d-b10534f1f3a9")
    @Override
    public boolean canSource(final MObject linkElement, final MObject fromElement) {
        return canSource(linkElement.getMClass(), fromElement.getMClass());
    }

    @objid ("218defb5-d69d-4cf2-9c78-5107817f060d")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass toMetaclass) {
        Class<? extends MObject> toJmc = toMetaclass.getJavaInterface();
        if (BpmnActivity.class.isAssignableFrom(toJmc)) {
            return true;
        } else if (BpmnItemAwareElement.class.isAssignableFrom(toJmc) && !BpmnDataInput.class.isAssignableFrom(toJmc)) {
            return true;
        } else if (BpmnThrowEvent.class.isAssignableFrom(toJmc)) {
            return true;
        }
        return false;
    }

    @objid ("a7dd99c1-1aeb-42c3-9a40-c02d004c4b55")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        return canTarget(linkElement.getMClass(), to.getMClass());
    }

    /**
     * @return <code>true</code> if the source and destination elements belong to the same {@link BpmnLane} or the same {@link BpmnProcess}.
     */
    @objid ("7e974171-1eb2-11e2-8009-002564c97630")
    private boolean isSameContext(final MObject fromElement, final MObject toElement) {
        MObject formContext = getContext(fromElement);
        MObject toContext = getContext(toElement);
        return formContext.equals(toContext);
    }

    @objid ("7e974179-1eb2-11e2-8009-002564c97630")
    private MObject getContext(final MObject element) {
        if (element instanceof BpmnProcess) {
            return element;
        }
        return getContext(element.getCompositionOwner());
    }

}
