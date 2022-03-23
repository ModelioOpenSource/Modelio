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
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << verify >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("844b6b14-0353-4b18-9447-12baf0537b75")
public class Verify {
    @objid ("c92f84d7-b3ad-44d2-bc84-36069d17024d")
    public static final String STEREOTYPE_NAME = "verify";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("3f8f116e-0619-41b4-b431-fe9438592ee2")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Verify proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << verify >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ad761ddf-efca-48c3-953d-dc38afa0ccc9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << verify >> then instantiate a {@link Verify} proxy.
     * 
     * @return a {@link Verify} proxy on the created {@link Dependency}.
     */
    @objid ("8022c009-aa34-44eb-9738-0de6c790b889")
    public static Verify create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME);
        return Verify.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Verify} proxy or <i>null</i>.
     */
    @objid ("6f95b4ff-ab88-4e47-9e15-b120c2b2a1b2")
    public static Verify instantiate(Dependency obj) {
        return Verify.canInstantiate(obj) ? new Verify(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Verify} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8ade8b5b-f4a8-42e3-9fca-1bc65acbf04f")
    public static Verify safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Verify.canInstantiate(obj))
        	return new Verify(obj);
        else
        	throw new IllegalArgumentException("Verify: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("14ffadab-3fce-485d-a61f-30bd98d87bf6")
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
        Verify other = (Verify) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("352503e7-f600-4bee-8805-035c845c8539")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ceef32a8-f6ac-4ce0-a642-1979dab8f054")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e625bd16-95c9-4f48-a3ab-7fdb539d6cbb")
    protected  Verify(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e2805429-a662-4e03-8705-adeea09fdcc7")
    public static final class MdaTypes {
        @objid ("b8879360-ddff-4976-9431-50fce6911e80")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d998706f-bcf0-4cd4-b847-086847697ccf")
        private static Stereotype MDAASSOCDEP;

        @objid ("f0548d79-63aa-4791-8daa-ff26b153506f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3e25d593-c393-4d94-9d28-e63c96d85013")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0229-0000-000000000000");
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
