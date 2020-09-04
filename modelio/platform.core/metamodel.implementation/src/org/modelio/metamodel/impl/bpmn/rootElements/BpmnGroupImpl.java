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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnGroupData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007a5ba2-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnGroupImpl extends BpmnArtifactImpl implements BpmnGroup {
    @objid ("562f8627-6974-433f-adfa-0ccea1679ee8")
    @Override
    public String getCategory() {
        return (String) getAttVal(((BpmnGroupSmClass)getClassOf()).getCategoryAtt());
    }

    @objid ("65830aeb-97f6-495d-997c-eaed08c2e9ba")
    @Override
    public void setCategory(String value) {
        setAttVal(((BpmnGroupSmClass)getClassOf()).getCategoryAtt(), value);
    }

    @objid ("c8f4d71e-f00f-415d-aaa9-b6b370c69a20")
    @Override
    public EList<BpmnFlowElement> getCategorized() {
        return new SmList<>(this, ((BpmnGroupSmClass)getClassOf()).getCategorizedDep());
    }

    @objid ("b5fd9293-4da8-4382-914b-5f3b545229c6")
    @Override
    public <T extends BpmnFlowElement> List<T> getCategorized(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnFlowElement element : getCategorized()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("61bdd3ba-4ae5-44c4-931a-101d6569c9ed")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("91b9f1d2-4322-4b97-9890-e24b40f378b1")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("b9ff1f45-0075-4131-b01d-e7e93d56ccda")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnGroup(this);
    }

}
