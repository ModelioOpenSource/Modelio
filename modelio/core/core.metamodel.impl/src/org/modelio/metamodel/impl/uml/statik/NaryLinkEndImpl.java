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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0075d726-17e8-10a1-88a0-001ec947cd2a")
public class NaryLinkEndImpl extends UmlModelElementImpl implements NaryLinkEnd {
    @objid ("556c562b-5382-463b-8d2f-11220f2bfe23")
    @Override
    public boolean isIsOrdered() {
        return (Boolean) getAttVal(((NaryLinkEndSmClass)getClassOf()).getIsOrderedAtt());
    }

    @objid ("4003b39a-b01a-4978-9cfd-992e82976bcf")
    @Override
    public void setIsOrdered(boolean value) {
        setAttVal(((NaryLinkEndSmClass)getClassOf()).getIsOrderedAtt(), value);
    }

    @objid ("910ad98e-ddaa-4131-85cf-d28ddd687646")
    @Override
    public boolean isIsUnique() {
        return (Boolean) getAttVal(((NaryLinkEndSmClass)getClassOf()).getIsUniqueAtt());
    }

    @objid ("bb2969e6-9651-4494-adf8-d60b006a9264")
    @Override
    public void setIsUnique(boolean value) {
        setAttVal(((NaryLinkEndSmClass)getClassOf()).getIsUniqueAtt(), value);
    }

    @objid ("f99d46c9-d5b0-4583-893f-49034e6b45dd")
    @Override
    public String getMultiplicityMax() {
        return (String) getAttVal(((NaryLinkEndSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("29c0d1c6-e0b7-47d2-8832-fccea592a910")
    @Override
    public void setMultiplicityMax(String value) {
        setAttVal(((NaryLinkEndSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("c021358b-2fc4-4bb7-917a-1bb88bb35268")
    @Override
    public String getMultiplicityMin() {
        return (String) getAttVal(((NaryLinkEndSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("9181c7f0-8890-4ae0-8b1b-0642e20b2f2e")
    @Override
    public void setMultiplicityMin(String value) {
        setAttVal(((NaryLinkEndSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("8f444599-5627-4adf-91e0-3d0bf9e7ff82")
    @Override
    public Instance getSource() {
        Object obj = getDepVal(((NaryLinkEndSmClass)getClassOf()).getSourceDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("0caa3aa4-dce3-4dab-8635-200f6ddb2e3f")
    @Override
    public void setSource(Instance value) {
        appendDepVal(((NaryLinkEndSmClass)getClassOf()).getSourceDep(), (SmObjectImpl)value);
    }

    @objid ("329f466b-40bf-44b4-a609-91ef46d40f61")
    @Override
    public NaryLink getNaryLink() {
        Object obj = getDepVal(((NaryLinkEndSmClass)getClassOf()).getNaryLinkDep());
        return (obj instanceof NaryLink)? (NaryLink)obj : null;
    }

    @objid ("7c4d73ed-ad3d-41b5-b791-78b9409458a5")
    @Override
    public void setNaryLink(NaryLink value) {
        appendDepVal(((NaryLinkEndSmClass)getClassOf()).getNaryLinkDep(), (SmObjectImpl)value);
    }

    @objid ("b6be080b-d319-4187-af62-2df92c07b5ac")
    @Override
    public RequiredInterface getConsumer() {
        Object obj = getDepVal(((NaryLinkEndSmClass)getClassOf()).getConsumerDep());
        return (obj instanceof RequiredInterface)? (RequiredInterface)obj : null;
    }

    @objid ("05653880-c486-4162-b795-83ed26478eff")
    @Override
    public void setConsumer(RequiredInterface value) {
        appendDepVal(((NaryLinkEndSmClass)getClassOf()).getConsumerDep(), (SmObjectImpl)value);
    }

    @objid ("308a4ced-6609-4438-a009-2cb22f89fdce")
    @Override
    public ProvidedInterface getProvider() {
        Object obj = getDepVal(((NaryLinkEndSmClass)getClassOf()).getProviderDep());
        return (obj instanceof ProvidedInterface)? (ProvidedInterface)obj : null;
    }

    @objid ("b8de3365-41e9-4bb3-8605-60574a81a15f")
    @Override
    public void setProvider(ProvidedInterface value) {
        appendDepVal(((NaryLinkEndSmClass)getClassOf()).getProviderDep(), (SmObjectImpl)value);
    }

    @objid ("b5289679-66ba-47f7-9070-0b02accc68a1")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Source
        obj = (SmObjectImpl)this.getDepVal(((NaryLinkEndSmClass)getClassOf()).getSourceDep());
        if (obj != null)
          return obj;
        // NaryLink
        obj = (SmObjectImpl)this.getDepVal(((NaryLinkEndSmClass)getClassOf()).getNaryLinkDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("cca15e63-166a-454e-9d32-b4b1271ab07c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Source
        dep = ((NaryLinkEndSmClass)getClassOf()).getSourceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // NaryLink
        dep = ((NaryLinkEndSmClass)getClassOf()).getNaryLinkDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5337848c-0e7d-4115-bb9e-cabb0967abd2")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitNaryLinkEnd(this);
    }

}
