/* 
 * Copyright 2013-2018 Modeliosoft
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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.LinkData;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("01170c7c-f6f1-4d69-b50e-5f5e0b067212")
public class LinkImpl extends UmlModelElementImpl implements Link {
    @objid ("8a613814-6158-4adc-a1ab-5dac81598ce7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        for (SmObjectImpl obj : this.getDepValList(((LinkSmClass) getClassOf()).getLinkEndDep())) {
            return obj;
        }
        return super.getCompositionOwner();
    }

    @objid ("4fb95fb2-6b47-4648-9ee9-8381d233fe17")
    @Override
    public SmDepVal getCompositionRelation() {
        for (SmObjectImpl obj : this.getDepValList(((LinkSmClass) getClassOf()).getLinkEndDep())) {
            return new SmDepVal(((LinkSmClass) getClassOf()).getLinkEndDep(), obj);
        }
        return super.getCompositionRelation();
    }

    @objid ("744787b7-3df1-4322-9aca-063a47159cc1")
    @Override
    public Association getModel() {
        Object obj = getDepVal(((LinkSmClass)getClassOf()).getModelDep());
        return (obj instanceof Association)? (Association)obj : null;
    }

    @objid ("7349998f-f24c-470e-9f7f-2d962eb081fc")
    @Override
    public void setModel(Association value) {
        appendDepVal(((LinkSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("c309ad30-b85d-4f80-bf9d-046f548807f8")
    @Override
    public EList<LinkEnd> getLinkEnd() {
        return new SmList<>(this, ((LinkSmClass)getClassOf()).getLinkEndDep());
    }

    @objid ("41881f3c-e0eb-48aa-b3fe-0324a6544a9a")
    @Override
    public <T extends LinkEnd> List<T> getLinkEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getLinkEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("4b65e64d-c085-4876-b969-74be081aac9f")
    @Override
    public CommunicationChannel getSent() {
        Object obj = getDepVal(((LinkSmClass)getClassOf()).getSentDep());
        return (obj instanceof CommunicationChannel)? (CommunicationChannel)obj : null;
    }

    @objid ("2ea64ce7-3c7d-4079-8882-191c5dd12170")
    @Override
    public void setSent(CommunicationChannel value) {
        appendDepVal(((LinkSmClass)getClassOf()).getSentDep(), (SmObjectImpl)value);
    }

    @objid ("6dabec80-bb68-46cd-bc34-6ce483a2086b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitLink(this);
    }

}
