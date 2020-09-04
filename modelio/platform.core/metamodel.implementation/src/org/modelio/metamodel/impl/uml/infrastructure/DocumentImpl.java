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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.DocumentData;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00927aac-c4be-1fd8-97fe-001ec947cd2a")
public class DocumentImpl extends AbstractResourceImpl implements Document {
    @objid ("463af7d0-9d47-4348-b7fe-99f0f56fdb4b")
    @Override
    public String getAbstract() {
        return (String) getAttVal(((DocumentSmClass)getClassOf()).getAbstractAtt());
    }

    @objid ("6eff891d-6b3d-496b-8582-93dc130f55d8")
    @Override
    public void setAbstract(String value) {
        setAttVal(((DocumentSmClass)getClassOf()).getAbstractAtt(), value);
    }

    @objid ("aa364e4c-d607-4c54-a00e-883fd2c09e72")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("66d33d23-e772-4b95-9325-d2562ef56a14")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("4fc2f024-f0a3-40ed-ac33-5b4f702d1dc9")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDocument(this);
    }

}
