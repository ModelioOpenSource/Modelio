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
package org.modelio.metamodel.impl.diagrams;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("3179c664-e485-47e8-aacc-ed2e277ea76b")
public class BehaviorDiagramSmClass extends AbstractDiagramSmClass {
    @objid ("25bcc39f-633b-4015-ad9b-08d2d54323b7")
    public BehaviorDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("f66a8622-6293-443e-8c79-4576f8633af8")
    @Override
    public String getName() {
        return "BehaviorDiagram";
    }

    @objid ("0676b863-8273-4c16-98c0-aef8b69adf2c")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("eb2d99c8-377f-4ca6-899b-450138603bad")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return BehaviorDiagram.class;
    }

    @objid ("3ff18417-4e43-4802-94d8-fdbb95b3d5af")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("5dab8924-f870-4742-96ac-fb178f748674")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("0b53060e-429c-459a-bee5-9dc695b6748c")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new BehaviorDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
    }

    @objid ("c2789e32-bd52-49cb-b045-c5095e7ca2eb")
    private static class BehaviorDiagramObjectFactory implements ISmObjectFactory {
        @objid ("8dfb81a3-b60e-4030-9dc6-3894b0a3c7b1")
        private BehaviorDiagramSmClass smClass;

        @objid ("ac0c4b67-583f-4cba-b50e-79fc93fc0dd7")
        public BehaviorDiagramObjectFactory(BehaviorDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("075f602e-758a-43c8-83e7-5c3338939eaf")
        @Override
        public ISmObjectData createData() {
            throw new UnsupportedOperationException();
        }

        @objid ("623efd65-ec90-44ac-b9d5-7f601f56eb67")
        @Override
        public SmObjectImpl createImpl() {
            throw new UnsupportedOperationException();
        }

    }

}
