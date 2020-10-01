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
    @objid ("8af542aa-7634-4e0c-8c70-49bd86cdd456")
    public static final String STEREOTYPE_NAME = "requirement_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("7c01266b-a3b5-4c93-b217-505d7a6b9a34")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RequirementDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << requirement_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("03622d5d-a651-4164-8bfd-14fc25620e9f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << requirement_diagram >> then instantiate a {@link RequirementDiagram} proxy.
     * 
     * @return a {@link RequirementDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("53585e05-0ac1-41ca-a387-eee24528f486")
    public static RequirementDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
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
    @objid ("77eb6d7e-e9b2-4d9d-b215-59ec44cfe340")
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
    @objid ("e606354f-f713-4299-962f-8d26c1ef3201")
    public static RequirementDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RequirementDiagram.canInstantiate(obj))
        	return new RequirementDiagram(obj);
        else
        	throw new IllegalArgumentException("RequirementDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("587f50a7-eecf-4349-9137-ce211d5f9a6c")
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
    @objid ("bd82e753-0081-410e-a81e-0704c757f35c")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("3a0fee2e-0ab7-4431-a3e5-ab1be6559a6e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0e0a2f55-5023-4840-a656-d1c2ddafd671")
    protected RequirementDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("857134ec-82ed-42fe-a6bb-b747f67e61ff")
    public static final class MdaTypes {
        @objid ("0df27875-2860-4130-a50f-42a4dd6fc95f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8b34ca09-cc0a-4dcf-9ff9-7421d6caedeb")
        private static Stereotype MDAASSOCDEP;

        @objid ("76bba261-290a-4cd4-bfaa-2b840be0021d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6c96df14-1a9c-4c9b-bea7-dad11efebfb6")
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
