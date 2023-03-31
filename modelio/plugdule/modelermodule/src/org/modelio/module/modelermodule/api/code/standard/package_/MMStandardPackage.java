/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.code.standard.package_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} metaclass.
 * <p>Description:
 * <br/><i>MMStandardPackage</i></p>
 */
@objid ("d700434f-7988-4657-83de-27be7ea53a82")
public class MMStandardPackage {
    @objid ("01d1b9b1-4944-44c8-b1b0-a9c3762e5041")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("98689c5d-4cb2-4694-b6cf-04ffa8510fda")
    protected final Package elt;

    /**
     * Tells whether a {@link MMStandardPackage proxy} can be instantiated from a {@link MObject} checking it is a {@link Package}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("111428d4-d0c3-41dd-8d78-73b3bbe80fe9")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Package);
    }

    /**
     * Tries to instantiate a {@link MMStandardPackage} proxy from a {@link Package} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link MMStandardPackage} proxy or <i>null</i>.
     */
    @objid ("4ed70a41-1c18-421b-94e7-286421382fdb")
    public static MMStandardPackage instantiate(Package obj) {
        return MMStandardPackage.canInstantiate(obj) ? new MMStandardPackage(obj) : null;
    }

    @objid ("49bf8438-085d-41b7-a8c6-f68b9c87896f")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MMStandardPackage other = (MMStandardPackage) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("104551c6-aa68-4baf-825e-aa02a7465732")
    public Package getElement() {
        return this.elt;
    }

    @objid ("39c1249c-0024-43f4-8492-7117366bba57")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8a353709-f5d8-4381-bfac-55fa495c6ac0")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("bd964c86-9faa-4f8f-8372-55353f4e5f19")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("945626ac-c9bf-4edf-bca2-299c773e0344")
    protected  MMStandardPackage(Package elt) {
        this.elt = elt;
    }

    @objid ("f21921ce-4059-4be1-8c60-757faebc519f")
    public static final class MdaTypes {
        @objid ("e8045790-c0a3-4f8d-953e-257a48be2e58")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("8f4c2f38-25fd-425a-99c3-33db7083dde2")
        private static Stereotype MDAASSOCDEP;

        @objid ("e7430840-3838-4228-ae91-2b6e5428fce7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("43161e30-7498-435e-9e26-b91613c0059c")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36bf-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }

	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
