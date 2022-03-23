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
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.ISmObjectFactory;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

@objid ("902edc6d-6529-4c52-936a-32642a134d20")
public class UsageSmClass extends DependencySmClass {
    @objid ("4685e497-e51c-4d64-af72-61b9682ade5d")
    public  UsageSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("181e7f6f-b0e1-46a9-af43-535680ccbdc5")
    @Override
    public String getName() {
        return "Usage";
        
    }

    @objid ("20d763ca-0832-48f4-b604-6e258b17e5d1")
    @Override
    public Version getVersion() {
        return new Version("0.0.9054");
    }

    @objid ("b856c8ac-11e3-46a4-879e-f35060b7070d")
    @Override
    public java.lang.Class<? extends MObject> getJavaInterface() {
        return Usage.class;
        
    }

    @objid ("45f8559a-c515-4c83-96b7-d51955b210e4")
    @Override
    public boolean isCmsNode() {
        return false;
        
    }

    @objid ("5e4634c4-43b6-41cb-8ec2-b40d1563fe08")
    @Override
    public boolean isAbstract() {
        return false;
        
    }

    @objid ("0e00d427-570a-4807-8672-9826b1d48758")
    @Override
    public void load(SmMetamodel metamodel) {
        this.parentClass = metamodel.getMClass(Dependency.MQNAME);
        this.registerFactory(new UsageObjectFactory(this));
        
        
        // Initialize and register the SmAttribute
        
        // Initialize and register the SmDependency
        
    }

    @objid ("acd4ea6f-4b9d-47af-8421-c9486900a632")
    @Override
    public boolean isLinkMetaclass() {
        return true;
        
    }

    @objid ("182c8855-4230-4580-80e3-51d3dda86b87")
    private static class UsageObjectFactory implements ISmObjectFactory {
        @objid ("eeb70b07-b996-4a1d-9d8a-d4380ed2bdc3")
        private UsageSmClass smClass;

        @objid ("2f1fbce2-32f5-4cb2-80b1-abc86a9c08d1")
        public  UsageObjectFactory(UsageSmClass smClass) {
            this.smClass = smClass;
        }

        @objid ("d128b944-b105-4d88-964e-f0e8c907959c")
        @Override
        public ISmObjectData createData() {
            return new UsageData(this.smClass);
        }

        @objid ("5dd3b127-970e-43fc-8109-014502431dda")
        @Override
        public SmObjectImpl createImpl() {
            return new UsageImpl();
        }

    }

}
