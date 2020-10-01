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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.DependencySmClass;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("71cbc9ba-e43d-4661-b852-efce11af5417")
public class AbstractionSmClass extends DependencySmClass {
    @objid ("551e16b0-2b91-4bd4-b25f-da995fa85943")
    private SmAttribute mappingAtt;

    @objid ("06287a1b-118e-48fe-a072-43c4bf259929")
    public AbstractionSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("6c73f039-e9da-498e-a5e1-de56ace280d1")
    @Override
    public String getName() {
        return "Abstraction";
    }

    @objid ("f4d7588d-1038-4c57-b672-521446438b53")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b2a51437-f2a9-4c11-9f7e-14fbac8e17e0")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Abstraction.class;
    }

    @objid ("a88b563e-d70f-4916-ad97-688a5a991ecd")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("9ccb5add-273b-4d98-86e7-5d34b0ef49b6")
    @Override
    public boolean isAbstract() {
        return false;
    }

    @objid ("163a2299-615f-4cc2-b6de-566c1f20c670")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Dependency.MQNAME);
        this.registerFactory(new AbstractionObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        this.mappingAtt = new MappingSmAttribute();
        this.mappingAtt.init("Mapping", this, String.class );
        registerAttribute(this.mappingAtt);
        
        
        // Initialize and register the SmDependency
    }

    @objid ("4f9439d3-2aec-4163-a8cb-0fbecc0cf8f7")
    public SmAttribute getMappingAtt() {
        if (this.mappingAtt == null) {
        	this.mappingAtt = this.getAttributeDef("Mapping");
        }
        return this.mappingAtt;
    }

    @objid ("fbafd079-ce22-4d09-b051-c736c50dd5dd")
    @Override
    public boolean isLinkMetaclass() {
        return true;
    }

    @objid ("158ccc0a-cc32-4135-a4b1-275229311823")
    private static class AbstractionObjectFactory implements ISmObjectFactory {
        @objid ("a97fd8c2-45c0-4532-a9ce-f5f8672d010e")
        private AbstractionSmClass smClass;

        @objid ("efa6adc6-7b18-44da-abe8-91e480716eca")
        public AbstractionObjectFactory(AbstractionSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("271eb5d6-53e6-4230-9edf-944a26161a67")
        @Override
        public ISmObjectData createData() {
            return new AbstractionData(this.smClass);
        }

        @objid ("d670f841-9741-4372-97d1-af942acbf9cb")
        @Override
        public SmObjectImpl createImpl() {
            return new AbstractionImpl();
        }

    }

    @objid ("f6bd0962-901c-496c-8d6d-e46a3a5a02a6")
    public static class MappingSmAttribute extends SmAttribute {
        @objid ("1a372054-0029-47d3-8934-56dc5b2e05c0")
        public Object getValue(ISmObjectData data) {
            return ((AbstractionData) data).mMapping;
        }

        @objid ("6dc98340-c4da-4f99-bd41-fe1f73c16371")
        public void setValue(ISmObjectData data, Object value) {
            ((AbstractionData) data).mMapping = value;
        }

    }

}
