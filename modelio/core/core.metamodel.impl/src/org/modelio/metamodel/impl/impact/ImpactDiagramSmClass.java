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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impl.diagrams.AbstractDiagramSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("09aabc1b-3c73-4356-8c32-fe32f693c2c1")
public class ImpactDiagramSmClass extends AbstractDiagramSmClass {
    @objid ("f0d1ecb5-d979-473f-bf08-80dc12d0731c")
    public  ImpactDiagramSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("cc736036-a00c-4a0c-ad65-6853e4d597bf")
    @Override
    public String getName() {
        return "ImpactDiagram";
        
    }

    @objid ("68ff53f2-5ffd-4126-a9d4-d5d3147d36e5")
    @Override
    public Version getVersion() {
        return new Version("3.6.00");
    }

    @objid ("680cd618-51ea-4bf5-9c39-bab5db1ab561")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return ImpactDiagram.class;
        
    }

    @objid ("023fc063-e8ea-428c-93ad-43e426a9a897")
    @Override
    public boolean isCmsNode() {
        return true;
        
    }

    @objid ("e0bbc061-0765-45f0-a3c0-d941015e3ced")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("6146dcdd-9e27-4c86-8c36-629752f7dad6")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(AbstractDiagram.MQNAME);
        this.registerFactory(new ImpactDiagramObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("2beae2ef-f96a-4cf6-a497-966596be45ac")
    private static class ImpactDiagramObjectFactory implements ISmObjectFactory {
        @objid ("7b2160e3-c866-4e17-a32f-a24a8d79e7c6")
        private ImpactDiagramSmClass smClass;

        @objid ("488e1ce9-7a38-4059-bab2-6e132dafcc08")
        public  ImpactDiagramObjectFactory(ImpactDiagramSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("ed2d3cc9-c2f5-4cd3-9031-c2be588247fa")
        @Override
        public ISmObjectData createData() {
            return new ImpactDiagramData(this.smClass);
        }

        @objid ("9f4bb134-06d2-486d-8e31-afd4e27517ec")
        @Override
        public SmObjectImpl createImpl() {
            return new ImpactDiagramImpl();
        }

    }

}
