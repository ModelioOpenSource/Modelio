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
package org.modelio.module.modelermodule.api.templateprofile.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << PatternRoot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57d27015-260c-4a65-ab1b-01cc7c60f0d1")
public class PatternRoot {
    @objid ("e4ccfab2-7378-456a-98b9-79bde36b2ee1")
    public static final String STEREOTYPE_NAME = "PatternRoot";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("5af7c893-b78f-4ffc-b125-405de81d8803")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternRoot >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("42c986af-f0b6-4a8f-9c8d-827f3289bd22")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternRoot >> then instantiate a {@link PatternRoot} proxy.
     * 
     * @return a {@link PatternRoot} proxy on the created {@link UmlModelElement}.
     */
    @objid ("ae76ea39-a0d2-4114-9f45-a11684fe15ef")
    public static PatternRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME);
        return PatternRoot.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternRoot} proxy or <i>null</i>.
     */
    @objid ("9d03b197-5849-48c5-84b1-055ff5347d50")
    public static PatternRoot instantiate(UmlModelElement obj) {
        return PatternRoot.canInstantiate(obj) ? new PatternRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link PatternRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f30107d4-0d2a-454f-8b8b-290ca4975eec")
    public static PatternRoot safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternRoot.canInstantiate(obj))
        	return new PatternRoot(obj);
        else
        	throw new IllegalArgumentException("PatternRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cac2d401-e65a-477e-95ef-6bc10c22658d")
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
        PatternRoot other = (PatternRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("704aec9b-9a52-4ed0-9689-45126f37494c")
    public UmlModelElement getElement() {
        return this.elt;
    }

    @objid ("f33a30a6-748c-468c-85b5-0727f2f4d7a0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("db9d3719-6a1f-49dc-9bcb-182f49aaf49c")
    protected PatternRoot(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("8ff5a629-6135-49ff-a514-232f119963bc")
    public static final class MdaTypes {
        @objid ("8a614290-2ae3-4af0-a92c-a3d51a7454e7")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1bdc8f01-93a9-4a13-bc46-9630a09a05d7")
        private static Stereotype MDAASSOCDEP;

        @objid ("094e001b-7f6f-47d2-a769-64f7827fd687")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c5a3265f-0fe8-481a-ab4a-f3d8356f3cc2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ad46ab04-9310-11df-a4cf-0014224f9977");
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
