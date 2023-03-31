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
package org.modelio.module.modelermodule.api.xmi.standard.parameter;

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
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Parameter} with << UML2OperationTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d3e40d9b-99b6-479e-872c-bab0545bb6db")
public class UML2OperationTemplateParameter {
    @objid ("66232745-197b-447c-b251-7beeeb977a80")
    public static final String STEREOTYPE_NAME = "UML2OperationTemplateParameter";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("8389f4f8-c866-486e-a62f-0b815e8d7dad")
    protected final Parameter elt;

    /**
     * Tells whether a {@link UML2OperationTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("b73a94fe-1387-428c-92d5-762752d56187")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Parameter) && ((Parameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Parameter} stereotyped << UML2OperationTemplateParameter >> then instantiate a {@link UML2OperationTemplateParameter} proxy.
     * 
     * @return a {@link UML2OperationTemplateParameter} proxy on the created {@link Parameter}.
     */
    @objid ("6c7d9758-1dfe-40ef-8ca3-d87df207ab14")
    public static UML2OperationTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Parameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME);
        return UML2OperationTemplateParameter.instantiate((Parameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Parameter
     * @return a {@link UML2OperationTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("3d2d7934-70fa-4b8b-a51e-5382f564d3f0")
    public static UML2OperationTemplateParameter instantiate(Parameter obj) {
        return UML2OperationTemplateParameter.canInstantiate(obj) ? new UML2OperationTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Parameter}
     * @return a {@link UML2OperationTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("369d8e48-68a1-4ee5-b127-ad4181a16dc5")
    public static UML2OperationTemplateParameter safeInstantiate(Parameter obj) throws IllegalArgumentException {
        if (UML2OperationTemplateParameter.canInstantiate(obj))
        	return new UML2OperationTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2OperationTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("685126a2-2f70-482b-bb0c-9ed44eed8259")
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
        UML2OperationTemplateParameter other = (UML2OperationTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Parameter}. 
     * @return the Parameter represented by this proxy, never null.
     */
    @objid ("3a0bcf01-b312-4345-938e-19406deae956")
    public Parameter getElement() {
        return this.elt;
    }

    @objid ("b1fb53dd-1bf1-4c1f-b9d7-f97db3cfccf0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("9f48920b-0d93-41ac-ae8b-643697a7ac3f")
    protected  UML2OperationTemplateParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("f0c89734-17df-443d-90e3-d99c1720d92b")
    public static final class MdaTypes {
        @objid ("0a5b4720-ff17-4871-9a1c-c63a7f87c654")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8de63277-cd2f-4fb4-9b0a-97707fd2c660")
        private static Stereotype MDAASSOCDEP;

        @objid ("08140964-27f2-4612-9ce8-28b98a239ea5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3e714969-b44e-4814-a5a8-be7aa7627efa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07ba6521-5d0d-11df-a996-001302895b2b");
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
