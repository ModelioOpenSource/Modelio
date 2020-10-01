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
 * Proxy class to handle a {@link MethodologicalLink} with << Represents >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business Objects, Data Objects, Products, Artifacts, doivent par drag & drop créer des Data Objects BPMN associés.</i></p>
 */
@objid ("f455da76-7d21-4a23-86cb-44284ad9c018")
public class Represents {
    @objid ("e3f30682-25d6-4004-88a7-68d2bd048db5")
    public static final String STEREOTYPE_NAME = "Represents";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("36db6216-21d0-4cc8-8d57-6bdc63b539d6")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Represents proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Represents >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("bb9b4910-f4fd-4540-92bb-132c1018e5f3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Represents >> then instantiate a {@link Represents} proxy.
     * 
     * @return a {@link Represents} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("84e3aa38-b81b-4a18-b287-3f86fa6e092f")
    public static Represents create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME);
        return Represents.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Represents} proxy or <i>null</i>.
     */
    @objid ("1995a6da-004d-40fd-a1f3-e444f4cd7b7c")
    public static Represents instantiate(MethodologicalLink obj) {
        return Represents.canInstantiate(obj) ? new Represents(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Represents} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c1adda98-288b-458f-ab73-258860c3ec3a")
    public static Represents safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Represents.canInstantiate(obj))
        	return new Represents(obj);
        else
        	throw new IllegalArgumentException("Represents: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b68488da-cf0c-4fc1-94b3-9950d52ae9bb")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("85a28a48-571f-47ac-b1b3-7f5fb1f27c90")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("eab33bc7-f63b-4ac2-befd-f199372f524e")
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
        Represents other = (Represents) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("d1fdad4a-82d1-424b-a082-aa144c72d861")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("518fc633-f8c6-4c88-8489-238370d15a76")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c854e5a0-d5c3-43ed-9026-fe5786628ade")
    protected Represents(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("d9896322-b59f-496b-99be-d10d51513d32")
    public static final class MdaTypes {
        @objid ("8bd5e33f-3eb6-4a92-b2dc-797a758f4157")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d033ae84-9169-4cc8-8e4c-ddb83b97e52b")
        private static Stereotype MDAASSOCDEP;

        @objid ("8dc489f7-bb89-4376-9ac4-6c1f9aefd8ec")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("888ea6b0-d29e-4a2d-89f0-a43fdd97f6a8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f5d2927d-46d6-4d87-9cf2-adb4a47ca929");
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
