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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("04a6ad33-6f2c-4870-bc3c-adb6020ad262")
    public static final String STEREOTYPE_NAME = "requirement_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("0f652d83-542f-4a1a-80ea-182b7947dbf9")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RequirementDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << requirement_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("166f0bdf-cfda-4bce-9865-45d765dea7d9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << requirement_diagram >> then instantiate a {@link RequirementDiagram} proxy.
     * 
     * @return a {@link RequirementDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("76dadd3c-c020-4090-aac3-9e6812778c05")
    public static RequirementDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME);
        return RequirementDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RequirementDiagram} proxy from a {@link StaticDiagram} stereotyped << requirement_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RequirementDiagram} proxy or <i>null</i>.
     */
    @objid ("097a3546-4d5a-43fe-9f24-fa1f470300ce")
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
    @objid ("9f881324-8136-47c1-999f-f8894d5631c3")
    public static RequirementDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RequirementDiagram.canInstantiate(obj))
        	return new RequirementDiagram(obj);
        else
        	throw new IllegalArgumentException("RequirementDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("49f019cd-2643-4b4d-88ed-ad632977e22f")
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
    @objid ("b857914a-d7a8-4486-bff3-9d0d9375dce1")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("cb698fc2-8a99-474f-955a-f4790f770dd7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("54cf576d-a831-4df0-9f9f-a3ab53d2450e")
    protected RequirementDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("857134ec-82ed-42fe-a6bb-b747f67e61ff")
    public static final class MdaTypes {
        @objid ("96872a90-aa93-40f2-9ab9-93bf5512316d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6783b245-3baf-422d-a03b-ee58778bb543")
        private static Stereotype MDAASSOCDEP;

        @objid ("a4f6f6dc-cff0-4369-93eb-d5d18314f456")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b4c3108c-11df-4ada-99bc-f7dbe77394c3")
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
