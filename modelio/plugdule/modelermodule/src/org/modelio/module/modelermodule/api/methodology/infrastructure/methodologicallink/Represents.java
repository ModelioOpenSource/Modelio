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
    @objid ("9b436386-b991-422d-af63-2c37a4f34794")
    public static final String STEREOTYPE_NAME = "Represents";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("3ed3e01e-59fa-437f-a5bb-3cedad2f9967")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Represents proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Represents >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c0efcc4b-0d84-4197-a281-ccb10883d190")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Represents >> then instantiate a {@link Represents} proxy.
     * 
     * @return a {@link Represents} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("d8ae73f1-4e23-4b02-98ae-06adef813f1a")
    public static Represents create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME);
        return Represents.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Represents} proxy or <i>null</i>.
     */
    @objid ("95992ded-c308-4f50-accb-debb01c5845c")
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
    @objid ("c038c45c-313d-4d72-8447-d2ad20b5e950")
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

    @objid ("e43b747e-d9f8-4032-84c7-b5e2aaa4e0b7")
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
    @objid ("ab9c7c9d-c82f-43c4-80b0-03ea33d01b3a")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("3a82616e-cd59-41d7-b90d-03da631d1965")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7186e53c-bda9-45d8-b3d0-4c6fb934cae9")
    protected Represents(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("d9896322-b59f-496b-99be-d10d51513d32")
    public static final class MdaTypes {
        @objid ("5744beaa-49f4-4ea5-97eb-5a0102ffff94")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7b869343-49f2-430f-9cf6-5c2d3864ec95")
        private static Stereotype MDAASSOCDEP;

        @objid ("d7deb6fa-91cd-4f9d-b4b3-a0b77bbf18b3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("dda9af8c-2412-479b-8453-2515ccb246a1")
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
