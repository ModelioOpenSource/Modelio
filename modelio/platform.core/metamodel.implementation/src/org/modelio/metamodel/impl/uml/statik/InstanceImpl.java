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
import org.modelio.metamodel.impl.uml.statik.InstanceData;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000d8770-c4bf-1fd8-97fe-001ec947cd2a")
public class InstanceImpl extends UmlModelElementImpl implements Instance {
    @objid ("e360fe68-19b0-4372-95cb-294389226338")
    @Override
    public boolean isIsConstant() {
        return (Boolean) getAttVal(((InstanceSmClass)getClassOf()).getIsConstantAtt());
    }

    @objid ("c85c7872-a397-4a03-972c-1967b21ca868")
    @Override
    public void setIsConstant(boolean value) {
        setAttVal(((InstanceSmClass)getClassOf()).getIsConstantAtt(), value);
    }

    @objid ("af8f0d54-92a8-4913-b7e5-9c8f86fa2b25")
    @Override
    public String getMultiplicityMin() {
        return (String) getAttVal(((InstanceSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("bd8a14f7-c97e-4683-b103-4d9e8e285c83")
    @Override
    public void setMultiplicityMin(String value) {
        setAttVal(((InstanceSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("012bb5ef-710b-400e-a8bb-8392803a09f7")
    @Override
    public String getMultiplicityMax() {
        return (String) getAttVal(((InstanceSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("fea6e5d3-ee25-42dd-af5a-5b3e2d2bde0f")
    @Override
    public void setMultiplicityMax(String value) {
        setAttVal(((InstanceSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("d3d1a8a0-7191-48c8-ae9a-ddc1ec458aab")
    @Override
    public String getValue() {
        return (String) getAttVal(((InstanceSmClass)getClassOf()).getValueAtt());
    }

    @objid ("71af336d-4731-46e9-9d06-79eb7b291dbb")
    @Override
    public void setValue(String value) {
        setAttVal(((InstanceSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("593f33ac-cad9-47e4-8e2d-028a09a29992")
    @Override
    public EList<CommunicationNode> getRepresentedCommunicationNode() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getRepresentedCommunicationNodeDep());
    }

    @objid ("d53fc10a-65c4-4c16-83e5-c47b52130d9b")
    @Override
    public <T extends CommunicationNode> List<T> getRepresentedCommunicationNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationNode element : getRepresentedCommunicationNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("a08bdcb0-fbf5-4ab8-ae6a-ce8a43a0d22e")
    @Override
    public EList<LinkEnd> getOwnedEnd() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getOwnedEndDep());
    }

    @objid ("32bcd035-eefc-4eec-9337-9f1322c820c8")
    @Override
    public <T extends LinkEnd> List<T> getOwnedEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getOwnedEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("0d7d2361-921a-4b4e-9218-3dfced967274")
    @Override
    public NameSpace getBase() {
        Object obj = getDepVal(((InstanceSmClass)getClassOf()).getBaseDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("11db13bf-a26c-4476-a776-3b4ae9479fca")
    @Override
    public void setBase(NameSpace value) {
        appendDepVal(((InstanceSmClass)getClassOf()).getBaseDep(), (SmObjectImpl)value);
    }

    @objid ("0e2527e9-1250-4365-9543-bc0e91fb9017")
    @Override
    public EList<ObjectNode> getRepresentingObjectNode() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getRepresentingObjectNodeDep());
    }

    @objid ("01127c98-aec5-4bba-b15c-c6bb84fe5358")
    @Override
    public <T extends ObjectNode> List<T> getRepresentingObjectNode(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ObjectNode element : getRepresentingObjectNode()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("78b26660-c95a-491d-960d-0fe591283ebf")
    @Override
    public NameSpace getOwner() {
        Object obj = getDepVal(((InstanceSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("fb064d45-fa0c-49a3-95d2-47be67438247")
    @Override
    public void setOwner(NameSpace value) {
        appendDepVal(((InstanceSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("34dd603d-21c4-4bc7-a5c4-074df367dde3")
    @Override
    public EList<NaryLinkEnd> getOwnedNaryEnd() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getOwnedNaryEndDep());
    }

    @objid ("22581c9e-2158-4ca4-8428-028c48767534")
    @Override
    public <T extends NaryLinkEnd> List<T> getOwnedNaryEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryLinkEnd element : getOwnedNaryEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("99380b89-db44-4295-b0b1-a159498591de")
    @Override
    public EList<Lifeline> getRepresentedLifeLine() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getRepresentedLifeLineDep());
    }

    @objid ("f351c3a8-272f-4429-9b8f-70bcbee5fe16")
    @Override
    public <T extends Lifeline> List<T> getRepresentedLifeLine(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Lifeline element : getRepresentedLifeLine()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8a666080-cad2-4e09-9d80-dc9d4ea7aa89")
    @Override
    public EList<AttributeLink> getSlot() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getSlotDep());
    }

    @objid ("a98ba7dc-0088-45bc-a23c-dafab4dacd1e")
    @Override
    public <T extends AttributeLink> List<T> getSlot(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AttributeLink element : getSlot()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f5357949-4db2-4436-b766-3c6691883108")
    @Override
    public EList<BindableInstance> getPart() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getPartDep());
    }

    @objid ("d2f5b292-8640-4994-8b78-d2c42a9fb8d9")
    @Override
    public <T extends BindableInstance> List<T> getPart(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BindableInstance element : getPart()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("549fac97-2427-476d-bd73-e2b324be1b35")
    @Override
    public EList<LinkEnd> getTargetingEnd() {
        return new SmList<>(this, ((InstanceSmClass)getClassOf()).getTargetingEndDep());
    }

    @objid ("c52aa516-fbb4-4314-bef4-ea499d6d2de0")
    @Override
    public <T extends LinkEnd> List<T> getTargetingEnd(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getTargetingEnd()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("cbd958f1-f790-4f3c-b7d2-d09346fc971d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((InstanceSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("9265b310-ddca-4488-89ce-9f449c6f0c26")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((InstanceSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("36477e8b-d98c-4fc8-b4db-f43761c34a7d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInstance(this);
    }

}
