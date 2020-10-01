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
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Acteurs, rôles : drag & drop dans les lanes & pool permettent de les associer et faire apparaitre. Les icones (ArchiMate) doivent s’afficher par défaut.</i></p>
 */
@objid ("de449f4b-02f1-4a89-aee5-f8ce3007641b")
public class PartitionElement {
    @objid ("92d76b02-b5fd-40c4-bfa5-80d496efcaf8")
    public static final String STEREOTYPE_NAME = "PartitionElement";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("d303059f-9ccd-4b41-98d2-3b32df8c6bd1")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link PartitionElement proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << PartitionElement >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3b0b6550-a947-47b7-a14c-1e3696b202b2")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << PartitionElement >> then instantiate a {@link PartitionElement} proxy.
     * 
     * @return a {@link PartitionElement} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("40b4c771-e8f7-4138-b53f-9ba8974fd75e")
    public static PartitionElement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME);
        return PartitionElement.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link PartitionElement} proxy or <i>null</i>.
     */
    @objid ("f5e92149-c006-4917-9e13-700d3597b158")
    public static PartitionElement instantiate(MethodologicalLink obj) {
        return PartitionElement.canInstantiate(obj) ? new PartitionElement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link PartitionElement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7e9754fc-e337-4211-bbbc-e6ee653c6598")
    public static PartitionElement safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (PartitionElement.canInstantiate(obj))
        	return new PartitionElement(obj);
        else
        	throw new IllegalArgumentException("PartitionElement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("91331fb1-fdcc-401b-9b23-a8272f6184f5")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("67e8e257-20b2-4f61-9cba-f089e9bc439f")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("f150eee0-53ae-4236-a4ff-2c7f14558d17")
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
        PartitionElement other = (PartitionElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("2332998c-75fa-4c1c-a314-61060561be95")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("825595b4-aa08-4e3a-a6cd-7d90a0abb2c5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8b04710a-2525-4ba3-a84a-a93e2eb45816")
    protected PartitionElement(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("5e6cefd6-55cf-4f85-b0bc-77215412fc10")
    public static final class MdaTypes {
        @objid ("a7d70d62-06bf-44c5-b371-248282442715")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("317578b8-aed2-444d-a08e-89f7c2fb9697")
        private static Stereotype MDAASSOCDEP;

        @objid ("7af3d242-e4ea-4b8c-9e32-565d8d732e64")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c3f83a7a-846b-40c9-9886-a5639af309f9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5de33d2a-ed28-439c-aa09-d11bf1a6d878");
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
