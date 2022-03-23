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
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link StaticDiagram} with << requirement_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d48cb628-f2fd-4e33-96f1-a085f4eea318")
public class RequirementDiagram {
    @objid ("aa632075-7f5c-4cf3-ab34-d762c0f26358")
    public static final String STEREOTYPE_NAME = "requirement_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("7f378549-a064-403e-9a74-35389e1bad8e")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RequirementDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << requirement_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fd263818-59c5-432d-8b98-86a4dc7853c7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << requirement_diagram >> then instantiate a {@link RequirementDiagram} proxy.
     * 
     * @return a {@link RequirementDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("64c6f913-6005-4a09-8208-11d30836d9e2")
    public static RequirementDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME);
        return RequirementDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RequirementDiagram} proxy from a {@link StaticDiagram} stereotyped << requirement_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RequirementDiagram} proxy or <i>null</i>.
     */
    @objid ("893974b8-3506-4578-b3c6-b96cd1917833")
    public static RequirementDiagram instantiate(StaticDiagram obj) {
        return RequirementDiagram.canInstantiate(obj) ? new RequirementDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RequirementDiagram} proxy from a {@link StaticDiagram} stereotyped << requirement_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link RequirementDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cb50158c-4dbe-4413-a6c2-858d713483b4")
    public static RequirementDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RequirementDiagram.canInstantiate(obj))
        	return new RequirementDiagram(obj);
        else
        	throw new IllegalArgumentException("RequirementDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("85c22a20-40b5-411b-86f7-6e1aeade8013")
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
        RequirementDiagram other = (RequirementDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("7cae712b-bb73-47e4-87e1-4121fc120f79")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("97f84ba0-4397-4a8d-94c4-ed66a398394f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7c66a276-18f6-4607-94ec-d52052c50ae0")
    protected  RequirementDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("857134ec-82ed-42fe-a6bb-b747f67e61ff")
    public static final class MdaTypes {
        @objid ("f92d5823-5ec8-406b-8b80-7ccc8e1a5311")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d397c9b8-af74-43be-b8ee-d96657fae4e4")
        private static Stereotype MDAASSOCDEP;

        @objid ("95684814-e307-4055-ba57-4b05d27b7d7f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f204fee0-154a-438e-865a-b924166ceeb6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0bfd-0000-000000000000");
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
