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
    @objid ("247cc606-67da-4861-812f-259b435988d2")
    public static final String STEREOTYPE_NAME = "Process";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("28b08175-11a3-4601-b792-5ebdc9973a96")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Process >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e4611969-f9cb-4880-97ad-60d685fbba14")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("3f4cae16-9130-486e-884c-c32db5fb3ee3")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
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
    @objid ("38d53e70-abc6-4b6e-957c-2b7d72760225")
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
    @objid ("fd80ff01-7e96-4330-b442-f05f7232e6cd")
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

    @objid ("93b43700-db4c-4c11-af07-cf0a45b1027d")
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
    @objid ("74583248-4b14-4ade-b3ca-750cb110d6dc")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("adad2c12-1398-4a79-b92e-19aa08d03b4d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8264d243-c876-4dfd-8de9-9a1dc5f129c0")
    protected  Process(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("86a4b530-cc53-4296-b4ec-0c0ed3b5a15a")
    public static final class MdaTypes {
        @objid ("e8ea7c25-29e8-46a8-a625-2e79d4acde7f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5ea36bcf-8066-409a-af51-e0fdd91fa499")
        private static Stereotype MDAASSOCDEP;

        @objid ("5acb1d61-d7a9-440c-8a58-45fc8ac21c31")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b6d5867f-39b0-4151-a3d7-6a82f01f9fc0")
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
