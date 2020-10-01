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
 * Proxy class to handle a {@link MethodologicalLink} with << Process >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Le drag & drop d’un processus BPMN doit faire apparaitre un Business Process dans les diagrammes Archimate.</i></p>
 */
@objid ("16535a93-7544-4b0e-b4e9-66d8056f81a0")
public class Process {
    @objid ("3c2ae8d8-6293-45e4-8536-acbe5b335bc7")
    public static final String STEREOTYPE_NAME = "Process";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("05e4ece3-41ab-4dbe-a74d-fa2c5deb6446")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Process >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3db18bcd-d175-45c3-ab2b-fe95ec437408")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("1ca17e3d-1424-4f4b-a358-c0fb57f5325b")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME);
        return Process.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link MethodologicalLink} stereotyped << Process >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Process} proxy or <i>null</i>.
     */
    @objid ("639a4c0b-2a80-4cf5-bf03-d78046eef738")
    public static Process instantiate(MethodologicalLink obj) {
        return Process.canInstantiate(obj) ? new Process(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link MethodologicalLink} stereotyped << Process >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Process} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("58d4602e-db0b-4872-990f-ad8e963933af")
    public static Process safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9caff0aa-aa63-4353-b660-5528c1dd3287")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("84395eea-8463-40d1-a1b1-d270f7c50538")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("08a9514d-9f9f-41e7-b250-3e398c5ddac0")
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
        Process other = (Process) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("731d05c5-33d9-4d4f-95ef-4a554edaceff")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("677f4597-2148-4e6c-990d-d247e56682df")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("327daf1c-5d68-4306-a3ec-d5ab951ec75f")
    protected Process(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("86a4b530-cc53-4296-b4ec-0c0ed3b5a15a")
    public static final class MdaTypes {
        @objid ("38d2d906-321b-4549-9418-5b9730b2fc9d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2757f393-acb1-4149-b7b9-de82c7d27605")
        private static Stereotype MDAASSOCDEP;

        @objid ("0c5cee78-8663-49ff-bcdd-df23866a2654")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d2bf8d08-704f-48a7-a200-d324b8fc7e69")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "616b72d4-1d47-49e1-a381-2e6ecfea637c");
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
