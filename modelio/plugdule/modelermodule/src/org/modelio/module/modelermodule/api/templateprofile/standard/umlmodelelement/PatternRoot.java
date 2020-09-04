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
    @objid ("47c50ad1-2d26-4d68-a7dd-905766d56941")
    public static final String STEREOTYPE_NAME = "PatternRoot";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("0b0aeb09-440f-49fb-85fb-bd854d6ddaaa")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link PatternRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << PatternRoot >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8ed8b9aa-8da7-462e-a060-a976d40c0b9e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << PatternRoot >> then instantiate a {@link PatternRoot} proxy.
     * 
     * @return a {@link PatternRoot} proxy on the created {@link UmlModelElement}.
     */
    @objid ("e37e7016-701b-44ea-980e-a13bd4f8bee4")
    public static PatternRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PatternRoot.STEREOTYPE_NAME);
        return PatternRoot.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link PatternRoot} proxy from a {@link UmlModelElement} stereotyped << PatternRoot >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link PatternRoot} proxy or <i>null</i>.
     */
    @objid ("c887e6f6-def4-47c6-bfa7-8ef80daf8ce6")
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
    @objid ("54188e7d-c528-41df-b62e-bc6b3ea24530")
    public static PatternRoot safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (PatternRoot.canInstantiate(obj))
        	return new PatternRoot(obj);
        else
        	throw new IllegalArgumentException("PatternRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ec300d09-dfd9-4d28-a5af-a8b75502b1b7")
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
    @objid ("947d455a-ad1a-406b-8c8f-0edace70e10b")
    public UmlModelElement getElement() {
        return this.elt;
    }

    @objid ("2d6a171b-35cb-4785-b358-1ed668b6bb0a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("40c9a18c-b25b-4456-a374-dcb2bb33dbe1")
    protected PatternRoot(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("8ff5a629-6135-49ff-a514-232f119963bc")
    public static final class MdaTypes {
        @objid ("4bf1758b-a475-4208-b2d7-adcd9d13b743")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c570a21e-e92c-432b-91dc-de21f8006596")
        private static Stereotype MDAASSOCDEP;

        @objid ("2d6a86e2-8d6e-46c1-ab7d-704efffb119e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f4cd5757-b15e-4aec-8c63-f311dd37ca7b")
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
