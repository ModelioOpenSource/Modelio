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
package org.modelio.module.modelermodule.api.analyst.analyst.analystelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a AnalystElement metaclass.
 * <p>Description:
 * <br/><i>MMAnalystAnalystElement</i></p>
 */
@objid ("6f75f5c7-fe92-4a1c-bcc0-d4437977ddc7")
public class MMAnalystAnalystElement {
    /**
     * The underlying {@link AnalystElement} represented by this proxy, never null.
     */
    @objid ("5db7e67e-7b58-455c-bbf6-cdb4ba4c4d93")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMAnalystAnalystElement proxy} can be instantiated from a {@link MObject} checking it is a AnalystElement.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e2a82346-aeb5-46f1-b5b8-ebd5bb7bf990")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Analyst.AnalystElement");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMAnalystAnalystElement} proxy from an AnalystElement checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * 
     * @param obj a AnalystElement
     * @return a {@link MMAnalystAnalystElement} proxy or <i>null</i>.
     */
    @objid ("ba46702c-4e64-44db-ab86-30c026e0ffcd")
    public static MMAnalystAnalystElement instantiate(ModelElement obj) {
        return MMAnalystAnalystElement.canInstantiate(obj) ? new MMAnalystAnalystElement(obj) : null;
    }

    @objid ("66c5926a-86e4-41f0-88cb-5d4a14e8bc0e")
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
        MMAnalystAnalystElement other = (MMAnalystAnalystElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying AnalystElement.
     * 
     * @return the AnalystElement represented by this proxy, never null.
     */
    @objid ("729b1d2f-4437-4d6b-88b8-cf6c5ecf757c")
    public ModelElement getElement() {
        return this.elt;
    }

    @objid ("6fd54071-dd99-4c57-8c8a-475457d968ab")
    @Override
    public int hashCode() {
        return 23 + (this.elt == null ? 0 : this.elt.hashCode());
    }

    @objid ("66fc77cf-27b7-496a-859f-59723737ec92")
    protected MMAnalystAnalystElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("67e8f8d4-4fb2-4f00-be1a-9e6a90e0562d")
    public static final class MdaTypes {
        @objid ("ea7a7357-6d25-4319-9fc7-d2d7d509b238")
        private static Stereotype MDAASSOCDEP;

        @objid ("6784ad7e-73b0-4176-84e0-aa01a3b3ff32")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b655e613-4014-4f06-a8d3-90c1cf4f919d")
        public static void init(IModuleContext ctx) {
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
