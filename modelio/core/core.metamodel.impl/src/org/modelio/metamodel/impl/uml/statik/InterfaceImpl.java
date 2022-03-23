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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
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

@objid ("000e545c-c4bf-1fd8-97fe-001ec947cd2a")
public class InterfaceImpl extends GeneralClassImpl implements Interface {
    @objid ("1545c859-d370-41ac-8019-a3a9b7c33947")
    @Override
    public EList<RequiredInterface> getRequiring() {
        return new SmList<>(this, ((InterfaceSmClass)getClassOf()).getRequiringDep());
    }

    @objid ("fe61e7c1-a255-42b4-9cfb-ec68ba49b462")
    @Override
    public <T extends RequiredInterface> List<T> getRequiring(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final RequiredInterface element : getRequiring()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("5a823c6a-2395-4580-8d9e-1696f20eab85")
    @Override
    public EList<InterfaceRealization> getImplementedLink() {
        return new SmList<>(this, ((InterfaceSmClass)getClassOf()).getImplementedLinkDep());
    }

    @objid ("4714d5c4-9329-4dee-a448-5c4e91e9987a")
    @Override
    public <T extends InterfaceRealization> List<T> getImplementedLink(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InterfaceRealization element : getImplementedLink()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("9873aa48-f25c-4729-bfab-a20c0e257e4e")
    @Override
    public EList<ProvidedInterface> getProviding() {
        return new SmList<>(this, ((InterfaceSmClass)getClassOf()).getProvidingDep());
    }

    @objid ("f3205312-a32c-47bb-a808-a717e87849eb")
    @Override
    public <T extends ProvidedInterface> List<T> getProviding(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ProvidedInterface element : getProviding()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("8cc06e09-a050-431a-bdb4-282730ce6fa3")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("77d733f0-e28c-42cb-83fc-460bf3a1a29d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("4735f38b-886a-42f7-9621-cbff1f803d3c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInterface(this);
    }

}
