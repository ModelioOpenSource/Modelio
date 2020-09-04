/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
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
 * Proxy class to handle a {@link AbstractDiagram} with << AutoDiagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b410fcf6-31df-4c16-b0e5-ff41e19d9852")
public class AutoDiagram {
    @objid ("8f669b42-c02c-4e8d-8fab-2321319b6a47")
    public static final String STEREOTYPE_NAME = "AutoDiagram";

    /**
     * The underlying {@link AbstractDiagram} represented by this proxy, never null.
     */
    @objid ("fcc6dcad-35ed-47a5-b4a1-d1830f1bb4d7")
    protected final AbstractDiagram elt;

    /**
     * Tells whether a {@link AutoDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link AbstractDiagram} stereotyped << AutoDiagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4baa0307-493b-466d-bf0c-02350c7ae44f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AbstractDiagram) && ((AbstractDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AbstractDiagram} stereotyped << AutoDiagram >> then instantiate a {@link AutoDiagram} proxy.
     * 
     * @return a {@link AutoDiagram} proxy on the created {@link AbstractDiagram}.
     */
    @objid ("8a17a7a5-cc45-42dc-81bc-6033f7f8a70f")
    public static AutoDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AbstractDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME);
        return AutoDiagram.instantiate((AbstractDiagram)e);
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AbstractDiagram
     * @return a {@link AutoDiagram} proxy or <i>null</i>.
     */
    @objid ("838e712b-bc19-4285-b742-ed22035b8271")
    public static AutoDiagram instantiate(AbstractDiagram obj) {
        return AutoDiagram.canInstantiate(obj) ? new AutoDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AbstractDiagram}
     * @return a {@link AutoDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a4d1d3ba-6aca-4f04-b9f2-f83cafe90226")
    public static AutoDiagram safeInstantiate(AbstractDiagram obj) throws IllegalArgumentException {
        if (AutoDiagram.canInstantiate(obj))
        	return new AutoDiagram(obj);
        else
        	throw new IllegalArgumentException("AutoDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9d5e86b9-9fa3-4e6a-9bcd-5850bce7bb9f")
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
        AutoDiagram other = (AutoDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AbstractDiagram}. 
     * @return the AbstractDiagram represented by this proxy, never null.
     */
    @objid ("fe888354-1621-4741-b9d9-20b5a8a814b0")
    public AbstractDiagram getElement() {
        return this.elt;
    }

    @objid ("902286ec-1f3b-46d7-a1a4-ae907f0725c3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7a44853d-2616-44de-bdfa-17f2b2393be3")
    protected AutoDiagram(AbstractDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d38733b-2cc3-4edc-aec0-6c87d5b6b705")
    public static final class MdaTypes {
        @objid ("f52a7172-c2c5-4fe9-b300-690c8104bb5a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7a909543-216f-4d8f-8e16-0613dbf15ad3")
        private static Stereotype MDAASSOCDEP;

        @objid ("e719dc6f-d19c-4f95-bc08-25c0e0c8cd72")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aa08c5fd-5a96-4be1-81ea-0f88ca9bec16")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8fb43b1c-7819-11e1-a4f1-002564c97630");
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
