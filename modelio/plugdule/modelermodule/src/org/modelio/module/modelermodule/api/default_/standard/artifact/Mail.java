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
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << mail >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3a3ea94-499f-4c05-bb6a-3566db79f132")
public class Mail {
    @objid ("86ac1c20-0033-4412-9d17-08e58c5cc065")
    public static final String STEREOTYPE_NAME = "mail";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("858359c8-49ab-4415-a606-cb8fdd16175b")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Mail proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << mail >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a98555d5-c2a3-4a92-a392-b5081aa32036")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << mail >> then instantiate a {@link Mail} proxy.
     * 
     * @return a {@link Mail} proxy on the created {@link Artifact}.
     */
    @objid ("b2207596-5561-4cd3-b037-288a20df1d16")
    public static Mail create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME);
        return Mail.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Mail} proxy or <i>null</i>.
     */
    @objid ("c1440c78-d6a6-49c7-a554-522085b5b620")
    public static Mail instantiate(Artifact obj) {
        return Mail.canInstantiate(obj) ? new Mail(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Mail} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4ccb7906-531a-4012-9f34-aef068063fad")
    public static Mail safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Mail.canInstantiate(obj))
        	return new Mail(obj);
        else
        	throw new IllegalArgumentException("Mail: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("58e15190-6261-4188-a743-7d419ac80f07")
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
        Mail other = (Mail) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("2a44c8f3-8c53-4361-ab72-4e30be19f7aa")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("1758fb97-65c1-4de8-a927-85dca64a158d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("31cbe268-dbdd-4b86-b3d0-e199a2e634c2")
    protected  Mail(Artifact elt) {
        this.elt = elt;
    }

    @objid ("d81ebae2-b027-4736-b68a-a9c31925809d")
    public static final class MdaTypes {
        @objid ("bb682640-5ea1-43b4-8bf2-98b82a72f556")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a3302c98-51c3-486a-870a-761af236a6d8")
        private static Stereotype MDAASSOCDEP;

        @objid ("10395c05-8606-4101-b80e-7bfffd6bcde7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a962e4d2-4eb4-491f-96bb-34df76fd2080")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "74d7cf69-58eb-48e4-b71a-e5bb0f7570f7");
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
