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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00376270-c4bf-1fd8-97fe-001ec947cd2a")
public class LoopNodeImpl extends StructuredActivityNodeImpl implements LoopNode {
    @objid ("efdaef80-137d-480e-b889-002704b82d18")
    @Override
    public boolean isIsTestedFirst() {
        return (Boolean) getAttVal(((LoopNodeSmClass)getClassOf()).getIsTestedFirstAtt());
    }

    @objid ("f3bdd9f4-3e68-4cd7-9505-ecd68bf85732")
    @Override
    public void setIsTestedFirst(boolean value) {
        setAttVal(((LoopNodeSmClass)getClassOf()).getIsTestedFirstAtt(), value);
    }

    @objid ("8c1ce690-44f8-4b55-a764-a3048e981f75")
    @Override
    public String getSetup() {
        return (String) getAttVal(((LoopNodeSmClass)getClassOf()).getSetupAtt());
    }

    @objid ("c7df1570-ece9-47eb-9b72-e7fe79a54c34")
    @Override
    public void setSetup(String value) {
        setAttVal(((LoopNodeSmClass)getClassOf()).getSetupAtt(), value);
    }

    @objid ("e6872b31-b114-4e06-a517-54967c51db68")
    @Override
    public String getTest() {
        return (String) getAttVal(((LoopNodeSmClass)getClassOf()).getTestAtt());
    }

    @objid ("2990b133-9edc-4c83-abfe-69c1ac293f3c")
    @Override
    public void setTest(String value) {
        setAttVal(((LoopNodeSmClass)getClassOf()).getTestAtt(), value);
    }

    @objid ("e68a67b2-7247-4a75-8904-510db5e394e2")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("c23fd543-6398-475a-88ae-73b91090bddd")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("bc84382b-5b1a-4a2f-8185-debf45e608a5")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitLoopNode(this);
    }

}
