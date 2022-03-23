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
package org.modelio.module.modelermodule.api.xmi.standard.templateparameter;

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
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link TemplateParameter} with << UML2ConnectableElementTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac45a46f-1893-4689-9905-62e6e9724c4f")
public class UML2ConnectableElementTemplateParameter {
    @objid ("3daca309-28b1-46b2-847f-a6cb1de7f1e5")
    public static final String STEREOTYPE_NAME = "UML2ConnectableElementTemplateParameter";

    /**
     * The underlying {@link TemplateParameter} represented by this proxy, never null.
     */
    @objid ("9badbca2-3a5d-4de1-9d84-c47d0c50a502")
    protected final TemplateParameter elt;

    /**
     * Tells whether a {@link UML2ConnectableElementTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("62897419-2289-4751-895b-9f0306c7355a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof TemplateParameter) && ((TemplateParameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> then instantiate a {@link UML2ConnectableElementTemplateParameter} proxy.
     * 
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy on the created {@link TemplateParameter}.
     */
    @objid ("11a6e4f1-c818-4ac2-8651-f0178361c738")
    public static UML2ConnectableElementTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.TemplateParameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ConnectableElementTemplateParameter.STEREOTYPE_NAME);
        return UML2ConnectableElementTemplateParameter.instantiate((TemplateParameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a TemplateParameter
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("ae5da28d-7e70-4a00-b49a-dc560efd7e73")
    public static UML2ConnectableElementTemplateParameter instantiate(TemplateParameter obj) {
        return UML2ConnectableElementTemplateParameter.canInstantiate(obj) ? new UML2ConnectableElementTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ConnectableElementTemplateParameter} proxy from a {@link TemplateParameter} stereotyped << UML2ConnectableElementTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link TemplateParameter}
     * @return a {@link UML2ConnectableElementTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("df0ff3f9-da0c-4067-bb54-16366609241b")
    public static UML2ConnectableElementTemplateParameter safeInstantiate(TemplateParameter obj) throws IllegalArgumentException {
        if (UML2ConnectableElementTemplateParameter.canInstantiate(obj))
        	return new UML2ConnectableElementTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2ConnectableElementTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("96c59108-ca79-404d-b5f0-c7c4841802ba")
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
        UML2ConnectableElementTemplateParameter other = (UML2ConnectableElementTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link TemplateParameter}. 
     * @return the TemplateParameter represented by this proxy, never null.
     */
    @objid ("793432b5-8cb0-4950-8922-3163e1450a84")
    public TemplateParameter getElement() {
        return this.elt;
    }

    @objid ("32a91066-c57f-48b2-8358-521111629d21")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("de7f0d90-0d50-48b1-b803-c8f8bcffc826")
    protected  UML2ConnectableElementTemplateParameter(TemplateParameter elt) {
        this.elt = elt;
    }

    @objid ("938d54a8-5da0-4ddb-804b-e7b573a5d935")
    public static final class MdaTypes {
        @objid ("9d961f72-5faf-4194-85d2-8beada3a13a2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a693e320-d7e2-4575-a3a8-6819b9a11cd3")
        private static Stereotype MDAASSOCDEP;

        @objid ("a13581bb-a863-4c66-b59c-1255638bd387")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0ada3ff1-4db1-4476-8869-f3da16dd9d1f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ff6e0375-5d09-11df-a996-001302895b2b");
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
