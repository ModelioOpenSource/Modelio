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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << destroyed >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5f98eedb-1fd0-40e9-8dd9-638944831a91")
public class Destroyed {
    @objid ("d26ba0d3-4a85-4e0b-9f2f-9f4c05b020b8")
    public static final String STEREOTYPE_NAME = "destroyed";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("d361e183-babf-4a4b-a66a-c29fae96e32e")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Destroyed proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << destroyed >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8cadfb98-18e5-4d08-b78e-b3ec995d4ef1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << destroyed >> then instantiate a {@link Destroyed} proxy.
     * 
     * @return a {@link Destroyed} proxy on the created {@link Constraint}.
     */
    @objid ("ee54bb59-62b2-4161-91c0-09cde462a8c8")
    public static Destroyed create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME);
        return Destroyed.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Destroyed} proxy or <i>null</i>.
     */
    @objid ("51adf543-3970-4d86-9fb6-37349899de34")
    public static Destroyed instantiate(Constraint obj) {
        return Destroyed.canInstantiate(obj) ? new Destroyed(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Destroyed} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("65eea834-0533-4daf-b2e3-f05705881dfa")
    public static Destroyed safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Destroyed.canInstantiate(obj))
        	return new Destroyed(obj);
        else
        	throw new IllegalArgumentException("Destroyed: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5f9eb6dd-bd2e-480b-ac19-c0e29d1b7998")
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
        Destroyed other = (Destroyed) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("39838498-724c-4d5d-a6f2-3c2d274085fd")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("14b33e13-7d24-41bc-9a46-8911041c3b35")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d48f5798-c33f-4156-ba8e-739368e2cc2e")
    protected Destroyed(Constraint elt) {
        this.elt = elt;
    }

    @objid ("74ba3d8d-a1db-491d-89b9-617b42eb8014")
    public static final class MdaTypes {
        @objid ("954bfb5d-bb3f-40a2-8752-9cd8dd9cc88e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f9bf7047-44c6-47c3-b335-f8b489486bbc")
        private static Stereotype MDAASSOCDEP;

        @objid ("4cbb29f8-2d66-4b6f-9797-73fe7185b64a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("02581dbd-2485-4a37-b1b3-93ebcbb07a52")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f3-0000-000000000000");
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
