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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("c97ba24a-0cba-4f7a-8f85-b93db65f4cf6")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("b7d5363b-bd30-45c1-9f75-14d3e183d096")
    protected final Package elt;

    /**
     * Tells whether a {@link MMStandardPackage proxy} can be instantiated from a {@link MObject} checking it is a {@link Package}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("15a58b7e-ce4d-4484-b522-7ee22b5fe03c")
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
    @objid ("49bf9d2e-95a8-4145-971a-f0b48e7eee3c")
    public static MMStandardPackage instantiate(Package obj) {
        return MMStandardPackage.canInstantiate(obj) ? new MMStandardPackage(obj) : null;
    }

    @objid ("c4fbceef-6ae8-4a3f-b523-b431fa4974b2")
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
    @objid ("38bd2436-de28-416c-99bf-bab64206e8eb")
    public Package getElement() {
        return this.elt;
    }

    @objid ("57f73d9b-dce5-4ec5-a889-16fdb2de75fe")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("64b65311-8893-4331-ab73-ebbdd371ae9d")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("402c5b7b-e097-46ee-be7d-9eaa72dd6d66")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardPackage.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("ad474bdc-bdeb-45c4-b005-1eab67eb6c6f")
    protected MMStandardPackage(Package elt) {
        this.elt = elt;
    }

    @objid ("f21921ce-4059-4be1-8c60-757faebc519f")
    public static final class MdaTypes {
        @objid ("47d272e6-ede6-4af7-9dde-f858c7b213db")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("b13f2cf3-2203-4342-9fe0-1715188edc56")
        private static Stereotype MDAASSOCDEP;

        @objid ("e56aaa64-e35c-4001-b1d6-b0f335f74ef8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("83d22faa-888b-4ff1-91fc-833367edcd16")
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
