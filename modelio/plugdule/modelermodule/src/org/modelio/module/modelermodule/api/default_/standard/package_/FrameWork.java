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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << frameWork >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8c7e5fca-e81e-401f-92b7-8980e98e3dd1")
public class FrameWork {
    @objid ("677e5c34-b667-40c1-aecc-bf8e8b1ac2f8")
    public static final String STEREOTYPE_NAME = "frameWork";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("df7f6544-c064-49ab-acb9-8f4963213cb1")
    protected final Package elt;

    /**
     * Tells whether a {@link FrameWork proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << frameWork >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("351acc88-2798-46ca-b7e6-64edbd05ba90")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << frameWork >> then instantiate a {@link FrameWork} proxy.
     * 
     * @return a {@link FrameWork} proxy on the created {@link Package}.
     */
    @objid ("1b5ce446-714f-455e-8212-5d9f4b4f2ac8")
    public static FrameWork create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME);
        return FrameWork.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link FrameWork} proxy or <i>null</i>.
     */
    @objid ("035a9263-35f1-4190-ab30-3fd0b7bc8af4")
    public static FrameWork instantiate(Package obj) {
        return FrameWork.canInstantiate(obj) ? new FrameWork(obj) : null;
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link FrameWork} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d68891f2-64e4-47d2-af6c-9a5d70667cad")
    public static FrameWork safeInstantiate(Package obj) throws IllegalArgumentException {
        if (FrameWork.canInstantiate(obj))
        	return new FrameWork(obj);
        else
        	throw new IllegalArgumentException("FrameWork: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5691c057-095b-4522-be1d-0aedc12583f2")
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
        FrameWork other = (FrameWork) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("37101027-619a-4ef9-bd1b-01a7d34bfa3f")
    public Package getElement() {
        return this.elt;
    }

    @objid ("daf4ac8b-e374-44cb-a84f-2bb3cdbbef6f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("930a1964-9c66-446e-88eb-e90be3830d92")
    protected FrameWork(Package elt) {
        this.elt = elt;
    }

    @objid ("c823ec2f-0b36-488c-9bc7-287eaac9b7cf")
    public static final class MdaTypes {
        @objid ("59a91682-3fe7-4c09-9540-cf551c00db4f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b12bdde8-c5f1-4f8b-be34-8a439c1bd39b")
        private static Stereotype MDAASSOCDEP;

        @objid ("a7673479-60ce-439c-97fc-afccc8db03ef")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f0951cf3-eeff-42c7-8b69-d53b5eb1f6cb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01db-0000-000000000000");
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
