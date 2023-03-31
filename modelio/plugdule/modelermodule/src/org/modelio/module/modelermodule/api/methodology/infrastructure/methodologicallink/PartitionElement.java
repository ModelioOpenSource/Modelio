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
 * Proxy class to handle a {@link MethodologicalLink} with << PartitionElement >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Acteurs, rôles : drag & drop dans les lanes & pool permettent de les associer et faire apparaitre. Les icones (ArchiMate) doivent s’afficher par défaut.</i></p>
 */
@objid ("de449f4b-02f1-4a89-aee5-f8ce3007641b")
public class PartitionElement {
    @objid ("706dc8fc-1336-4c54-9f20-e5f25314f850")
    public static final String STEREOTYPE_NAME = "PartitionElement";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("c7cfc47f-5aa4-4e6a-af95-79613e5514f1")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link PartitionElement proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << PartitionElement >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("35a30958-4c9f-4dcf-b8a7-6a89d0caa25a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << PartitionElement >> then instantiate a {@link PartitionElement} proxy.
     * 
     * @return a {@link PartitionElement} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("08b5abc1-8b73-4e6c-9426-b6ab46324476")
    public static PartitionElement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, PartitionElement.STEREOTYPE_NAME);
        return PartitionElement.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link PartitionElement} proxy or <i>null</i>.
     */
    @objid ("ca3501c4-da25-4670-b585-6e6229877687")
    public static PartitionElement instantiate(MethodologicalLink obj) {
        return PartitionElement.canInstantiate(obj) ? new PartitionElement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link PartitionElement} proxy from a {@link MethodologicalLink} stereotyped << PartitionElement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link PartitionElement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("29f7d1b0-36ca-4c04-9405-170efe175443")
    public static PartitionElement safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (PartitionElement.canInstantiate(obj))
        	return new PartitionElement(obj);
        else
        	throw new IllegalArgumentException("PartitionElement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("91331fb1-fdcc-401b-9b23-a8272f6184f5")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("67e8e257-20b2-4f61-9cba-f089e9bc439f")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("82124408-f96d-47cf-aa3f-ef0dc5ee7a06")
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
        PartitionElement other = (PartitionElement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("df0d85f9-802e-4afd-897f-6e4b9a0f9d99")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("00bc5cac-49e3-4a38-b993-9c31304b1d0b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("af68965a-2ed3-4f68-827b-89340edc440d")
    protected  PartitionElement(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("5e6cefd6-55cf-4f85-b0bc-77215412fc10")
    public static final class MdaTypes {
        @objid ("1b59cdc3-de07-4ef3-b30e-c1b44ecdd6d1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("725a607e-4613-4f9f-a813-2a9fe2d7cf6a")
        private static Stereotype MDAASSOCDEP;

        @objid ("f9b34559-dd09-4f0a-8d17-258883f05935")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bb06754d-e639-40b4-a9f7-7659737df78d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5de33d2a-ed28-439c-aa09-d11bf1a6d878");
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
