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
package org.modelio.module.modelermodule.api.code.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} metaclass.
 * <p>Description:
 * <br/><i>MMStandardOperation</i></p>
 */
@objid ("0d96d790-acba-4008-ba91-0d19e3d43fd8")
public class MMStandardOperation {
    @objid ("3a8d4d4b-1cbb-408c-bd48-309ebbf7758f")
    public static final String NOCODE_TAGTYPE = "nocode";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("2e51d095-c0e4-4546-8d5f-d7a7cc5cf0af")
    protected final Operation elt;

    /**
     * Tells whether a {@link MMStandardOperation proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation}.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("24ddf230-895f-437c-9cec-51ecdcd8dd00")
    public static boolean canInstantiate(MObject elt) {
        return (elt instanceof Operation);
    }

    /**
     * Tries to instantiate a {@link MMStandardOperation} proxy from a {@link Operation} checking its metaclass. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link MMStandardOperation} proxy or <i>null</i>.
     */
    @objid ("d31ad680-8d61-44b7-a1f9-46bf14866ece")
    public static MMStandardOperation instantiate(Operation obj) {
        return MMStandardOperation.canInstantiate(obj) ? new MMStandardOperation(obj) : null;
    }

    @objid ("c1201042-0054-43e5-864a-39552453d0f0")
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
        MMStandardOperation other = (MMStandardOperation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("296a52f8-0783-440b-afcc-3a671439d991")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("0fdbb93f-33e7-4cc8-9a07-224fe819be14")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Getter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("79099916-7a4d-42bd-bb49-4e9631052389")
    public boolean isNocode() {
        return this.elt.isTagged(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'nocode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("e95d25c1-0c5b-4c3e-b0e1-24d6b45ccd53")
    public void setNocode(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(MMStandardOperation.MdaTypes.NOCODE_TAGTYPE_ELT);
    }

    @objid ("e27921de-1887-48d1-b30b-8bed45bfc635")
    protected  MMStandardOperation(Operation elt) {
        this.elt = elt;
    }

    @objid ("07e0b6ca-7e0b-432d-b376-9cd9c9f48459")
    public static final class MdaTypes {
        @objid ("c565c6da-5890-4f2f-9db8-9bc6b1321407")
        public static TagType NOCODE_TAGTYPE_ELT;

        @objid ("3ace3f3e-b4d3-43aa-9f8b-baff21075071")
        private static Stereotype MDAASSOCDEP;

        @objid ("3d9f1642-ee40-4a9c-a95f-123d6f67b88f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("87fa581c-cda3-4e80-9d23-92cf01b6317f")
        public static void init(IModuleContext ctx) {
            NOCODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "00000000-0000-36bd-0000-000000000000");
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
