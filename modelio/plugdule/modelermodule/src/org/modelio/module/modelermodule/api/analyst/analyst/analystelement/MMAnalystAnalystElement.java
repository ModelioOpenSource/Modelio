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
 * Proxy class to handle a Analyst.AnalystElement metaclass.
 * <p>Description:
 * <br/><i>MMAnalystAnalystElement</i></p>
 */
@objid ("6f75f5c7-fe92-4a1c-bcc0-d4437977ddc7")
public class MMAnalystAnalystElement {
    /**
     * The underlying {@link AnalystElement} represented by this proxy, never null.
     */
    @objid ("4b63a6af-95dd-4490-9a34-5d0638d2944f")
    protected final ModelElement elt;

    /**
     * Tells whether a {@link MMAnalystAnalystElement proxy} can be instantiated from a {@link MObject} checking it is a Analyst.AnalystElement.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("446a679b-7aa9-4257-9d41-d713701e0a45")
    public static boolean canInstantiate(MObject elt) {
        if (elt == null) {
            return false;
        }
        MClass mClass = elt.getMClass().getMetamodel().getMClass("Analyst.AnalystElement");
        return elt.getMClass().hasBase(mClass);
    }

    /**
     * Tries to instantiate a {@link MMAnalystAnalystElement} proxy from a Analyst.AnalystElement checking its metaclass.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AnalystElement
     * @return a {@link MMAnalystAnalystElement} proxy or <i>null</i>.
     */
    @objid ("31ea0d6f-3800-4564-ba86-d26b34339d51")
    public static MMAnalystAnalystElement instantiate(ModelElement obj) {
        return MMAnalystAnalystElement.canInstantiate(obj) ? new MMAnalystAnalystElement(obj) : null;
    }

    @objid ("aacd615d-aeb5-492b-9c6f-5352828f7c52")
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
     * Get the underlying Analyst.AnalystElement.
     * @return the AnalystElement represented by this proxy, never null.
     */
    @objid ("da9bcce9-89a6-4908-b2b0-a3d3e08d3957")
    public ModelElement getElement() {
        return this.elt;
    }

    @objid ("534cb4a6-77bd-4739-99bd-8102ed3abb7d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa623a4c-102b-4c3e-bf0a-cbc54245d7bc")
    protected  MMAnalystAnalystElement(ModelElement elt) {
        this.elt = elt;
    }

    @objid ("67e8f8d4-4fb2-4f00-be1a-9e6a90e0562d")
    public static final class MdaTypes {
        @objid ("22935b49-3023-4b76-a5b6-5fb092b415ce")
        private static Stereotype MDAASSOCDEP;

        @objid ("d356d4a4-b430-4a34-9445-e1f425eea227")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("06bfd201-cd3d-4381-8f84-de9e6eb18f54")
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
